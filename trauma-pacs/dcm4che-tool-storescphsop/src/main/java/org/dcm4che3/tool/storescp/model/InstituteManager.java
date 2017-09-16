package org.dcm4che3.tool.storescp.model;

import org.dcm4che3.tool.storescp.dao.ImageDAO;
import org.dcm4che3.tool.storescp.dao.InstituteDAO;
import org.dcm4che3.tool.storescp.domain.Image;
import org.dcm4che3.tool.storescp.domain.Institute;

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
