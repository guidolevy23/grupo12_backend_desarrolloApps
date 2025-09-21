package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.dto.auth.LoginRequest;
import com.uade.tpo.gimnasio.dto.auth.LoginResponse;
import com.uade.tpo.gimnasio.dto.auth.RegisterRequest;
import com.uade.tpo.gimnasio.dto.auth.RegisterResponse;

public interface AuthService {

  LoginResponse login(LoginRequest request);
  RegisterResponse register(RegisterRequest request);
}