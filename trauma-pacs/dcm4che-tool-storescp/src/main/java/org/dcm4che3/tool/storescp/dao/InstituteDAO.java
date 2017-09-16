package org.dcm4che3.tool.storescp.dao;

import org.dcm4che3.tool.storescp.domain.Institute;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class InstituteDAO extends DAO{
    public static Institute getInstitute(String name) {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Institute where name = :name");
        query.setString("name", name);
        Institute institute = (Institute) query.uniqueResult();
        return institute;
    }

    public static List<Institute> getInstitutes() {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Institute");
        List<Institute> institutes = query.list();
        return institutes;
    }

    public static int deleteInstitute(String name) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Institute where name = :name";
        Query query = session.createQuery(hql);
        query.setString("name", name);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }

}
