package newodoo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND , code = HttpStatus.NOT_FOUND, reason = "Project non trovato")
public class ProjectNotFoundException {
    public ProjectNotFoundException(){super();}
}
