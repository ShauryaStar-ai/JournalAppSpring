package net.engineeringdigest.journalApp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@EnableWebSecurity
@Configuration
public class Config {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for API testing
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/hello").permitAll()       // Only /hello is public
                        .anyRequest().authenticated()                // All other endpoints require auth
                )
                .httpBasic(Customizer.withDefaults());          // Enable HTTP Basic Auth

        return http.build();

    }

    // Define a user so Spring Security can authenticate
    // this is the sus code domain

}
