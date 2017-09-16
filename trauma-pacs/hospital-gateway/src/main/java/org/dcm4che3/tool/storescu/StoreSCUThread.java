package org.dcm4che3.tool.storescu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by ASUS1 on 2/21/2017.
 */
public class StoreSCUThread implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(StoreSCUThread.class);

    private LinkedBlockingDeque<File> files;
    Properties prop = new Properties();


    public StoreSCUThread(LinkedBlockingDeque files) {
        this.files = files;
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
            prop.load(input);

        } catch (Exception e) {
            LOG.error("application properties file is missing [{}]", e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void run() {
        while (true) {

            try {
                String filePath = files.take().getPath();

                if (prop.getProperty("storescu.hosp.server.args.c2") != null)
                    StoreSCU.setArgs(
                            new String[]{
                                    prop.getProperty("storescu.hosp.server.args.c1"),
                                    prop.getProperty("storescu.hosp.server.args.c2"),
                                    filePath},
                            Boolean.valueOf(prop.getProperty("storescu.hosp.server.boolean")));


                StoreSCU.setArgs(
                        new String[]{
                                prop.getProperty("storescu.trauma.server.args.c1"),
                                prop.getProperty("storescu.trauma.server.args.c2"),
                                filePath},
                        Boolean.valueOf(prop.getProperty("storescu.trauma.server.boolean")));
//                storeSCU.sendFiles();
            } catch (InterruptedException e) {
                LOG.error("can not take from queue because: [{}]", e.getMessage());
            }/* catch (IOException e) {
                e.printStackTrace();
            }*/
        }
    }
}
