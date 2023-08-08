package newodoo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "sub_project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SubProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "project_type")
    private String projectType;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "presumed_starting_month")
    private Date presumedStartingMonth;

    @Column(name = "presumed_end_month")
    private Date presumedEndMonth;

    @Column(name = "link_dir_business_onedrive")
    private String linkDirBusinessOnedrive;

    @Column(name = "link_dir_operative_onedrive")
    private String linkDirOperativeOnedrive;

    @ManyToOne
    @JoinColumn(name = "id_project")
    private ProjectEntity idProject;

    @Column(name = "deleted", nullable = false, columnDefinition = "bit default 0")
    @JsonIgnore
    private boolean deleted;
}
