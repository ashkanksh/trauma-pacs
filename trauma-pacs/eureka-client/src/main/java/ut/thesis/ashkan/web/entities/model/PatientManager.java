package ut.thesis.ashkan.web.entities.model;


import ut.thesis.ashkan.web.entities.dao.PatientDAO;
import ut.thesis.ashkan.web.entities.domain.Patient;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class PatientManager {
    public void saveOrUpdate(Patient patient) {
        PatientDAO.saveOrUpdate(patient);
    }

    public Patient getPatient(Long id) {
        return PatientDAO.getPatient(id);
    }

    public List<Patient> getPatients() {
        return PatientDAO.getPatients();
    }

    public boolean deletePatient(Long id) {
        int affectedRow = PatientDAO.deletePatient(id);
        if (affectedRow > 0) {
            return true;
        } else {
            return false;
        }
    }
}
