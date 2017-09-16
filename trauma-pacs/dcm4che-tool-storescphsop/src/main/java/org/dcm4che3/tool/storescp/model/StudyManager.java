package org.dcm4che3.tool.storescp.model;

import org.dcm4che3.tool.storescp.dao.StudyDAO;
import org.dcm4che3.tool.storescp.domain.Study;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
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

    public boolean deletePatient(String id) {
        int affectedRow = StudyDAO.deletStudy(id);
        if (affectedRow > 0) {
            return true;
        } else {
            return false;
        }
    }
}
