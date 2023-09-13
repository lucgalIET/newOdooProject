package newodoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Project not found trovato")
public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(){super();}
    public ProjectNotFoundException(String messaggio) {
        super(messaggio);
    }
}
