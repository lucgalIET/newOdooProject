package iet.internal.new_odoo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iet.internal.new_odoo.Country;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Table(name = "sub_project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Where(clause = "deleted = 0")
public class SubProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    private Country country;

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
    @JsonIgnore
    private ProjectEntity idProject;

    @Column(name = "deleted", nullable = false, columnDefinition = "bit default 0")
    @JsonIgnore
    private boolean deleted;
}
