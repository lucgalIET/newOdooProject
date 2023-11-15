package newodoo.controller;

import newodoo.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/keycloak")
public class KeycloakController {
    @Autowired
    private KeycloakService keycloakService;
    @GetMapping("")
    public String createTestUser(){
        System.out.println("Questo endpoint è stato chiamato!");
        return keycloakService.createTestUser();
    }

}
