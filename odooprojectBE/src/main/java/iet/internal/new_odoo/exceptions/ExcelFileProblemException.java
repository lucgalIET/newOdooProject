package iet.internal.new_odoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "This Excel file can't be uploaded or accepted")
public class ExcelFileProblemException extends RuntimeException{
    public ExcelFileProblemException(){super();}
    public ExcelFileProblemException(String messaggio) {

        super(messaggio);
        System.out.println("Eccezione del file excel: " + messaggio);
    }
}
