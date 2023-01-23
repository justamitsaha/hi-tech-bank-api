package com.saha.amit.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
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
}
