package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.models.entity.User;

public interface OtpService {

    void createAndSend(User user);
    boolean validate(User user, String code);
}
