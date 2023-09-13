package newodoo.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Email;
import newodoo.dto.ProjectDTO;
import newodoo.entity.ProjectEntity;
import newodoo.entity.SubProjectEntity;
import newodoo.exceptions.ProjectNotFoundException;
import newodoo.mapper.ProjectMapper;
import newodoo.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/project")
@CrossOrigin(origins = "*")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    private ModelMapper modelMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    public ProjectController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    private ProjectNotFoundException projectNotFoundException;

    @PostMapping("")
    public ResponseEntity<ProjectDTO> addProject(@RequestBody ProjectDTO projectDTO) {
        ProjectEntity projectEntity = modelMapper.map(projectDTO, ProjectEntity.class);
        ProjectEntity projectEntity1 = projectService.saveProject(projectEntity);
        ProjectDTO projectDTO1 = modelMapper.map(projectEntity1, ProjectDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectDTO1);
    }

    @GetMapping("")
    public ResponseEntity<List<ProjectDTO>> getAllProject() {
        List<ProjectEntity> projectEntities = projectService.getAllProject();
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        List<Long> invalid = new ArrayList<>();
        for (ProjectEntity r : projectEntities) {
            try {
                ProjectDTO projectDTO = projectMapper.toDTO(r);
                projectDTOS.add(projectDTO);
            } catch (IllegalArgumentException e) {
                invalid.add(r.getId());
            }
        }
        return ResponseEntity.ok(projectDTOS);
       /* List<ProjectDTO> projectDTOs=projectEntities.stream()
                .map(project->modelMapper.map(project, ProjectDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectDTOs);*/
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {
            ProjectEntity projectEntity = projectService.findById(id);
            /*if(projectEntity==null){
                System.out.println("err");

                throw new ProjectNotFoundException("Project " + id + "NOT FOUND");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(projectNotFoundException.getMessage())
            }*/

            ProjectDTO projectDTO = modelMapper.map(projectEntity, ProjectDTO.class);
            return ResponseEntity.ok(projectDTO);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectDTO> updateApplication(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {

        ProjectEntity projectEntity = projectService.updateProject(id, projectDTO);

        ProjectDTO projectDTO1 = modelMapper.map(projectEntity, ProjectDTO.class);

        return ResponseEntity.ok(projectDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project " + id + " deleted");
    }
}
