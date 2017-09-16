package org.dcm4che3.tool.storescp.dao;

import org.dcm4che3.tool.storescp.domain.Patient;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class PatientDAO extends DAO{

    public static Patient getPatient(Long id) {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Patient where id = :id");
        query.setLong("id", id);
        Patient patient = (Patient) query.uniqueResult();
        return patient;
    }

    public static List<Patient> getPatients() {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Patient");
        List<Patient> patients = query.list();
        return patients;
    }

    public static int deletePatient(Long id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Patient where id = :id";
        Query query = session.createQuery(hql);
        query.setLong("id", id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }

}
