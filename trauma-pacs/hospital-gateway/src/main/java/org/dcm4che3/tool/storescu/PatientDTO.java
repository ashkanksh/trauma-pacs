package org.dcm4che3.tool.storescu;

import java.util.Date;

/**
 * Created by ASUS1 on 4/16/2017.
 */
public class PatientDTO {
    private String patientId;
    private String name;
    private Date birthDate;
    private String sex;
    private String age;
    private String weight;
    private String address;
    private Date acquestionDate;
    private String acquestionComment;
    private String imageComment;
    private Date studyDateTime;
    private String studyId;
    private String studyModality;
    private String studyDescription;
    private String seriesDescription;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


    public String getAcquestionComment() {
        return acquestionComment;
    }

    public void setAcquestionComment(String acquestionComment) {
        this.acquestionComment = acquestionComment;
    }

    public String getImageComment() {
        return imageComment;
    }

    public void setImageComment(String imageComment) {
        this.imageComment = imageComment;
    }



    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public String getStudyModality() {
        return studyModality;
    }

    public void setStudyModality(String studyModality) {
        this.studyModality = studyModality;
    }

    public String getStudyDescription() {
        return studyDescription;
    }

    public void setStudyDescription(String studyDescription) {
        this.studyDescription = studyDescription;
    }

    public String getSeriesDescription() {
        return seriesDescription;
    }

    public void setSeriesDescription(String seriesDescription) {
        this.seriesDescription = seriesDescription;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getAcquestionDate() {
        return acquestionDate;
    }

    public void setAcquestionDate(Date acquestionDate) {
        this.acquestionDate = acquestionDate;
    }

    public Date getStudyDateTime() {
        return studyDateTime;
    }

    public void setStudyDateTime(Date studyDateTime) {
        this.studyDateTime = studyDateTime;
    }
}
