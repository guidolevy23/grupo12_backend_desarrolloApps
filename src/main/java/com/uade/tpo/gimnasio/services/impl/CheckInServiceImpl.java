package com.uade.tpo.gimnasio.services.impl;

import com.uade.tpo.gimnasio.dto.checkin.CheckInRequestDTO;
import com.uade.tpo.gimnasio.dto.checkin.CheckInResponseDTO;
import com.uade.tpo.gimnasio.models.entity.Asistencia;
import com.uade.tpo.gimnasio.models.entity.Reserva;
import com.uade.tpo.gimnasio.models.entity.User;
import com.uade.tpo.gimnasio.repositories.AsistenciaRepository;
import com.uade.tpo.gimnasio.repositories.ReservaRepository;
import com.uade.tpo.gimnasio.repositories.UserRepository;
import com.uade.tpo.gimnasio.services.CheckInService;
import com.uade.tpo.gimnasio.services.QRCodeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class CheckInServiceImpl implements CheckInService {
    
    private final ReservaRepository reservaRepository;
    private final AsistenciaRepository asistenciaRepository;
    private final UserRepository userRepository;
    private final QRCodeService qrCodeService;
    
    public CheckInServiceImpl(ReservaRepository reservaRepository,
                             AsistenciaRepository asistenciaRepository,
                             UserRepository userRepository,
                             QRCodeService qrCodeService) {
        this.reservaRepository = reservaRepository;
        this.asistenciaRepository = asistenciaRepository;
        this.userRepository = userRepository;
        this.qrCodeService = qrCodeService;
    }
    
    @Override
    @Transactional
    public CheckInResponseDTO processCheckIn(CheckInRequestDTO request, String userEmail) {
        // 1. Get authenticated user
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));
        
        // 2. Validate reservation exists and belongs to user
        Reserva reserva = reservaRepository.findById(request.reservaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
        
        if (!reserva.getUsuario().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found");
        }
        
        // 3. Validate reservation status
        if (reserva.getEstado() != Reserva.Estado.CONFIRMADA) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This reservation is not active");
        }
        
        // 4. Check for duplicate check-in
        if (reserva.isCheckedIn()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Check-in already completed for this reservation");
        }
        
        // Alternative check using asistencias table
        if (asistenciaRepository.existsByUsuario_IdAndReserva_Id(user.getId(), reserva.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Check-in already completed for this reservation");
        }
        
        // 5. Validate QR code
        if (!qrCodeService.validateQRCode(request.qrCode(), reserva.getCourse().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid or expired QR code");
        }
        
        // 6. Validate time window (15 minutes before to 15 minutes after class start)
        validateCheckInTimeWindow(reserva);
        
        // 7. Create attendance record
        Asistencia asistencia = createAsistenciaRecord(user, reserva);
        
        // 8. Update reservation status
        reserva.setCheckedIn(true);
        reservaRepository.save(reserva);
        
        // 9. Return success response
        return new CheckInResponseDTO(
            true,
            "Check-in successful",
            asistencia.getId(),
            asistencia.getCheckInAt()
        );
    }
    
    private void validateCheckInTimeWindow(Reserva reserva) {
        LocalDateTime classStartTime = reserva.getCourse().getStartsAt();
        if (classStartTime == null) {
            // If no start time is set, allow check-in
            return;
        }
        
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime checkInWindowStart = classStartTime.minusMinutes(15);
        LocalDateTime checkInWindowEnd = classStartTime.plusMinutes(15);
        
        if (now.isBefore(checkInWindowStart)) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN, 
                "Check-in not yet available. Class starts at " + classStartTime
            );
        }
        
        if (now.isAfter(checkInWindowEnd)) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN, 
                "Check-in window has closed for this class"
            );
        }
    }
    
    private Asistencia createAsistenciaRecord(User user, Reserva reserva) {
        Asistencia asistencia = new Asistencia();
        asistencia.setUsuario(user);
        asistencia.setReserva(reserva);
        asistencia.setCheckInAt(Instant.now());
        
        return asistenciaRepository.save(asistencia);
    }
}
