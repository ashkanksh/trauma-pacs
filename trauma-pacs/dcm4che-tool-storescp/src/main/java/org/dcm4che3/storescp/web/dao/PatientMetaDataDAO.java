package org.dcm4che3.storescp.web.dao;

import org.dcm4che3.storescp.web.domain.PatientMetaData;
import org.dcm4che3.tool.storescp.dao.DAO;
import org.dcm4che3.tool.storescp.dao.SessionUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class PatientMetaDataDAO extends DAO {
    public static PatientMetaData getMetaData(int patientId) {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from PatientMetaData where patientId = :patientId");
        query.setInteger("patientId", patientId);
        PatientMetaData patientMetaData = (PatientMetaData) query.uniqueResult();
        return patientMetaData;
    }

    public static List<PatientMetaData> getMetaDatas() {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from PatientMetaData");
        List<PatientMetaData> patientMetaDatas = query.list();
        return patientMetaDatas;
    }


}
