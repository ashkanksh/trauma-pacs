/* ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is part of dcm4che, an implementation of DICOM(TM) in
 * Java(TM), hosted at https://github.com/gunterze/dcm4che.
 *
 * The Initial Developer of the Original Code is
 * Agfa Healthcare.
 * Portions created by the Initial Developer are Copyright (C) 2011
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 * See @authors listed below
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * ***** END LICENSE BLOCK ***** */

package org.dcm4che3.tool.storescp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.dcm4che3.data.Attributes;
import org.dcm4che3.data.Tag;
import org.dcm4che3.data.VR;
import org.dcm4che3.io.DicomInputStream;
import org.dcm4che3.io.DicomInputStream.IncludeBulkData;
import org.dcm4che3.io.DicomOutputStream;
import org.dcm4che3.net.*;
import org.dcm4che3.net.pdu.PresentationContext;
import org.dcm4che3.net.service.BasicCEchoSCP;
import org.dcm4che3.net.service.BasicCStoreSCP;
import org.dcm4che3.net.service.DicomServiceException;
import org.dcm4che3.net.service.DicomServiceRegistry;
import org.dcm4che3.storescp.web.service.MetadataReceiverImpl;
import org.dcm4che3.tool.common.CLIUtils;
import org.dcm4che3.tool.storescp.domain.*;
import org.dcm4che3.tool.storescp.model.*;
import org.dcm4che3.util.AttributesFormat;
import org.dcm4che3.util.SafeClose;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.ws.Endpoint;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author Gunter Zeilinger <gunterze@gmail.com>
 */
@Service
public class StoreSCP {

    private static final Logger LOG = LoggerFactory.getLogger(StoreSCP.class);

    private static ResourceBundle rb =
            ResourceBundle.getBundle("org.dcm4che3.tool.storescp.messages");
    private static final String PART_EXT = ".part";

    private final Device device = new Device("storescp");
    private final ApplicationEntity ae = new ApplicationEntity("*");
    private final Connection conn = new Connection();
    private File storageDir;
    private AttributesFormat filePathFormat;
    private int status;

    @Value("${weburl:unknown}")
    private String weburl;


    @Value("${command:unknown}")
    private String command;

    private final BasicCStoreSCP cstoreSCP = new BasicCStoreSCP("*") {

        @Override
        protected void store(Association as, PresentationContext pc,
                             Attributes rq, PDVInputStream data, Attributes rsp)
                throws IOException {
            rsp.setInt(Tag.Status, VR.US, status);
            if (storageDir == null)
                return;

            String cuid = rq.getString(Tag.AffectedSOPClassUID);
            String iuid = rq.getString(Tag.AffectedSOPInstanceUID);
            String tsuid = pc.getTransferSyntax();
            File file = new File(storageDir, iuid + PART_EXT);
            try {
                storeTo(as, as.createFileMetaInformation(iuid, cuid, tsuid),
                        data, file);
                File f = new File(storageDir,
                        filePathFormat == null
                                ? iuid
                                : filePathFormat.format(parse(file)));
                renameTo(as, file, f);
                saveDb(f, as, pc, rq);
            } catch (Exception e) {
                deleteFile(as, file);
                throw new DicomServiceException(Status.ProcessingFailure, e);
            }
        }

    };

