package iet.internal.new_odoo.mapper;

import iet.internal.new_odoo.dto.SubTaskDTO;
import iet.internal.new_odoo.entity.SubTaskEntity;
import org.springframework.stereotype.Component;

@Component
public class SubTaskMapper {
    public SubTaskEntity toEntity(SubTaskDTO taskDTO){
        SubTaskEntity subTaskEntity = new SubTaskEntity();
        subTaskEntity.setIdTask(taskDTO.getIdTask());
        subTaskEntity.setTitle(taskDTO.getTitle());
        subTaskEnt.set
    }
    public SubTaskDTO toDTO(SubTaskEntity taskEntity){

    }
}
