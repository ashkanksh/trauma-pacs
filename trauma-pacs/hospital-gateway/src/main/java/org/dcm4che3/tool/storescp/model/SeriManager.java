package org.dcm4che3.tool.storescp.model;

import org.dcm4che3.tool.storescp.dao.SeriDAO;
import org.dcm4che3.tool.storescp.domain.Seri;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class SeriManager {
    public void saveOrUpdate(Seri seri) {
        SeriDAO.saveOrUpdate(seri);
    }

    public Seri getStudy(int id) {
        return SeriDAO.getSeri(id);
    }

    public List<Seri> getSeries() {
        return SeriDAO.getSeries();
    }

    public boolean deletePatient(int id) {
        int affectedRow = SeriDAO.deleteSeri(id);
        if (affectedRow > 0) {
            return true;
        } else {
            return false;
        }
    }
}
