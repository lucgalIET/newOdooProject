package newodoo.controller;

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
    public ResponseEntity<ProgramDTO>addProgram(@RequestBody ProgramDTO programDTO){
        ProgramEntity programEntity = modelMapper.map(programDTO, ProgramEntity.class);
        ProgramEntity programEntity1=programService.saveProgram(programEntity);
        ProgramDTO programDTO1 = modelMapper.map(programEntity1, ProgramDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(programDTO1);
    }

    @GetMapping("")
    public ResponseEntity<List<ProgramDTO>>getAllProgram(){
        List<ProgramEntity> programEntities=programService.getAllPrograms();
        List<ProgramDTO> programDTO = programEntities.stream()
                .map(program -> modelMapper.map(program, ProgramDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(programDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramDTO>getProgramById(@PathVariable Long id){
        ProgramEntity programEntity = programService.findById(id);
        ProgramDTO programDTO = modelMapper.map(programEntity, ProgramDTO.class);
        return ResponseEntity.ok(programDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProgramDTO>updateProgram(@PathVariable Long id, @RequestBody ProgramDTO programDTO){

        ProgramEntity programEntity=programService.updateProgram(id, programDTO);

        ProgramDTO programDTO1 = modelMapper.map(programEntity, ProgramDTO.class);

        return ResponseEntity.ok(programDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteProgram(@PathVariable Long id){
        programService.deleteProgram(id);
        return ResponseEntity.ok("Program " + id + " deleted");
    }
}
