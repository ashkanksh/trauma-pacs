package ut.thesis.ashkan.web.entities.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ut.thesis.ashkan.web.entities.domain.Study;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class StudyDAO extends DAO {

    public static Study getStudy(String id) {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Study where id = :id");
        query.setString("id", id);
        Study study = (Study) query.uniqueResult();
        return study;
    }

    public static List<Study> getStudies() {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Study");
        List<Study> studies = query.list();
        return studies;
    }

    public static List<Study> getPatientStudies(String patient_id, String patient_name) {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Study study join fetch study.patient where " +
                "study.patient.id=:patient_id or study.patient.name like :patient_name");
        query.setString("patient_id", patient_id);
        query.setString("patient_name", patient_name);
        List<Study> studies = query.list();
        return studies;
    }

    public static int deletStudy(String id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Study where id = :id";
        Query query = session.createQuery(hql);
        query.setString("id", id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }

}
