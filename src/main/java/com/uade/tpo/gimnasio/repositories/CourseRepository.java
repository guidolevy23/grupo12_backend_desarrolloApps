package com.uade.tpo.gimnasio.repositories;

import com.uade.tpo.gimnasio.models.entity.Course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ADMIN')")
@RepositoryRestResource(collectionResourceRel = "courses", path = "courses")
public interface CourseRepository extends CrudRepository<Course, Long>, PagingAndSortingRepository<Course, Long> {

    @RestResource(path = "byName", rel = "byName")
    Page<Course> findByNameIsContaining(@Param("name") String name, Pageable p);
}