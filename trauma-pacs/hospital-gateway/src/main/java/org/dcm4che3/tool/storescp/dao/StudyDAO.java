package org.dcm4che3.tool.storescp.dao;

import org.dcm4che3.tool.storescp.domain.Study;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
