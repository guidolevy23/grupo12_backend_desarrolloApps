package com.uade.tpo.gimnasio.dto;

import java.util.Objects;

public class OtpDto {
    String identifier;
    String otp;

    public OtpDto() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OtpDto otpDto = (OtpDto) o;
        return Objects.equals(identifier, otpDto.identifier) && Objects.equals(otp, otpDto.otp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, otp);
    }
}
