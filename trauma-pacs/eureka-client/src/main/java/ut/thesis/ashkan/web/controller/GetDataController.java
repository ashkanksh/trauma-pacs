package ut.thesis.ashkan.web.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ut.thesis.ashkan.web.entities.domain.Reports;
import ut.thesis.ashkan.web.entities.model.PatientMetaDataManager;
import ut.thesis.ashkan.web.entities.model.ReportManager;
import ut.thesis.ashkan.web.entities.model.StudyManager;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GetDataController {
    Logger logger = LoggerFactory.getLogger(GetDataController.class);

    @Resource(name = "patientMetaDataManager")
    PatientMetaDataManager patientMetaDataManager;

    @Resource(name = "studyManager")
    StudyManager studyManager;

    @Resource(name = "reportManager")
    ReportManager reportManager;


    @ResponseBody
    @RequestMapping(value = "/get_patient_data", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getPatientData(@RequestParam(value = "query") String query) {
        logger.info("get patient data for query [{}]", query);
        Map<String, Object> results = new HashMap();
        results.put("images_data", studyManager.getPatientStudies(query));
        results.put("meta_data", patientMetaDataManager.getPatientMetaDatas(query));
        ObjectMapper mapper = new ObjectMapper();

        String json = null;
        try {
            json = mapper.writeValueAsString(results);
        } catch (IOException e) {
            logger.error("cant create json value for query [{}]", query);
            e.printStackTrace();

        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/save_reporter_reports",produces = "application/json;charset=UTF-8")
    public String saveReports(@RequestParam(value = "reporter") String reporter,
                              @RequestParam(value = "text") String reporterText,
                              @RequestParam(value = "patient_id", required = false) String patientId,
                              @RequestParam(value = "study_id", required = false) String studyId,
                              @RequestParam(value = "seri_id", required = false) String seriId,
                              @RequestParam(value = "image_id", required = false) String imageId) {
        List<String> fieldValueMap = getFieldValue(patientId, studyId, seriId, imageId);
        if (fieldValueMap.isEmpty() || reporter.isEmpty()) {
            logger.error("issue in inputs");
            return "input has problem";
        }
        String field_name = fieldValueMap.get(0);
        String field_value = fieldValueMap.get(1);

        logger.info("save report data for [{}] and value is [{}]", field_name, field_value);


        String json = null;
        try {
            reportManager.setReports(reporter, reporterText, field_name, field_value);
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString("ok");
        } catch (IOException e) {
            logger.info("cant create json for  save report data of [{}] and value is  [{}]", field_name, field_value);
            e.printStackTrace();

        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/get_reporter_reports", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getReports(@RequestParam(value = "reporter") String reporter,
                             @RequestParam(value = "patient_id", required = false) String patientId,
                             @RequestParam(value = "study_id", required = false) String studyId,
                             @RequestParam(value = "seri_id", required = false) String seriId,
                             @RequestParam(value = "image_id", required = false) String imageId) {
        List<String> fieldValueMap = getFieldValue(patientId, studyId, seriId, imageId);
        if (fieldValueMap.isEmpty() || reporter.isEmpty()) {
            logger.error("issue in inputs");
            return "input has problem";
        }
        String field_name = fieldValueMap.get(0);
        String field_value = fieldValueMap.get(1);
        logger.info("get report data for [{}] and value is [{}]", field_name, field_value);
        List<Reports> reportsList = reportManager.getReports(reporter, field_name, field_value);
        ObjectMapper mapper = new ObjectMapper();

        String json = null;
        try {
            json = mapper.writeValueAsString(reportsList);
        } catch (IOException e) {
            logger.info("cant create json for  report data of [{}] and value is  [{}]", field_name, field_value);
            e.printStackTrace();

        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/get_reports", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getReports(@RequestParam(value = "patient_id", required = false) String patientId,
                             @RequestParam(value = "study_id", required = false) String studyId,
                             @RequestParam(value = "seri_id", required = false) String seriId,
                             @RequestParam(value = "image_id", required = false) String imageId) {
        List<String> fieldValueMap = getFieldValue(patientId, studyId, seriId, imageId);
        if (fieldValueMap.isEmpty()) {
            logger.error("issue in inputs");
            return "input has problem";
        }
        String field_name = fieldValueMap.get(0);
        String field_value = fieldValueMap.get(1);
        logger.info("get report data for [{}] and value is [{}]", field_name, field_value);
        List<Reports> reportsList = reportManager.getReports(field_name, field_value);
        ObjectMapper mapper = new ObjectMapper();

        String json = null;
        try {
            json = mapper.writeValueAsString(reportsList);
        } catch (IOException e) {
            logger.info("cant create json for  report data of [{}] and value is  [{}]", field_name, field_value);
            e.printStackTrace();

        }
        return json;
    }

    private List<String> getFieldValue(String patientId, String studyId, String seriId, String imageId) {
        List<String> results = new ArrayList<>();
        if (patientId != null) {
            results.add("patient_id");
            results.add(patientId);
        } else if (studyId != null) {
            results.add("study_id");
            results.add(studyId);
        } else if (seriId != null) {
            results.add("seri_id");
            results.add(seriId);
        } else if (imageId != null) {
            results.add("image_id");
            results.add(imageId);
        }
        return results;
    }
}
