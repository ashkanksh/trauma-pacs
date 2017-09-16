package ut.thesis.ashkan.web.entities.model;


import org.springframework.stereotype.Service;
import ut.thesis.ashkan.web.entities.dao.ReportDAO;
import ut.thesis.ashkan.web.entities.domain.Reports;

import java.util.List;

/**
 * Created by ASUS1 on 12/9/2016.
 */
@Service("reportManager")
public class ReportManager {
    public void saveOrUpdate(Reports reports) {
        ReportDAO.saveOrUpdate(reports);
    }

    public void setReports(String reporterName, String reportText, String fieldName, String fieldValue) {
        ReportDAO.setReports(reporterName, reportText, fieldName, fieldValue);
    }

    public List<Reports> getReports(String field_name, String field_value) {
        return ReportDAO.getReports(field_name, field_value);
    }

    public List<Reports> getReports(String reporter_name, String field_name, String field_value) {
        return ReportDAO.getReports(reporter_name, field_name, field_value);
    }

}
