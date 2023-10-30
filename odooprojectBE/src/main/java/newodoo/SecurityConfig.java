package newodoo;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.client.RestTemplate;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final KeycloakLogoutHandler keycloakLogoutHandler;

    SecurityConfig(KeycloakLogoutHandler keycloakLogoutHandler) {
        this.keycloakLogoutHandler = keycloakLogoutHandler;
    }
    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }
    @Order(1)
    @Bean
    public SecurityFilterChain clientFilterChain(HttpSecurity http) throws Exception {
        /*http.authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
        );
        http.oauth2Login(withDefaults());
        http.logout((logout) -> logout.addLogoutHandler(keycloakLogoutHandler).logoutSuccessUrl("/"));
        */

        http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/api/program").denyAll()
                .requestMatchers("/api/keycloak").denyAll()
                .anyRequest().permitAll());
        return http.build();
    }

//    @Order(1)
//    @Bean
//    public SecurityFilterChain clientFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((authorize) -> authorize
//                .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
//                .antMatchers("/**").authenticated()
//        );
//        http.oauth2Login(withDefaults());
//        http.logout((logout) -> logout.addLogoutHandler(keycloakLogoutHandler).logoutSuccessUrl("/"));
//
//        return http.build();
//    }

    @Order(2)
    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        /*
        http.authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
        ); //provvisorio, dovrebbe essere piu restrittivo
        http.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults())); //non dovrebbe essere con il customizer
        return http.build();

         */

        http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/**").permitAll());
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }
}