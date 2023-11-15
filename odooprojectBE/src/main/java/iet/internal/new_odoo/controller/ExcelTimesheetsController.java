package iet.internal.new_odoo.controller;

import iet.internal.new_odoo.TimeSheet;
import iet.internal.new_odoo.service.ExcelTimesheetService;
import newodoo.exceptions.ExcelFileProblemException;
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
    public ResponseEntity<Object> uploadExcelTimesheet(@RequestParam("file") MultipartFile file){
        if(excelTimesheetService.uploadExcelFileToServer(file)){
            try {
                TimeSheet ts = excelTimesheetService.extractTimeSheetFromFile(file.getOriginalFilename());
                return ResponseEntity.ok(ts);
            }catch(ExcelFileProblemException e){
                return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
            }
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    /*
    @GetMapping("")
    public ResponseEntity<String> testNameAndSurnameExtractor(@RequestParam("Filename") String filename, @RequestParam("NameSurname") String namesurname){
        String[] res = excelTimesheetService.extractNameAndSurname(filename, namesurname);
        return ResponseEntity.ok("Nome: " + res[0] + "; Cognome: " + res[1]);
    }
    */
}
