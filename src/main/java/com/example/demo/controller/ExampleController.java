package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class ExampleController {

    private MyUserDetailsService myUserDetailsService ;

    @Autowired
    public ExampleController(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @GetMapping(path = "/home")
    public ResponseEntity<String> home(@RequestBody Customer customer) {
        return new ResponseEntity<>("welcome to registration page", HttpStatus.CREATED);
     //   return new ResponseEntity<>( myUserDetailsService.register(customer), HttpStatus.CREATED);
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
