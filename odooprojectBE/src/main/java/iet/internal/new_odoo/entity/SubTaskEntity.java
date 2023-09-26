package iet.internal.new_odoo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iet.internal.new_odoo.KanbanStateLabel;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
@Entity
@Table(name = "sub_task")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Where(clause = "deleted = 0")
public class SubTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_task")
    @JsonIgnore
    private TaskEntity idTask;

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
