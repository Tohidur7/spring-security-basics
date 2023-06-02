package com.example.demo.controller;

import com.example.demo.model.Customer;

import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class ExampleController {

//    registration using MyUserDetailsService Class
//    private MyUserDetailsService myUserDetailsService ;
//
//    @Autowired
//    public ExampleController(MyUserDetailsService myUserDetailsService) {
//        this.myUserDetailsService = myUserDetailsService;
//    }
//
//    @PostMapping(path = "/register")
//    public ResponseEntity<String> home(@RequestBody Customer customer) {
//     //   return new ResponseEntity<>("welcome to registration page", HttpStatus.CREATED);
//        return new ResponseEntity<>( myUserDetailsService.register(customer), HttpStatus.CREATED);
//    }

    private CustomerRepository customerRepository ;
    private PasswordEncoder passwordEncoder ;

    @Autowired
    public ExampleController(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> home(@RequestBody Customer customer) {

        // setting up the id value
        String temp = "id:"+ UUID.randomUUID() ;
        customer.setId(temp);

        // encoding the row password
        String rawPassword = customer.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // saving the customer details with the encoded password
        customer.setPassword(encodedPassword);
        Customer save = customerRepository.save(customer);

        return new ResponseEntity<>( "user details saved successfully", HttpStatus.CREATED);
    }

    @GetMapping(path = "/user")
    public ResponseEntity<String> user() {
        return new ResponseEntity<>("welcome to user page", HttpStatus.OK);
    }

    @GetMapping(path = "/admin")
    public ResponseEntity<String> admin() {
        return new ResponseEntity<>("welcome to admin page", HttpStatus.OK);
    }


}
