package ut.thesis.ashkan.web.entities.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Image {
    @Id
    private int id;
    @Column
    private Date received_date;
    @Column
    private String file_name;
    @Column
    private String path;


    @JoinColumn(name = "seri_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Seri seri;

    @JoinColumn(name = "institute_name")
    @OneToOne(fetch = FetchType.EAGER)
    private Institute institute;

    public int getId() {
        return id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public Date getReceived_date() {
        return received_date;
    }

    public void setReceived_date(Date received_date) {
        this.received_date = received_date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Seri getSeri() {
        return seri;
    }

    public void setSeri(Seri seri) {
        this.seri = seri;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }
}