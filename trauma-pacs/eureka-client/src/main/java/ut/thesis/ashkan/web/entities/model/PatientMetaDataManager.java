package ut.thesis.ashkan.web.entities.model;


import org.springframework.stereotype.Service;
import ut.thesis.ashkan.web.entities.dao.PatientMetaDataDAO;
import ut.thesis.ashkan.web.entities.domain.PatientMetaData;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
@Service("patientMetaDataManager")
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

    public List<PatientMetaData> getPatientMetaDatas(String query) {
        return PatientMetaDataDAO.getMetaDatas(query, "%" + query + "%");
    }

}
