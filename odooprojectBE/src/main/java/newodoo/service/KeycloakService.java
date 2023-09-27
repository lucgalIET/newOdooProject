package newodoo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KeycloakService {

    private final RestTemplate restTemplate;

    public KeycloakService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String createTestUser() {
        String keycloakBaseUrl = "http://192.168.2.33/auth/realms/iet-sso-test";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Costruisci il corpo della richiesta per creare l'utente
        String requestBody = "{ \"username\": \"testuser\", \"enabled\": true, \"email\": \"testuser@example.com\", \"firstName\": \"Test\", \"lastName\": \"User\" }";

        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                keycloakBaseUrl + "/users",
                HttpMethod.POST,
                request,
                String.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            return "Utente di prova creato con successo.";
        } else {
            return "Errore nella creazione dell'utente di prova: " + response.getBody();
        }
    }
}
