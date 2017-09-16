package org.dcm4che3.tool.storescu;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * Created by ASUS1 on 12/10/2016.
 */
@Service
public class sender {

    @Value("${aeTitle:unknown}")
    String aeTitle;

    @Value("${port:unknown}")
    int port;

    @Value("${weburl:unknown}")
    private String weburl;

    @Value("${command:unknown}")
    private String command;

    @Value("${directory:unknown}")
    String directory;

    public void runService() {
        for (int i = 0; i < 2; i++) {
            StoreSCU storeSCU = new StoreSCU();
            String filePath = directory + "\\1.dcm";
            if (i % 2 == 1) {
                filePath = directory + "\\2.dcm";
            }
            StoreSCU.setArgs(new String[]{command, aeTitle +":"+ port, filePath});
            storeSCU.start();
        }
    }
}
