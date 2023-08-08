package newodoo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="program")
public class ProgramEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="program_manager")
    private String programManager;
    @Column(name="id_coo")
    private Long idCoo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idProgram") // Attributo nella classe Project che fa riferimento a program
    @JsonBackReference
    private List<ProjectEntity> projects;

    // Costruttore vuoto
    public ProgramEntity() {
    }

    // Costruttore con parametri
    public ProgramEntity(String name, String programManager, Long idCoo) {
        this.name = name;
        this.programManager = programManager;
        this.idCoo = idCoo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgramManager() {
        return programManager;
    }

    public void setProgramManager(String programManager) {
        this.programManager = programManager;
    }

    public Long getIdCoo() {
        return idCoo;
    }

    public void setIdCoo(Long idCoo) {
        this.idCoo = idCoo;
    }
}
