package iet.internal.new_odoo.mapper;

import iet.internal.new_odoo.dto.ProgramDTO;
import iet.internal.new_odoo.entity.ProgramEntity;
import org.springframework.stereotype.Component;

@Component
public class ProgramMapper {
    public ProgramEntity toEntity(ProgramDTO programDTO){
        ProgramEntity programEntity = new ProgramEntity();
        programEntity.setId(programDTO.getId());
        programEntity.setProgramManager(programDTO.getProgramManager());
        programEntity.setName(programDTO.getName());
        programEntity.setIdCoo(programDTO.getIdCoo());
        return programEntity;
    }
    public ProgramDTO toDTO(ProgramEntity programEntity){
        ProgramDTO programDTO = new ProgramDTO();
        programDTO.setId(programEntity.getId());
        programDTO.setProgramManager(programEntity.getProgramManager());
        programDTO.setName(programEntity.getName());
        programDTO.setIdCoo(programEntity.getIdCoo());
        return programDTO;
    }

}
