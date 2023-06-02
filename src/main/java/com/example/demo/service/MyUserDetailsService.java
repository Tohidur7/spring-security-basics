//package com.example.demo.service;
//
//import com.example.demo.model.Customer;
//import com.example.demo.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Optional;
//import java.util.Set;
//import java.util.UUID;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    private CustomerRepository customerRepository ;
//    private PasswordEncoder passwordEncoder ;
//
//    @Autowired
//    public MyUserDetailsService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
//        this.customerRepository = customerRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<Customer> byUsername = customerRepository.findByUsername(username);
//        if (byUsername.isEmpty()) {
//            throw new UsernameNotFoundException("user not found with given user name " +username);
//        }
//
//        Customer customer = byUsername.get();
//
//        String password = customer.getPassword() ;
//
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(customer.getRoles());
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        authorities.add(authority);
//
//        return new User(username, password, authorities);
//    }
//
//    public String register(Customer customer) {
//
//        // setting up the id value
//        String temp = "id:"+ UUID.randomUUID() ;
//        customer.setId(temp);
//
//        // encoding the row password
//        String rawPassword = customer.getPassword();
//        String encodedPassword = passwordEncoder.encode(rawPassword);
//
//        // saving the customer details with the encoded password
//        customer.setPassword(encodedPassword);
//        Customer save = customerRepository.save(customer);
//        return "user details saved successfully";
//    }
//}
