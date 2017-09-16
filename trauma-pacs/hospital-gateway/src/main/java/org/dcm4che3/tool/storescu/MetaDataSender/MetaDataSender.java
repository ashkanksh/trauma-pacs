package org.dcm4che3.tool.storescu.MetaDataSender;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by ASUS1 on 4/15/2017.
 */
@WebService(name = "MetaDataSender", targetNamespace = "http://service.web.storescp.dcm4che3.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MetaDataSender {
    @WebMethod
    @WebResult(partName = "return")
    public String getMetaDataReceiver(
            @WebParam(name = "arg0", partName = "arg0")
                    String arg0);
}
