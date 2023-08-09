package newodoo.controller;

import newodoo.dto.SubProjectDTO;
import newodoo.entity.SubProjectEntity;
import newodoo.service.SubProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/subProject")
public class SubProjectController {
    @Autowired
    private SubProjectService subProjectService;
    private ModelMapper modelMapper;
    @Autowired
    public SubProjectController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    public ResponseEntity<SubProjectDTO>addSubProject(@RequestBody SubProjectDTO subProjectDTO){
        SubProjectEntity subProjectEntity = modelMapper.map(subProjectDTO, SubProjectEntity.class);
        SubProjectEntity subProjectEntity1=subProjectService.saveSubProject(subProjectEntity);
        SubProjectDTO subProjectDTO1 = modelMapper.map(subProjectEntity1, SubProjectDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(subProjectDTO1);
    }

    @GetMapping("")
    public ResponseEntity<List<SubProjectDTO>>getAllSubProject(){
        List<SubProjectEntity> subProjectEntities=subProjectService.getAllSubProjects();
        List<SubProjectDTO> subProjectDTO = subProjectEntities.stream()
                .map(subProject -> modelMapper.map(subProject, SubProjectDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(subProjectDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubProjectDTO>getSubProjectById(@PathVariable Long id){
        SubProjectEntity subProjectEntity = subProjectService.findById(id);
        SubProjectDTO subProjectDTO = modelMapper.map(subProjectEntity, SubProjectDTO.class);
        return ResponseEntity.ok(subProjectDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SubProjectDTO>updateSubProject(@PathVariable Long id, @RequestBody SubProjectDTO subProjectDTO){

        SubProjectEntity subProjectEntity=subProjectService.updateSubProject(id, subProjectDTO);

        SubProjectDTO subProjectDTO1 = modelMapper.map(subProjectEntity, SubProjectDTO.class);

        return ResponseEntity.ok(subProjectDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteSubProject(@PathVariable Long id){
        subProjectService.deleteSubProject(id);
        return ResponseEntity.ok("SubProject " + id + " deleted");
    }
}
