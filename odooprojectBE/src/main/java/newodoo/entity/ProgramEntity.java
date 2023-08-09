package newodoo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "program")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Where(clause = "deleted = 0")
public class ProgramEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "program_manager")
    private String programManager;

    @Column(name = "id_coo")
    private Long idCoo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idProgram")
    // Attributo nella classe Project che fa riferimento a program
    @JsonBackReference
    private List<ProjectEntity> projects;

    @Column(name = "deleted", nullable = false, columnDefinition = "bit default 0")
    @JsonIgnore
    private boolean deleted;
}