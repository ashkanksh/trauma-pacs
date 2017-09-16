package ut.thesis.ashkan.web.entities.model;


import org.springframework.stereotype.Service;
import ut.thesis.ashkan.web.entities.dao.StudyDAO;
import ut.thesis.ashkan.web.entities.domain.Study;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
@Service("studyManager")
public class StudyManager {
    public void saveOrUpdate(Study study) {
        StudyDAO.saveOrUpdate(study);
    }

    public Study getStudy(String id) {
        return StudyDAO.getStudy(id);
    }

    public List<Study> getPatients() {
        return StudyDAO.getStudies();
    }

    public List<Study> getPatientStudies(String patient_query) {
        return StudyDAO.getPatientStudies(patient_query, "%" + patient_query + "%");
    }

    public boolean deletePatient(String id) {
        int affectedRow = StudyDAO.deletStudy(id);
        if (affectedRow > 0) {
            return true;
        } else {
            return false;
        }
    }
}
