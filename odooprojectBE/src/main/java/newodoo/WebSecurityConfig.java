/*
package newodoo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String CASUAL = "CASUAL";
   // public static final String STUDENT = "student";
    private final JwtAuthConverter jwtAuthConverter;
    public WebSecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                                .requestMatchers("/","/api/subproject", "api/subproject/**").permitAll()
                                //.requestMatchers("/api/admin/**").hasRole("ADMIN")
                                //.requestMatchers("/api/rental").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/api/project").hasRole("CASUAL")
                                .anyRequest().authenticated()
                ).oauth2ResourceServer((oauth2)-> oauth2.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConverter)));

        http.sessionManagement((sessMag)->sessMag.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
*/