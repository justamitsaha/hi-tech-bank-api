package com.saha.amit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OnboardUserDTO {
    private String accountType;
    private Date dob;
    private String name;
    private String pan;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String pincode;
    private String email;
    private String phone;
    private String applicationId;
    private String emailOtp;
    private String textOtp;
    private String attachment1Name;
    private String attachment2Name;
}
