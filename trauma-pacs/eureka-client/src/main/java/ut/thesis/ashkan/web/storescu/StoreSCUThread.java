package ut.thesis.ashkan.web.storescu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashSet;

/**
 * Created by ASUS1 on 2/21/2017.
 */
public class StoreSCUThread implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(StoreSCUThread.class);

    private HashSet<File> files;
    private String serverAddress;

    public StoreSCUThread(HashSet<File> files, String serverAddress) {
        this.files = files;
        this.serverAddress = serverAddress;
    }

    @Override
    public void run() {
        for (File file : files) {
            try {
                String filePath = file.getPath();

                StoreSCU.setArgs(
                        new String[]{
                                "-c",
                                serverAddress,
                                filePath});

            } catch (Exception e) {
                LOG.error("can not send image [{}] because: [{}]", file.getPath(), e.getMessage());
            }
        }

           /* catch (IOException e) {
                e.printStackTrace();
            }*/
    }

}
