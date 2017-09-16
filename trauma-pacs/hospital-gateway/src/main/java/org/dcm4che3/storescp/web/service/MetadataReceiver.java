package org.dcm4che3.storescp.web.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by ashkan.keshavarzi on 4/15/2017.
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MetadataReceiver {
    @WebMethod
    String saveMetaData(String data);

}
