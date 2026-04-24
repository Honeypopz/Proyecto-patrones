package com.proyecto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

    @Bean
    public UserDetailsService users() {
        return new InMemoryUserDetailsManager(

            User.withDefaultPasswordEncoder()
                .username("cliente")
                .password("123")
                .roles("CLIENTE")
                .build(),

            User.withDefaultPasswordEncoder()
                .username("vendedor")
                .password("123")
                .roles("VENDEDOR")
                .build(),

            User.withDefaultPasswordEncoder()
                .username("admin")
                .password("123")
                .roles("ADMIN")
                .build()
        );
    }
}
