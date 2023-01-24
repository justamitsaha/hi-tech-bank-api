package com.saha.amit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "onboard_customer")
public class OnboardUser {
    @NotEmpty(message = "Please provide account type!")
    @Column(name = "accountType")
    private String accountType;
    @Column(name = "dob")
    private Date dob;
    @NotEmpty(message = "Please provide your Name!")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Please provide your PAN card number!")
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
    @NotEmpty(message = "Email cannot be blank!")
    @Column(name = "email")
    private String email;
    @NotEmpty(message = "Phone not cannot be blank!")
    @Column(name = "phone")
    private String phone;
    @Column(name = "applicationId")
    private String applicationId;
    @Column(name = "submittedOn")
    private Date submittedOn;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "id")
    private int id;
}
