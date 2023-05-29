package com.example.demo.securityConfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class MySecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            requests
                    .requestMatchers("api/admin").authenticated()
                    .requestMatchers("api/user").authenticated()
                    .requestMatchers("/api/home").permitAll();

        });
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }


//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//
//
////        UserDetails user = User.withDefaultPasswordEncoder()
////                .username("user")
////                .password("user")
////                .authorities("USER")
////                .build();
////
////
////        UserDetails admin = User.withDefaultPasswordEncoder()
////                .username("admin")
////                .password("admin")
////                .authorities("ADMIN")
////                .build();
//
//
//
//        UserDetails user = User.withUsername("user")
//                .password("user")
//                .authorities("USER")
//                .build();
//
//
//        UserDetails admin = User.withUsername("admin")
//                .password("admin")
//                .authorities("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance() ;
    }
}
