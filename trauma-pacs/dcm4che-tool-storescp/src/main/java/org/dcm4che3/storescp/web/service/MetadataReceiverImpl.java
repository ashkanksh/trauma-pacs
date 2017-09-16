package org.dcm4che3.storescp.web.service;

import org.codehaus.jackson.map.ObjectMapper;
import org.dcm4che3.storescp.web.domain.PatientMetaData;
import org.dcm4che3.storescp.web.model.PatientMetaDataManager;

import javax.jws.WebService;
import java.io.IOException;

/**
 * Created by ASUS1 on 4/15/2017.
 */
@WebService(endpointInterface = "org.dcm4che3.storescp.web.service.MetadataReceiver")
public class MetadataReceiverImpl implements MetadataReceiver {
    @Override
    public String saveMetaData(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            PatientMetaData patientMetaData = objectMapper.readValue(data, PatientMetaData.class);
            PatientMetaDataManager patientMetaDataManager = new PatientMetaDataManager();
            patientMetaDataManager.saveOrUpdate(patientMetaData);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return "ok";
    }
}
