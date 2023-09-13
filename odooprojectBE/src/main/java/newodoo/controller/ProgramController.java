package newodoo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import newodoo.dto.ProgramDTO;
import newodoo.entity.ProgramEntity;
import newodoo.service.ProgramService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/program")
@Tag(name = "Programs Controller", description = "This controller allows create, read, update and delete operations on Programs")
@CrossOrigin(origins = "*")
public class ProgramController {
    @Autowired
    private ProgramService programService;
    private ModelMapper modelMapper;
    @Autowired
    public ProgramController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostMapping("")
    @Operation(description = "Adds a new program to the repository and gives it an id")
    public ResponseEntity<ProgramDTO>addProgram(@RequestBody @Schema(description = "The new program in a JSON format") ProgramDTO programDTO){
        ProgramEntity programEntity = modelMapper.map(programDTO, ProgramEntity.class);
        ProgramEntity programEntity1 = programService.saveProgram(programEntity);
        ProgramDTO programDTO1 = modelMapper.map(programEntity1, ProgramDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(programDTO1);
    }

    @GetMapping("")
    @Operation(description = "Returns all the programs of the repository")
    public ResponseEntity<List<ProgramDTO>>getAllProgram(){
        List<ProgramEntity> programEntities=programService.getAllPrograms();
        List<ProgramDTO> programDTO = programEntities.stream()
                .map(program -> modelMapper.map(program, ProgramDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(programDTO);
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns the program of the repository with the specified id")
    public ResponseEntity<ProgramDTO>getProgramById(@Parameter(description = "The id of the program to return") @PathVariable Long id){
        ProgramEntity programEntity = programService.findById(id);
        ProgramDTO programDTO = modelMapper.map(programEntity, ProgramDTO.class);
        return ResponseEntity.ok(programDTO);
    }

    @PatchMapping("/{id}")
    @Operation(description = "Updates some information on the program of the repository that has the specified id")
    public ResponseEntity<ProgramDTO> updatePatchProgram(@Parameter(description = "The id of the program to update") @PathVariable Long id, @RequestBody @Schema(description = "The updated program in a JSON format")  ProgramDTO programDTO){

        ProgramEntity programEntity=programService.updateProgram(id, programDTO);

        ProgramDTO programDTO1 = modelMapper.map(programEntity, ProgramDTO.class);

        return ResponseEntity.ok(programDTO1);
    }
    @PutMapping("/{id}")
    @Operation(description = "Updates all information on the program of the repository that has the specified id")
    public ResponseEntity<ProgramDTO> updatePutProgram(@Parameter(description = "The id of the program to update") @PathVariable Long id, @RequestBody @Schema(description = "The updated program in a JSON format")  ProgramDTO programDTO){

        ProgramEntity programEntity=programService.updateProgram(id, programDTO);

        ProgramDTO programDTO1 = modelMapper.map(programEntity, ProgramDTO.class);

        return ResponseEntity.ok(programDTO1);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Deletes the program of the repository with the specified id")
    public ResponseEntity<String>deleteProgram(@Parameter(description = "The id of the program to delete") @PathVariable Long id){
        programService.deleteProgram(id);
        return ResponseEntity.ok("Program " + id + " deleted");
    }
}
