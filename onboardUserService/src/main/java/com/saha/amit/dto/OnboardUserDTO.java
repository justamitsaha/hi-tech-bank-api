package com.saha.amit.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Data
@Builder
public class OnboardUserDTO {
    @NotEmpty(message = "Please provide account type!")
    private String accountType;
    private Date dob;
    @NotEmpty(message = "Please provide your Name!")
    private String name;
    @NotEmpty(message = "Please provide your PAN card number!")
    private String pan;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String pincode;
    @NotEmpty(message = "Email cannot be blank!")
    private String email;
    @NotEmpty(message = "Phone not cannot be blank!")
    private String phone;
    private String applicationId;
    private String emailOtp;
    private String textOtp;
    private String attachment1Name;
    private String attachment2Name;
}
