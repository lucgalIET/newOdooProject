package newodoo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="sub_project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class SubProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
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

    @ManyToOne
    @JoinColumn(name="id_project")
    private ProjectEntity idProject;
}
