package org.dcm4che3.tool.storescp.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class DAO {

    public static void saveOrUpdate(Object object) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(object);
        tx.commit();
        session.close();
    }
}
