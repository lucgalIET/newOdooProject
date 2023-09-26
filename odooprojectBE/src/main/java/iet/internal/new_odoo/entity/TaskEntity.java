package iet.internal.new_odoo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iet.internal.new_odoo.KanbanStateLabel;
import iet.internal.new_odoo.ProjectPhase;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Where(clause = "deleted = 0")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_phase")
    private ProjectPhase projectPhase;

    @ManyToOne
    @JoinColumn(name = "id_project")
    @JsonIgnore
    private ProjectEntity idProject;

    @Enumerated(EnumType.STRING)
    @Column(name = "kanban_state_label")
    private KanbanStateLabel kanbanStateLabel;

    @Column(name = "exclude_from_sale")
    private Boolean excludeFromSale;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Column(name = "deleted")
    private Boolean deleted;
}
