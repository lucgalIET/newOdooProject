package newodoo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name="sub_project")
public class SubProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public Date getPresumed_starting_month() {
        return presumed_starting_month;
    }

    public void setPresumed_starting_month(Date presumed_starting_month) {
        this.presumed_starting_month = presumed_starting_month;
    }

    public Date getPresumed_end_month() {
        return presumed_end_month;
    }

    public void setPresumed_end_month(Date presumed_end_month) {
        this.presumed_end_month = presumed_end_month;
    }

    public String getLink_dir_business_onedrive() {
        return link_dir_business_onedrive;
    }

    public void setLink_dir_business_onedrive(String link_dir_business_onedrive) {
        this.link_dir_business_onedrive = link_dir_business_onedrive;
    }

    public String getLink_dir_operative_onedrive() {
        return link_dir_operative_onedrive;
    }

    public void setLink_dir_operative_onedrive(String link_dir_operative_onedrive) {
        this.link_dir_operative_onedrive = link_dir_operative_onedrive;
    }

    public Long getId_project() {
        return id_project;
    }

    public void setId_project(Long id_project) {
        this.id_project = id_project;
    }

    public SubProjectEntity(Long id, String country, String project_type, String project_name, Date presumed_starting_month, Date presumed_end_month, String link_dir_business_onedrive, String link_dir_operative_onedrive, Long id_project) {
        this.id = id;
        this.country = country;
        this.project_type = project_type;
        this.project_name = project_name;
        this.presumed_starting_month = presumed_starting_month;
        this.presumed_end_month = presumed_end_month;
        this.link_dir_business_onedrive = link_dir_business_onedrive;
        this.link_dir_operative_onedrive = link_dir_operative_onedrive;
        this.id_project = id_project;
    }
    public SubProjectEntity(){

    }

    @Size(max = 255)
    @Column(name="country")
    private String country;
    @Size(max = 255)
    @Column(name="project_type")
    private String project_type;
    @Size(max = 255)
    @Column(name="project_name")
    private String project_name;
    @Column(name="presumed_starting_month")
    private Date presumed_starting_month;
    @Column(name="presumed_end_month")
    private Date presumed_end_month;
    @Size(max = 255)
    @Column(name="link_dir_business_onedrive")
    private String link_dir_business_onedrive;
    @Size(max = 255)
    @Column(name="link_dir_operative_onedrive")
    private String link_dir_operative_onedrive;
    @Column(name="id_project")
    private Long id_project;
}
