package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.dto.OtpDto;

public interface AuthService {

  boolean createAndSendOtp(OtpDto dto);
}