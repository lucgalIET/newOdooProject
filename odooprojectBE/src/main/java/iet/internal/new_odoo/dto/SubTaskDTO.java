package iet.internal.new_odoo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import iet.internal.new_odoo.KanbanStateLabel;
import iet.internal.new_odoo.ProjectPhase;
import iet.internal.new_odoo.entity.TaskEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubTaskDTO {
    private Long id;
    private String title;
    private ProjectPhase projectPhase;
    private TaskEntity idTask;
    private KanbanStateLabel kanbanStateLabel;
    private Boolean excludeFromSale;
    private LocalDate expireDate;
}
