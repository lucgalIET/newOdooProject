package newodoo.mapper;

import newodoo.dto.ProjectDTO;
import newodoo.dto.SubProjectDTO;
import newodoo.entity.ProjectEntity;
import newodoo.entity.SubProjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectMapper {
    @Autowired
    SubProjectMapper subProjectMapper; 
    public ProjectEntity toEntity(ProjectDTO projectDTO) {
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
        //projectEntity.setSubProjects(projectDTO.getSubProjects());

        List<SubProjectEntity> subProjectEntities = new ArrayList<>();
        List<SubProjectDTO> subProjectDTOS = projectDTO.getSubProjects();
        for (SubProjectDTO sub : subProjectDTOS) {
            SubProjectEntity subProjectEntity = subProjectMapper.toEntity(sub);
            subProjectEntities.add(subProjectEntity);
        }

        projectEntity.setSubProjects(subProjectEntities);
        return projectEntity;
    }



    public ProjectDTO toDTO(ProjectEntity projectEntity) {
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
