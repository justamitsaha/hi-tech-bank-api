package com.saha.amit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OtpMailSenderDTO {
    public String email;
    public String otp;
    public String type;

}
