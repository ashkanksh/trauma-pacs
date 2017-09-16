package org.dcm4che3.storescp.web;


import org.dcm4che3.storescp.web.common.GuiLoader;
import org.dcm4che3.tool.storescp.StoreSCP;
import org.dcm4che3.tool.storescu.StoreSCUThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by ASUS1 on 2/21/2017.
 */
public class GatewayAgent {
    private static final Logger LOG = LoggerFactory.getLogger(GatewayAgent.class);
    static LinkedBlockingDeque<File> files = new LinkedBlockingDeque<File>();

    public static void main(String[] args) {
        GatewayAgent gatewayAgent = new GatewayAgent();
        gatewayAgent.runningGateway(args);

    }

    public void runningGateway(String[] args){

        try {

            Properties prop = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
            prop.load(input);
            GuiLoader guiLoader = new GuiLoader(prop.getProperty("gateway.agent.frame.title"));
            //receive object from storescp and send with storescu with link blocking queue
            StoreSCP storeSCP = new StoreSCP(prop.getProperty("command").split(" "), files);
            storeSCP.run();
            StoreSCUThread storeSCUThread = new StoreSCUThread(files);
            storeSCUThread.run();
        }
        catch(Exception e){
            LOG.error("issue on loading properties file!");
        }

    }
}
