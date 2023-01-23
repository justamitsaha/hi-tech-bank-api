package com.saha.amit.controller;

import com.saha.amit.config.CustomAuthenticationProvider;
import com.saha.amit.constants.SecurityConstants;
import com.saha.amit.dto.UserDTO;
import com.saha.amit.model.Customer;
import com.saha.amit.repository.CustomerRepository;
import com.saha.amit.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class AuthenticationController {

    private final Logger LOG = Logger.getLogger(AuthenticationController.class.getName());

    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/authenticate")
    public ResponseEntity<String> getUserDetailsAfterLogin(@RequestBody UserDTO userDTO) {
        LOG.info("getUserDetailsAfterLogin");
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
        customAuthenticationProvider.authenticate(token);
        String jwt = JWTTokenGenerator.tokenGenerator(userDTO.getUsername());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(SecurityConstants.JWT_HEADER,jwt);
        return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body("OK");
    }

    @GetMapping("/dashboard")
    public String test() {
        return "hello";
    }
}
