package newodoo.mapper;

import newodoo.dto.ProjectDTO;
import newodoo.entity.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    public ProjectEntity toEntity(ProjectDTO projectDTO){
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(projectDTO.getId());
        projectEntity.setCountry(projectDTO.getCountry());
        projectEntity.setProjectType(projectDTO.getProjectType());
        projectEntity.setProjectName(projectDTO.getProjectName());
        projectEntity.setPresumedStartingMonth(projectDTO.getPresumedStartingMonth());
        projectEntity.setPresumedEndMonth(projectDTO.getPresumedEndMonth());
        projectEntity.setIdAllocatedResources(projectDTO.getIdAllocatedResources());
        projectEntity.setLinkDirBusinessOnedrive(projectDTO.getLinkDirBusinessOnedrive());
        projectEntity.setLinkDirOperativeOnedrive(projectDTO.getLinkDirOperativeOnedrive());
        projectEntity.setLinkTrello(projectDTO.getLinkTrello());
        projectEntity.setLinkGitlab(projectDTO.getLinkGitlab());
        projectEntity.setLinkLogbook(projectDTO.getLinkLogbook());
        //MAPPARE SUBPROJECT
        return projectEntity;
    }

    public ProjectDTO toDTO(ProjectEntity projectEntity){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(projectEntity.getId());
        projectDTO.setCountry(projectEntity.getCountry());
        projectDTO.setProjectType(projectEntity.getProjectType());
        projectDTO.setProjectName(projectEntity.getProjectName());
        projectDTO.setPresumedStartingMonth(projectEntity.getPresumedStartingMonth());
        projectDTO.setPresumedEndMonth(projectEntity.getPresumedEndMonth());
        projectDTO.setIdAllocatedResources(projectEntity.getIdAllocatedResources());
        projectDTO.setLinkDirBusinessOnedrive(projectEntity.getLinkDirBusinessOnedrive());
        projectDTO.setLinkDirOperativeOnedrive(projectEntity.getLinkDirOperativeOnedrive());
        projectDTO.setLinkTrello(projectEntity.getLinkTrello());
        projectDTO.setLinkGitlab(projectEntity.getLinkGitlab());
        projectDTO.setLinkLogbook(projectEntity.getLinkLogbook());
        //MAPPARE SUBPROJECT
        return projectDTO;
    }
}
