package org.dcm4che3.storescp.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;


/**
 * Created by ASUS1 on 4/16/2017.
 */
@Entity
public class PatientMetaData {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "patient_id")
    private String patientId;

    @Column(name = "patient_name")
    private String name;

    @Column(name = "patient_birth_date")
    private Date birthDate;

    @Column(name = "patient_sex")
    private String sex;

    @Column(name = "patient_age")
    private String age;

    @Column(name = "patient_weight")
    private String weight;

    @Column(name = "patient_address")
    private String address;

    @Column(name = "acquestion_date")
    private Date acquestionDate;

    @Column(name = "acquestion_comment")
    private String acquestionComment;

    @Column(name = "image_comment")
    private String imageComment;

    @Column(name = "study_date")
    private Date studyDateTime;

    @Column(name = "study_id")
    private String studyId;

    @Column(name = "study_modality")
    private String studyModality;

    @Column(name = "study_description")
    private String studyDescription;

    @Column(name = "series_description")
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

    public Long getId() {
        return id;
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
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
