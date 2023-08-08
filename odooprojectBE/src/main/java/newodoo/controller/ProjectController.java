package newodoo.controller;

import newodoo.dto.ProjectDTO;
import newodoo.entity.ProjectEntity;
import newodoo.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {
    private ProjectService projectService;
    private ModelMapper modelMapper;

    @Autowired
    public ProjectController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateApplication(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {

        ProjectEntity projectEntity = projectService.updateProject(id, projectDTO);

        ProjectDTO projectDTO1 = modelMapper.map(projectEntity, ProjectDTO.class);

        return ResponseEntity.ok(projectDTO1);
    }
}
