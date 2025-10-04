package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.LocalDateTime;
import java.util.List;

@PreAuthorize("hasRole('ADMIN')")
@RepositoryRestResource(collectionResourceRel = "courses", path = "courses")
public interface CourseRepository extends CrudRepository<Course, Long>, PagingAndSortingRepository<Course, Long> {

    // Buscar por nombre
    @RestResource(path = "byName", rel = "byName")
    Page<Course> findByNameIsContainingIgnoreCase(@Param("name") String name, Pageable p);

    // Buscar por profesor
    @RestResource(path = "byProfessor", rel = "byProfessor")
    Page<Course> findByProfessorIsContainingIgnoreCase(@Param("professor") String professor, Pageable p);

    // Buscar entre fechas de inicio
    @RestResource(path = "byDateBetween", rel = "byDateBetween")
    Page<Course> findByStartsAtBetween(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            Pageable p
    );


    List<Course> findByStartsAtBetween(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    );

        // Buscar por sede (branch)
    @RestResource(path = "byBranch", rel = "byBranch")
    Page<Course> findByBranchIsContainingIgnoreCase(
            @Param("branch") String branch,
            Pageable p
    );



}
