package ut.thesis.ashkan.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ut.thesis.ashkan.web.entities.domain.Image;
import ut.thesis.ashkan.web.entities.model.ImageManager;
import ut.thesis.ashkan.web.entities.model.PatientMetaDataManager;
import ut.thesis.ashkan.web.entities.model.ReportManager;
import ut.thesis.ashkan.web.storescu.StoreSCUThread;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashSet;
import java.util.List;

/**
 * Created by ASUS1 on 5/21/2017.
 */
@RestController
public class SendDataController {
    Logger logger = LoggerFactory.getLogger(SendDataController.class);

    @Resource(name = "patientMetaDataManager")
    PatientMetaDataManager patientMetaDataManager;

    @Resource(name = "imageManager")
    ImageManager imageManager;

    @Resource(name = "reportManager")
    ReportManager reportManager;


    @ResponseBody
    @RequestMapping(value = "/send_patient_image", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public String getPatientData(@RequestParam(value = "patient_id") String patientId,
                                 @RequestParam(value = "study_id") String studyId,
                                 @RequestParam(value = "server_address") String serverAddress) {
        logger.info("send patient data for patient [{}] and study id [{}]", patientId, studyId);
        List<Image> images = imageManager.getImagesForPatient(patientId, studyId);

        HashSet<File> files = new HashSet<File>();
        for (Image image: images) {
            File file = new File(image.getPath()+"/"+image.getFile_name());
            files.add(file);
        }
        StoreSCUThread storeSCUThread = new StoreSCUThread(files,serverAddress);
        storeSCUThread.run();

        return "اطلاعات به آدرس خواسته شده ارسال می شود";
    }

}
