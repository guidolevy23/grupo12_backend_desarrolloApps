package com.uade.tpo.gimnasio.services;

import com.uade.tpo.gimnasio.dto.checkin.CheckInRequestDTO;
import com.uade.tpo.gimnasio.dto.checkin.CheckInResponseDTO;

public interface CheckInService {
    /**
     * Process check-in for a reservation
     */
    CheckInResponseDTO processCheckIn(CheckInRequestDTO request, String userEmail);
}
