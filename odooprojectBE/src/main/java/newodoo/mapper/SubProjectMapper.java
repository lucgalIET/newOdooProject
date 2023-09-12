package newodoo.mapper;

import newodoo.dto.ProjectDTO;
import newodoo.dto.SubProjectDTO;
import newodoo.entity.ProjectEntity;
import newodoo.entity.SubProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class SubProjectMapper {
    public SubProjectEntity ToEntity(SubProjectDTO subProjectDTO){
        SubProjectEntity subProjectEntity = new SubProjectEntity();
        subProjectEntity.setProjectName(subProjectDTO.getProjectName());
        subProjectEntity.setProjectType(subProjectDTO.getProjectType());
        subProjectEntity.setId(subProjectDTO.getId());
        subProjectEntity.setCountry(subProjectDTO.getCountry());
        subProjectEntity.setPresumedEndMonth(subProjectDTO.getPresumedEndMonth());
        subProjectEntity.setLinkDirBusinessOnedrive(subProjectDTO.getLinkDirBusinessOnedrive());
        subProjectEntity.setLinkDirOperativeOnedrive(subProjectDTO.getLinkDirOperativeOnedrive());
        subProjectEntity.setPresumedStartingMonth(subProjectDTO.getPresumedStartingMonth());
        return subProjectEntity;
    }
    public SubProjectDTO ToDTO(SubProjectEntity subProjectEntity){
        SubProjectDTO subProjectDTO = new SubProjectDTO();
        subProjectDTO.setProjectName(subProjectEntity.getProjectName());
        subProjectDTO.setProjectType(subProjectEntity.getProjectType());
        subProjectDTO.setId(subProjectEntity.getId());
        subProjectDTO.setCountry(subProjectEntity.getCountry());
        subProjectDTO.setPresumedEndMonth(subProjectEntity.getPresumedEndMonth());
        subProjectDTO.setLinkDirBusinessOnedrive(subProjectEntity.getLinkDirBusinessOnedrive());
        subProjectDTO.setLinkDirOperativeOnedrive(subProjectEntity.getLinkDirOperativeOnedrive());
        subProjectDTO.setPresumedStartingMonth(subProjectEntity.getPresumedStartingMonth());
        return subProjectDTO;
    }
}