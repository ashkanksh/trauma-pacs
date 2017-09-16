package org.dcm4che3.tool.storescu.MetaDataSender;

import org.dcm4che3.storescp.web.service.MetadataReceiver;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * Created by ASUS1 on 4/15/2017.
 */
public class MetaDataSenderService {
    public String sendData(String data) throws Exception {
        Properties prop = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
        prop.load(input);

        URL url = new URL("http://"+prop.getProperty("weburl.metadata")+"/ws/metadatareceiver?wsdl");

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://service.web.storescp.dcm4che3.org/", "MetadataReceiverImplService");

        Service service = Service.create(url, qname);

        MetadataReceiver metadataReceiver = service.getPort(MetadataReceiver.class);

        return metadataReceiver.saveMetaData(data);
    }
}
