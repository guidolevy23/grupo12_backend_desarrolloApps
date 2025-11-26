package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.qr.QRCodeDataDTO;
import com.uade.tpo.gimnasio.models.entity.Course;
import com.uade.tpo.gimnasio.repositories.CourseRepository;
import com.uade.tpo.gimnasio.services.QRCodeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/qr")
public class QRCodeController {
    
    private final QRCodeService qrCodeService;
    private final CourseRepository courseRepository;
    
    public QRCodeController(QRCodeService qrCodeService, CourseRepository courseRepository) {
        this.qrCodeService = qrCodeService;
        this.courseRepository = courseRepository;
    }
    
    @GetMapping("/courses/{courseId}/generate")
    public ResponseEntity<QRCodeDataDTO> generateQRData(@PathVariable Long courseId) {
        // Verify course exists
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
        
        // Generate session ID based on course and current date
        String sessionId = generateSessionId(course);
        
        QRCodeDataDTO qrData = qrCodeService.generateQRCodeData(courseId, sessionId);
        return ResponseEntity.ok(qrData);
    }
    
    @GetMapping("/courses/{courseId}/image")
    public ResponseEntity<byte[]> generateQRImage(@PathVariable Long courseId) {
        try {
            // Verify course exists
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
            
            // Generate session ID and QR data
            String sessionId = generateSessionId(course);
            QRCodeDataDTO qrData = qrCodeService.generateQRCodeData(courseId, sessionId);
            
            // Generate QR code image
            byte[] qrImage = qrCodeService.generateQRCodeImage(qrData);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(qrImage.length);
            
            return new ResponseEntity<>(qrImage, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating QR code image");
        }
    }
    
    @GetMapping("/courses/{courseId}/session/{sessionId}/image")
    public ResponseEntity<byte[]> generateQRImageWithSession(
            @PathVariable Long courseId, 
            @PathVariable String sessionId) {
        try {
            // Verify course exists
            courseRepository.findById(courseId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Course not found"));
            
            QRCodeDataDTO qrData = qrCodeService.generateQRCodeData(courseId, sessionId);
            byte[] qrImage = qrCodeService.generateQRCodeImage(qrData);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentLength(qrImage.length);
            headers.set("Content-Disposition", "inline; filename=qr-course-" + courseId + ".png");
            
            return new ResponseEntity<>(qrImage, headers, HttpStatus.OK);
            
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error generating QR code image");
        }
    }
    
    private String generateSessionId(Course course) {
        // Generate a unique session ID based on course and current date
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmm");
        return "COURSE-" + course.getId() + "-" + now.format(formatter);
    }
}
