package org.dcm4che3.storescp.web.model;

import org.dcm4che3.storescp.web.dao.PatientMetaDataDAO;
import org.dcm4che3.storescp.web.domain.PatientMetaData;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class PatientMetaDataManager {
    public void saveOrUpdate(PatientMetaData patientMetaData) {
        PatientMetaDataDAO.saveOrUpdate(patientMetaData);
    }

    public PatientMetaData getPatientMetaData(int id) {
        return PatientMetaDataDAO.getMetaData(id);
    }

    public List<PatientMetaData> getPatientMetaDatas() {
        return PatientMetaDataDAO.getMetaDatas();
    }

}
