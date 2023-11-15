package iet.internal.new_odoo;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
@EnableWebSecurity
class SecurityConfig {
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

        http.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/**").permitAll());
        return http.build();
    }

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