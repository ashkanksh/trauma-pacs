package org.dcm4che3.tool.storescu.MetaDataSender;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceClient;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ASUS1 on 4/15/2017.
 */

@WebServiceClient(name = "MetadataReceiverImplService",
        targetNamespace = "http://service.web.storescp.dcm4che3.org/",
        wsdlLocation = "http://localhost:9999/ws/metadatareceiver?wsdl")
public class MetaDataSenderImpl extends Service {

    private final static URL MetaDataSenderIMPLSERVICE_WSDL_LOCATION;

    static {
        URL url = null;
        try {
            url = new URL("http://localhost:9999/ws/metadatareceiver?wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        MetaDataSenderIMPLSERVICE_WSDL_LOCATION = url;
    }

    protected MetaDataSenderImpl(URL wsdlDocumentLocation, QName serviceName) {
        super(wsdlDocumentLocation, serviceName);
    }

    public MetaDataSenderImpl() {
        super(MetaDataSenderIMPLSERVICE_WSDL_LOCATION,
                new QName("http://service.web.storescp.dcm4che3.org/", "MetadataReceiverImplService"));
    }

}
