package newodoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "SubProject not found")
public class SubProjectNotFoundException extends RuntimeException{
    public SubProjectNotFoundException(){super();}
    public SubProjectNotFoundException(String messaggio) {
        super(messaggio);
    }
}