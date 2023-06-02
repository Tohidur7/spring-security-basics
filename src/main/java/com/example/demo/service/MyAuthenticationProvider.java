package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

    private PasswordEncoder passwordEncoder ;
    private CustomerRepository customerRepository ;

    @Autowired
    public MyAuthenticationProvider(PasswordEncoder passwordEncoder, CustomerRepository customerRepository) {
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String inputPassword = authentication.getCredentials().toString();

        Optional<Customer> byUsername = customerRepository.findByUsername(name);
        if (byUsername.isEmpty()) {
            throw new UsernameNotFoundException("User not found with the given credential");
        } else{

            if (passwordEncoder.matches(inputPassword, byUsername.get().getPassword())) {

                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(byUsername.get().getRoles()));
                return new UsernamePasswordAuthenticationToken(name, inputPassword, authorities);
            } else {
                throw new UsernameNotFoundException("invalid password");
            }
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
