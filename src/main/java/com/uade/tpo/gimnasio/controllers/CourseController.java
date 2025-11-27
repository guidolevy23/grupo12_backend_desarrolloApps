package com.uade.tpo.gimnasio.controllers;

import com.uade.tpo.gimnasio.dto.ResultDto;
import com.uade.tpo.gimnasio.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping("/{id}/cancel")
    public ResponseEntity<ResultDto> cancel(@PathVariable Long id) {
        courseService.cancel(id);
        return ResponseEntity.ok(new ResultDto("OK", "Clase cancelada"));
    }
}
