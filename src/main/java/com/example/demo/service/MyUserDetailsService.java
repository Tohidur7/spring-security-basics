package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private CustomerRepository customerRepository ;

    @Autowired
    public MyUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> byUsername = customerRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new UsernameNotFoundException("user not found with given user name " +username);
        }

        Customer customer = byUsername.get();

        String password = customer.getPassword() ;

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(customer.getRoles());
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);

        return new User(username, password, authorities);
    }

    public String register(Customer customer) {

        Customer save = customerRepository.save(customer);
        return "user details saved successfully";
    }
}
