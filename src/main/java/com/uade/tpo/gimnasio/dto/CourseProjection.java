package com.uade.tpo.gimnasio.dto;

import com.uade.tpo.gimnasio.models.entity.Course;
import com.uade.tpo.gimnasio.models.entity.Sede;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDateTime;

@Projection(name = "courseWithBranch", types = Course.class)
public interface CourseProjection {

    Long getId();
    String getName();
    String getDescription();
    String getProfessor();
    LocalDateTime getStartsAt();
    LocalDateTime getEndsAt();

    // Embed the child entity directly
    Sede getBranch();
}