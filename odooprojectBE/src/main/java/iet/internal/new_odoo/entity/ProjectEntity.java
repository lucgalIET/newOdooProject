package iet.internal.new_odoo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iet.internal.new_odoo.Country;
import iet.internal.new_odoo.ProjectType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Data
@Where(clause = "deleted = 0")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    private Country country;
    //private String country;

    @Column(name = "project_type")
    private ProjectType projectType;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "presumed_starting_month")
    private LocalDate presumedStartingMonth;

    @Column(name = "presumed_end_month")
    private LocalDate presumedEndMonth;

    @Column(name = "id_allocated_resources")
    private Long idAllocatedResources;

    @Column(name = "link_dir_business_onedrive")
    private String linkDirBusinessOnedrive;

    @Column(name = "link_dir_operative_onedrive")
    private String linkDirOperativeOnedrive;

    @Column(name = "link_trello")
    private String linkTrello;

    @Column(name = "link_gitlab")
    private String linkGitlab;

    @Column(name = "link_logbook")
    private String linkLogbook;

    @ManyToOne
    @JoinColumn(name = "id_program")
    @JsonIgnore
    private ProgramEntity idProgram;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idProject")
    // Attributo nella classe Project che fa riferimento a SUBproject
    private List<SubProjectEntity> subProjects;

    @Column(name = "deleted", nullable = false, columnDefinition = "bit default 0")
    @JsonIgnore
    private boolean deleted;
}
