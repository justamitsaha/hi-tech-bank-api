package com.saha.amit.controller;

import com.saha.amit.constants.SecurityConstants;
import com.saha.amit.dto.UserDTO;
import com.saha.amit.repository.CustomerRepository;
import com.saha.amit.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.logging.Logger;
import java.util.stream.Stream;

@RestController
public class AuthenticationController {

    private final Logger LOG = Logger.getLogger(AuthenticationController.class.getName());

//    @Autowired
//    CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("/public/authenticate")
    public ResponseEntity<String> getUserDetailsAfterLogin(@RequestBody UserDTO userDTO, HttpServletResponse response) {
        LOG.info("getUserDetailsAfterLogin");
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword());
//        customAuthenticationProvider.authenticate(token);
        String jwt = JWTTokenGenerator.tokenGenerator(userDTO.getUsername());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(SecurityConstants.JWT_HEADER,jwt);
        Cookie cookie = new Cookie(SecurityConstants.JWT_HEADER, jwt);
        cookie.setMaxAge(120);
        cookie.setHttpOnly(false);
        cookie.setSecure(false);
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).headers(responseHeaders).body("OK");
    }

    @GetMapping("/private/dashboard")
    public String test(HttpServletRequest httpServletRequest) {
        LOG.info("Dashboard");
        return "hello";
    }
}
