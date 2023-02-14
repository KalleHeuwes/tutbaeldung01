package de.kheuwes.tutbaeldung01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .permitAll()
                .and()
                .csrf()
                .disable();
        //https://stackoverflow.com/questions/53395200/h2-console-is-not-showing-in-browser
        http.csrf().disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
}
