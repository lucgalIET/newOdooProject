package newodoo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import newodoo.TimeSheet;
import newodoo.service.ExcelTimesheetService;
import newodoo.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api/exceltimesheet")
//@Tag(name = "Programs Controller", description = "This controller allows create, read, update and delete operations on Programs")
@CrossOrigin(origins = "*")
public class ExcelTimesheetsController {
    @Autowired
    private ExcelTimesheetService excelTimesheetService;

    @PostMapping(value = "", consumes = {"multipart/form-data"})
    public ResponseEntity<TimeSheet> uploadExcelTimesheet(@RequestParam("file") MultipartFile file){
        if(excelTimesheetService.uploadExcelFileToServer(file)){
            TimeSheet ts = excelTimesheetService.extractTimeSheetFromFile(file.getOriginalFilename());
            if(ts != null){
                return ResponseEntity.ok(ts);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
            }


        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("")
    public ResponseEntity<String> testNameAndSurnameExtractor(@RequestParam("Filename") String filename, @RequestParam("NameSurname") String namesurname){
        String[] res = excelTimesheetService.extractNameAndSurname(filename, namesurname);
        return ResponseEntity.ok("Nome: " + res[0] + "; Cognome: " + res[1]);
    }
}
