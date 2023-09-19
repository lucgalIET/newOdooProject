package iet.internal.new_odoo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Project not found")
public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException() {
        super();
    }

    public ProjectNotFoundException(String messaggio) {
        super(messaggio);
    }
}
