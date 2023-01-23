package com.saha.amit.controller;

import com.saha.amit.dto.OnboardUserDTO;
import com.saha.amit.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OnboardingController {

    @PostMapping("/applyToOpenAccount")
    public ResponseEntity<ResponseDTO> applyToOpenAccount(@RequestBody OnboardUserDTO  onboardUserDTO){
        ResponseDTO rs = ResponseDTO.builder()
                .statusCode(HttpStatus.OK.value())
                .status("Success")
                .response(true)
                .build();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(rs);
    }

}
