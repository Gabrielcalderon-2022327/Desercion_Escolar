package com.scrum.ProyectoDesercion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).httpBasic(Customizer.withDefaults());
        return http.build();
    }
<<<<<<< HEAD
<<<<<<< HEAD
}
=======
}
>>>>>>> 3c84bceabbce306173aad04f1d9cd4a60fa59f31
=======
}
>>>>>>> a7964f992b64ed4a900de1650dc1d527c7717a06
