package org.dcm4che3.tool.storescp.dao;

import org.dcm4che3.tool.storescp.domain.Image;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class ImageDAO extends DAO{
    public static Image getImage(int id) {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Image where id = :id");
        query.setInteger("id", id);
        Image image = (Image) query.uniqueResult();
        return image;
    }

    public static List<Image> getImages() {
        Session session = SessionUtil.getSession();
        Query query = session.createQuery("from Image");
        List<Image> images = query.list();
        return images;
    }

    public static int deleteImage(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from Image where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id", id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }

}