    public void saveDb(File file, Association as, PresentationContext pc,
                       Attributes rq) {
        DicomInputStream in = null;
        try {
            in = new DicomInputStream(file);
            in.setIncludeBulkData(IncludeBulkData.URI);
            Attributes attrs = in.readDataset(-1, -1);

            String studyiuid = null;
            String patID = null;
            String patName = null;
            String institutionName = null;

            if (attrs != null) {


                Patient patient = new Patient();
                patient.setId(Long.parseLong(attrs.getString(Tag.PatientID).replaceAll(" ", "")));
                patient.setName(attrs.getString(Tag.PatientName));
                patient.setSex(attrs.getString(Tag.PatientSex));
                patient.setBirthDate(attrs.getDate(Tag.PatientBirthDate));
                patient.setAge(Integer.parseInt(attrs.getString(Tag.PatientAge).toLowerCase().replace("y", "")));
                patient.setAddress(attrs.getString(Tag.PatientAddress));
                patient.setWeight(attrs.getDouble(Tag.PatientWeight, 0));
                PatientManager patientManager = new PatientManager();
                patientManager.saveOrUpdate(patient);


                Study study = new Study();
                study.setId(attrs.getString(Tag.StudyID));
                study.setModality(attrs.getString(Tag.Modality));
                study.setDate(attrs.getDate(Tag.StudyDateAndTime));
                study.setDescription(attrs.getString(Tag.StudyDescription));
                study.setPatient(patient);
                StudyManager studyManager = new StudyManager();
                studyManager.saveOrUpdate(study);


                Seri seri = new Seri();
                seri.setDate(attrs.getDate(Tag.SeriesDateAndTime));
                seri.setDescription(attrs.getString(Tag.SeriesDescription));
                seri.setStudy(study);
                SeriManager seriManager = new SeriManager();
                seriManager.saveOrUpdate(seri);


                Institute institute = new Institute();
                institute.setName(attrs.getString(Tag.InstitutionName));
                institute.setIp_address(as.getSocket().getInetAddress().getHostAddress());
                institute.setAddress(attrs.getString(Tag.InstitutionAddress));
                InstituteManager instituteManager = new InstituteManager();
                instituteManager.saveOrUpdate(institute);

                Image image = new Image();
                image.setReceived_date(new Date());
                image.setFile_name(rq.getString(Tag.AffectedSOPInstanceUID));
                image.setPath(System.getProperty("user.dir"));
                image.setInstitute(institute);
                image.setSeri(seri);
                ImageManager imageManager = new ImageManager();
                imageManager.saveOrUpdate(image);

                studyiuid = attrs.getString(Tag.StudyInstanceUID);
                patID = attrs.getString(Tag.PatientID);
                patID = (patID == null || patID.length() == 0) ? "<UNKNOWN>" : patID;
                patName = attrs.getString(Tag.PatientName);
                institutionName = attrs.getString(Tag.InstitutionName);
                String ipAddress = as.getSocket().getInetAddress().getHostAddress(); //ip address
                //// TODO: 12/4/2016 it seems the cuid has diffrent to file name
                String cuid = rq.getString(Tag.AffectedSOPClassUID);
                String tsuid = pc.getTransferSyntax();
            }

            LOG.info("New dicom file received -> StudyInstanceUID:" + studyiuid + " PatientID:" + patID + " PatientName:" + patName + " InstitutionName:" + institutionName);
            LOG.info("let's notify the listeners");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SafeClose.close(in);
        }
    }

    public StoreSCP() throws IOException {
        device.setDimseRQHandler(createServiceRegistry());
        device.addConnection(conn);
        device.addApplicationEntity(ae);
        ae.setAssociationAcceptor(true);
        ae.addConnection(conn);
    }

    private void storeTo(Association as, Attributes fmi,
                         PDVInputStream data, File file) throws IOException {
        LOG.info("{}: M-WRITE {}", as, file);
        file.getParentFile().mkdirs();
        DicomOutputStream out = new DicomOutputStream(file);
        try {
            out.writeFileMetaInformation(fmi);
            data.copyTo(out);
        } finally {
            SafeClose.close(out);
        }
    }

    private void renameTo(Association as, File from, File dest)
            throws IOException {
        LOG.info("{}: M-RENAME {} to {}", as, from, dest);
        if (!dest.getParentFile().mkdirs())
            dest.delete();
        if (!from.renameTo(dest))
            throw new IOException("Failed to rename " + from + " to " + dest);
    }

    private Attributes parse(File file) throws IOException {
        DicomInputStream in = new DicomInputStream(file);
        try {
            in.setIncludeBulkData(IncludeBulkData.NO);
            return in.readDataset(-1, Tag.PixelData);
        } finally {
            SafeClose.close(in);
        }
    }

    private void deleteFile(Association as, File file) {
        if (file.delete())
            LOG.info("{}: M-DELETE {}", as, file);
        else
            LOG.warn("{}: M-DELETE {} failed!", as, file);
    }

    private DicomServiceRegistry createServiceRegistry() {
        DicomServiceRegistry serviceRegistry = new DicomServiceRegistry();
        serviceRegistry.addDicomService(new BasicCEchoSCP());
        serviceRegistry.addDicomService(cstoreSCP);
        return serviceRegistry;
    }

