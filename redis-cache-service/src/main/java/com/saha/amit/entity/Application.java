package com.saha.amit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@RedisHash("Application")
public class Application implements Serializable {
    @Id
    private String applicationId;
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
    private String emailOtp;
    private String textOtp;
    private String attachment1Name;
    private String attachment2Name;
}
