package newodoo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
//@Where(clause="deleted = 0")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="country")
    private String country;

    @Column(name="project_type")
    private String projectType;

    @Column(name="project_name")
    private String projectName;

    @Column(name="presumed_starting_month")
    private LocalDate presumedStartingMonth;

    @Column(name="presumed_end_month")
    private LocalDate presumedEndMonth;

    @Column(name="id_allocated_resources")
    private Long idAllocatedResources;

    @Column(name="link_dir_business_onedrive")
    private String linkDirBusinessOnedrive;

    @Column(name="link_dir_operative_onedrive")
    private String linkDirOperativeOnedrive;

    @Column(name="link_trello")
    private String linkTrello;

    @Column(name="link_gitlab")
    private String linkGitlab;

    @Column(name="link_logbook")
    private String linkLogbook;

    @ManyToOne
    @JoinColumn(name="id_program")
    private ProgramEntity idProgram;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idProject")
    // Attributo nella classe Project che fa riferimento a program
    @JsonBackReference
    private List<SubProjectEntity> subProjects;
}
