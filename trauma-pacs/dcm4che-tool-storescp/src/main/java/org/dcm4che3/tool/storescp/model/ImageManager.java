package org.dcm4che3.tool.storescp.model;

import org.dcm4che3.tool.storescp.dao.ImageDAO;
import org.dcm4che3.tool.storescp.domain.Image;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
public class ImageManager {
    public void saveOrUpdate(Image image) {
        ImageDAO.saveOrUpdate(image);
    }

    public Image getImage(int id) {
        return ImageDAO.getImage(id);
    }

    public List<Image> getImages() {
        return ImageDAO.getImages();
    }

    public boolean deleteImage(int id) {
        int affectedRow = ImageDAO.deleteImage(id);
        if (affectedRow > 0) {
            return true;
        } else {
            return false;
        }
    }
}
