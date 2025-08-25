package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.dto.OtpDto;
import com.uade.tpo.gimnasio.models.entity.Otp;

public interface OtpService {

    Otp create(OtpDto dto);
    boolean validate(OtpDto dto);
}
