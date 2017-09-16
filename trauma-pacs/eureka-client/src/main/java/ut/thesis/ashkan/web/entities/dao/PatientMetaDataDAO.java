package ut.thesis.ashkan.web.entities.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import ut.thesis.ashkan.web.entities.domain.PatientMetaData;

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

    public static List<PatientMetaData> getMetaDatas(String patient_id, String patient_name) {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from PatientMetaData where patientId= :patient_id or name like :patient_name");
        query.setString("patient_id", patient_id);
        query.setString("patient_name", patient_name);
        List<PatientMetaData> patientMetaDatas = query.list();
        return patientMetaDatas;
    }


}
