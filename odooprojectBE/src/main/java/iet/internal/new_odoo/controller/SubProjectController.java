package iet.internal.new_odoo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import iet.internal.new_odoo.dto.SubProjectDTO;
import iet.internal.new_odoo.entity.SubProjectEntity;
import iet.internal.new_odoo.service.SubProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/subproject")
@Tag(name = "Subprojects Controller", description = "This controller allows create, read, update and delete operations on Subprojects")
@CrossOrigin(origins = "*")
public class SubProjectController {

    @Autowired
    private SubProjectService subProjectService;
    private ModelMapper modelMapper;

    @Autowired
    public SubProjectController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Operation(description = "Adds a new subproject to the repository and gives it an id")
    @PostMapping("")
    public ResponseEntity<SubProjectDTO> addSubProject(@RequestBody @Schema(description = "The new subproject in a JSON format") SubProjectDTO subProjectDTO) {
        SubProjectEntity subProjectEntity = modelMapper.map(subProjectDTO, SubProjectEntity.class);
        SubProjectEntity subProjectEntity1 = subProjectService.saveSubProject(subProjectEntity);
        SubProjectDTO subProjectDTO1 = modelMapper.map(subProjectEntity1, SubProjectDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(subProjectDTO1);
    }

    @Operation(description = "Returns all the subprojects of the repository")
    @GetMapping("")
    public ResponseEntity<List<SubProjectDTO>> getAllSubProject() {
        List<SubProjectEntity> subProjectEntities = subProjectService.getAllSubProjects();
        List<SubProjectDTO> subProjectDTO = subProjectEntities.stream()
                .map(subProject -> modelMapper.map(subProject, SubProjectDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(subProjectDTO);
    }

    @Operation(description = "Returns the subproject of the repository with the specified id")
    @GetMapping("/{id}")
    public ResponseEntity<SubProjectDTO> getSubProjectById(@Parameter(description = "The id of the subproject to return") @PathVariable Long id) {
        SubProjectEntity subProjectEntity = subProjectService.findById(id);
       /* if (subProjectEntity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }*/

        SubProjectDTO subProjectDTO = modelMapper.map(subProjectEntity, SubProjectDTO.class);
        return ResponseEntity.ok(subProjectDTO);
    }

    @Operation(description = "Updates some information on the subproject of the repository that has the specified id")
    @PatchMapping("/{id}")
    public ResponseEntity<SubProjectDTO> updatePatchSubProject(@Parameter(description = "The id of the subproject to update") @PathVariable Long id, @RequestBody @Schema(description = "The updated subproject in a JSON format") SubProjectDTO subProjectDTO) {
        SubProjectEntity subProjectEntity = subProjectService.updateSubProject(id, subProjectDTO);
        SubProjectDTO subProjectDTO1 = modelMapper.map(subProjectEntity, SubProjectDTO.class);
        return ResponseEntity.ok(subProjectDTO1);
    }

    @Operation(description = "Updates all information on the subproject of the repository that has the specified id")
    @PutMapping("/{id}")
    public ResponseEntity<SubProjectDTO> updatePutSubProject(@Parameter(description = "The id of the subproject to update") @PathVariable Long id, @RequestBody @Schema(description = "The updated subproject in a JSON format") SubProjectDTO subProjectDTO) {
        SubProjectEntity subProjectEntity = subProjectService.updateSubProject(id, subProjectDTO);
        SubProjectDTO subProjectDTO1 = modelMapper.map(subProjectEntity, SubProjectDTO.class);
        return ResponseEntity.ok(subProjectDTO1);
    }

    @Operation(description = "Deletes the subproject of the repository with the specified id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubProject(@Parameter(description = "The id of the subproject to delete") @PathVariable Long id) {
        subProjectService.deleteSubProject(id);
        return ResponseEntity.ok("SubProject " + id + " deleted");
    }
}
