package ut.thesis.ashkan.web.entities.model;


import ut.thesis.ashkan.web.entities.dao.InstituteDAO;
import ut.thesis.ashkan.web.entities.domain.Institute;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class InstituteManager {
    public void saveOrUpdate(Institute institute) {
        InstituteDAO.saveOrUpdate(institute);
    }

    public Institute getInstitute(String name) {
        return InstituteDAO.getInstitute(name);
    }

    public List<Institute> getInstitutes() {
        return InstituteDAO.getInstitutes();
    }

    public boolean deleteInstitute(String name) {
        int affectedRow = InstituteDAO.deleteInstitute(name);
        if (affectedRow > 0) {
            return true;
        } else {
            return false;
        }
    }
}
