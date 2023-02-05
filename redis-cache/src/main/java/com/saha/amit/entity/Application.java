package com.saha.amit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Application")
public class Application implements Serializable {
    @Id
    private String applicationId;
    private int otp;
    private String name;
    private String pan;
}
