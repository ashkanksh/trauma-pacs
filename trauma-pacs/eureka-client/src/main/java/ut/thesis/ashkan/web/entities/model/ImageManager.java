package ut.thesis.ashkan.web.entities.model;


import org.springframework.stereotype.Service;
import ut.thesis.ashkan.web.entities.dao.ImageDAO;
import ut.thesis.ashkan.web.entities.domain.Image;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
@Service("imageManager")
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

    public List<Image> getImagesForPatient(String query, String studyId){
        return ImageDAO.getImages(query, studyId);
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