    public void setStorageDirectory(File storageDir) {
        if (storageDir != null)
            storageDir.mkdirs();
        this.storageDir = storageDir;
    }

    public void setStorageFilePathFormat(String pattern) {
        this.filePathFormat = new AttributesFormat(pattern);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private CommandLine parseComandLine(String[] args)
            throws ParseException {
        Options opts = new Options();
        CLIUtils.addBindServerOption(opts);
        CLIUtils.addAEOptions(opts);
        CLIUtils.addCommonOptions(opts);
        addStatusOption(opts);
        addStorageDirectoryOptions(opts);
        addTransferCapabilityOptions(opts);
        return CLIUtils.parseComandLine(args, opts, rb, StoreSCP.class);
    }

    @SuppressWarnings("static-access")
    private void addStatusOption(Options opts) {
        opts.addOption(OptionBuilder
                .hasArg()
                .withArgName("code")
                .withDescription(rb.getString("status"))
                .withLongOpt("status")
                .create(null));
    }

    @SuppressWarnings("static-access")
    private void addStorageDirectoryOptions(Options opts) {
        opts.addOption(null, "ignore", false,
                rb.getString("ignore"));
        opts.addOption(OptionBuilder
                .hasArg()
                .withArgName("path")
                .withDescription(rb.getString("directory"))
                .withLongOpt("directory")
                .create(null));
        opts.addOption(OptionBuilder
                .hasArg()
                .withArgName("pattern")
                .withDescription(rb.getString("filepath"))
                .withLongOpt("filepath")
                .create(null));
    }

    @SuppressWarnings("static-access")
    private void addTransferCapabilityOptions(Options opts) {
        opts.addOption(null, "accept-unknown", false,
                rb.getString("accept-unknown"));
        opts.addOption(OptionBuilder
                .hasArg()
                .withArgName("file|url")
                .withDescription(rb.getString("sop-classes"))
                .withLongOpt("sop-classes")
                .create(null));
    }

    public void runService() {
        try {
            Endpoint.publish("http://" + this.weburl + "/ws/metadatareceiver", new MetadataReceiverImpl());
//            GuiLoader guiLoader = new GuiLoader(this.frametitle);
            CommandLine cl = parseComandLine(command.split(" "));
            CLIUtils.configureBindServer(this.conn, this.ae, cl);
            CLIUtils.configure(this.conn, cl);
            this.setStatus(CLIUtils.getIntOption(cl, "status", 0));
            configureTransferCapability(this.ae, cl);
            configureStorageDirectory(this, cl);
            ExecutorService executorService = Executors.newCachedThreadPool();
            ScheduledExecutorService scheduledExecutorService =
                    Executors.newSingleThreadScheduledExecutor();
            this.device.setScheduledExecutor(scheduledExecutorService);
            this.device.setExecutor(executorService);
            this.device.bindConnections();
        } catch (ParseException e) {
            System.err.println("storescp: " + e.getMessage());
            System.err.println(rb.getString("try"));
            System.exit(2);
        } catch (Exception e) {
            System.err.println("storescp: " + e.getMessage());
            e.printStackTrace();
            System.exit(2);
        }
    }

    private static void configureStorageDirectory(StoreSCP main, CommandLine cl) {
        if (!cl.hasOption("ignore")) {
            main.setStorageDirectory(
                    new File(cl.getOptionValue("directory", ".")));
            if (cl.hasOption("filepath"))
                main.setStorageFilePathFormat(cl.getOptionValue("filepath"));
        }
    }

    private static void configureTransferCapability(ApplicationEntity ae,
                                                    CommandLine cl) throws IOException {
        if (true || cl.hasOption("accept-unknown")) {
            ae.addTransferCapability(
                    new TransferCapability(null,
                            "*",
                            TransferCapability.Role.SCP,
                            "*"));
        } else {
            Properties p = CLIUtils.loadProperties(
                    cl.getOptionValue("sop-classes",
                            "resource:sop-classes.properties"),
                    null);
            for (String cuid : p.stringPropertyNames()) {
                String ts = p.getProperty(cuid);
                TransferCapability tc = new TransferCapability(null,
                        CLIUtils.toUID(cuid),
                        TransferCapability.Role.SCP,
                        CLIUtils.toUIDs(ts));
                ae.addTransferCapability(tc);
            }
        }
    }

}
