package ut.thesis.ashkan.web.entities.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ut.thesis.ashkan.web.entities.domain.Reports;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class ReportDAO extends DAO {


    public static void setReports(String reporter_name, String report_text, String field_name, String field_value) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("insert into Reports (reporter_name, report_text, " + field_name + ") " +
                "VALUES(:reporter_name, :report_text ,:field_value)");
        query.setParameter("reporter_name", reporter_name)
                .setParameter("report_text", report_text)
                .setParameter("field_value", field_value);
        query.executeUpdate();
        tx.commit();
    }

    public static List<Reports> getReports(String field_name, String field_value) {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Reports where " + field_name + "=:field_value");
        query.setString("field_value", field_value);
        List<Reports> reportsList = query.list();
        return reportsList;
    }

    public static List<Reports> getReports(String reporter_name, String field_name, String field_value) {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Reports where reporter_name=:reporter_name and " + field_name + " =:field_value");
        query.setString("reporter_name", reporter_name);
        query.setString("field_value", field_value);
        List<Reports> reportsList = query.list();
        return reportsList;
    }

    public static int deletReport(String id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Reports where id = :id";
        Query query = session.createQuery(hql);
        query.setString("id", id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }

}
