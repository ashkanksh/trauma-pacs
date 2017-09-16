package org.dcm4che3.tool.storescp.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class DAO {
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(DAO.class);
    @Transactional
    public static void saveOrUpdate(Object object) {
        try {
            Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.saveOrUpdate(object);
            tx.commit();
            session.close();
        } catch (Exception e) {
            LOG.error("issue in connecting to database. contact administrator");
            LOG.error(e.getMessage(), e);
        }
    }
}
