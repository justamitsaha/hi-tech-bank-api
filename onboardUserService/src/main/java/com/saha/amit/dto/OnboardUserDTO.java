package com.saha.amit.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;
@Data
@Builder
public class OnboardUserDTO {
    @NotEmpty(message = "Please provide account type")
    private String accountType;
    private Date dob;
    @NotEmpty(message = "Please provide your date of Name")
    private String name;
    @NotEmpty(message = "Please provide your date of Name")
    private String pan;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String pincode;
    @NotEmpty(message = "Email cannot be blank")
    @Email(message = "Provide a valid email ID")
    private String email;
    @NotEmpty(message = "Phone not cannot be blank")
    private String phone;
}
