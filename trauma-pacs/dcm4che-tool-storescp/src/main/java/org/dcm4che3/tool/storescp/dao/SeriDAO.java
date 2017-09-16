package org.dcm4che3.tool.storescp.dao;

import org.dcm4che3.tool.storescp.domain.Seri;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class SeriDAO extends DAO{
    public static Seri getSeri(int id) {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Seri where id = :id");
        query.setInteger("id", id);
        Seri study = (Seri) query.uniqueResult();
        return study;
    }

    public static List<Seri> getSeries() {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Seri");
        List<Seri> studies = query.list();
        return studies;
    }

    public static int deleteSeri(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Seri where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }

}
