package com.uade.tpo.gimnasio.services;



public interface AuthService {
  /** Solicita/genera y envía un OTP al email. */
  void requestOtp(String email);

  /** Verifica el OTP y devuelve un access token (JWT o similar). */
  String verifyOtp(String email, String otp);
}