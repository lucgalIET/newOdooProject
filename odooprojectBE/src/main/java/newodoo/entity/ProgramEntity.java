package newodoo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name="program")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProgramEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    @Size(max = 255)
    private String name;
    @Column(name = "program_manager")
    @Size(max = 255)
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