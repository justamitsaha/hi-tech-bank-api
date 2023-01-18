package com.saha.amit.controller;

import com.saha.amit.config.HiTechUsernamePwdAuthenticationProvider;
import com.saha.amit.dto.UserDTO;
import com.saha.amit.model.Customer;
import com.saha.amit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthenticationController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    HiTechUsernamePwdAuthenticationProvider hiTechUsernamePwdAuthenticationProvider;

    @PostMapping("/authenticate")
    public Customer login(@RequestBody UserDTO user){
        System.out.println("user.getUsername()");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        hiTechUsernamePwdAuthenticationProvider.authenticate(token);
        boolean result = token.isAuthenticated();
        if(result){
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        List<Customer> customers = customerRepository.findByEmail(user.getUsername());
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }
    }

    @GetMapping("/dashboard")
    public Customer login2(Authentication authentication){
        List<Customer> customers = customerRepository.findByEmail(authentication.getName());
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }
    }
}
