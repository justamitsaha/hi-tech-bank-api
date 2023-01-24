package com.saha.amit.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
@Data
@Builder
@Entity
@Table(name = "onboard_customer")
public class OnboardUserDTO {
    @NotEmpty(message = "Please provide account type")
    @Column(name = "accountType")
    private String accountType;
    @Column(name = "dob")
    private Date dob;
    @NotEmpty(message = "Please provide your date of Name")
    @Column(name = "name")
    @NotEmpty(message = "Please provide your PAN card number")
    private String name;
    @Column(name = "pan")
    private String pan;
    @Column(name = "address1")
    private String address1;
    @Column(name = "address2")
    private String address2;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "pincode")
    private String pincode;
    @NotEmpty(message = "Email cannot be blank")
    @Column(name = "EMAIL")
    private String email;
    @NotEmpty(message = "Phone not cannot be blank")
    @Column(name = "phone")
    private String phone;
    @Id
    @Column(name = "applicationId")
    private String applicationId;
    @Column(name = "submittedOn")
    private Date submittedOn;
}
