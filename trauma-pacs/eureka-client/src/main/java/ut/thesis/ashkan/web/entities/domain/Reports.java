package ut.thesis.ashkan.web.entities.domain;

import javax.persistence.*;

@Entity
public class Reports {
    @Id
    private String id;

    @Column
    private String reporter_name;

    @Column
    private String report_text;

    @JoinColumn(name = "image_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

    @JoinColumn(name = "seri_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Seri seri;

    @JoinColumn(name = "patient_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Patient patient;

    public String getId() {
        return id;
    }

    public String getReporter_name() {
        return reporter_name;
    }

    public void setReporter_name(String reporter_name) {
        this.reporter_name = reporter_name;
    }

    public String getReport_text() {
        return report_text;
    }

    public void setReport_text(String report_text) {
        this.report_text = report_text;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Seri getSeri() {
        return seri;
    }

    public void setSeri(Seri seri) {
        this.seri = seri;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setId(String id) {
        this.id = id;
    }
}
