package iet.internal.new_odoo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import iet.internal.new_odoo.dto.ProjectDTO;
import iet.internal.new_odoo.entity.ProjectEntity;
import iet.internal.new_odoo.mapper.ProjectMapper;
import iet.internal.new_odoo.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/project")
@Tag(name = "Projects Controller", description = "This controller allows create, read, update and delete operations on Projects")
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

    @Operation(description = "Adds a new project to the repository and gives it an id")
    @PostMapping("")
    public ResponseEntity<ProjectDTO> addProject(@RequestBody @Schema(description = "The new project in a JSON format") ProjectDTO projectDTO) {
        ProjectEntity projectEntity = modelMapper.map(projectDTO, ProjectEntity.class);
        ProjectEntity projectEntity1 = projectService.saveProject(projectEntity);
        ProjectDTO projectDTO1 = modelMapper.map(projectEntity1, ProjectDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectDTO1);
    }

    @Operation(description = "Returns all the projects of the repository")
    @GetMapping("")
    public ResponseEntity<List<ProjectDTO>> getAllProject() {
        List<ProjectEntity> projectEntities = projectService.getAllProject();
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        for (ProjectEntity r : projectEntities) {
            ProjectDTO projectDTO = projectMapper.toDTO(r);
            projectDTOS.add(projectDTO);
        }
        return ResponseEntity.ok(projectDTOS);
       /* List<ProjectDTO> projectDTOs=projectEntities.stream()
                .map(project->modelMapper.map(project, ProjectDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectDTOs);*/
    }

    @Operation(description = "Returns the project of the repository with the specified id")
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

    @Operation(description = "Updates some information of the project of the repository that has the specified id")
    public ResponseEntity<ProjectDTO> updatePatchProject(@Parameter(description = "The id of the project to update")  @PathVariable Long id, @RequestBody @Schema(description = "The updated project in a JSON format") ProjectDTO projectDTO) {
        boolean isFirst=false;
        ProjectEntity projectEntity = projectService.updateProject(id, projectDTO, isFirst);

        ProjectDTO projectDTO1 = modelMapper.map(projectEntity, ProjectDTO.class);
        return ResponseEntity.ok(projectDTO1);
    }

    @PatchMapping("firstmodified/{id}")
    @Operation(description = "Updates some information of the project of the repository that has the specified id")
    public ResponseEntity<ProjectDTO> updatePatchProjectFromPmToTl(@Parameter(description = "The id of the project to update")  @PathVariable Long id, @RequestBody @Schema(description = "The updated project in a JSON format") ProjectDTO projectDTO) {
        boolean isFirst=true;

        ProjectEntity projectEntity = projectService.updateProject(id, projectDTO, isFirst);

        ProjectDTO projectDTO1 = modelMapper.map(projectEntity, ProjectDTO.class);

        return ResponseEntity.ok(projectDTO1);
    }

    @Operation(description = "Updates all information of the project of the repository that has the specified id")
    public ResponseEntity<ProjectDTO> updatePutProject(@Parameter(description = "The id of the project to update")  @PathVariable Long id, @RequestBody @Schema(description = "The updated project in a JSON format") ProjectDTO projectDTO) {
        boolean isFirst=false;

        ProjectEntity projectEntity = projectService.updateProject(id, projectDTO, isFirst);

        ProjectDTO projectDTO1 = modelMapper.map(projectEntity, ProjectDTO.class);
        return ResponseEntity.ok(projectDTO1);
    }

    @Operation(description = "Deletes the project of the repository with the specified id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@Parameter(description = "The id of the project to delete") @PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok("Project " + id + " deleted");
    }
}
