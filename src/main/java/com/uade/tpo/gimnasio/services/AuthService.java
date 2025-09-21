package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.dto.auth.LoginRequest;
import com.uade.tpo.gimnasio.dto.auth.LoginResponse;

public interface AuthService {

  LoginResponse login(LoginRequest request);
}