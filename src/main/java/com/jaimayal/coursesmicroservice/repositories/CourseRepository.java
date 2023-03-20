package com.jaimayal.coursesmicroservice.repositories;

import com.jaimayal.coursesmicroservice.entities.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {
    
    @Transactional
    @Modifying
    @Query("delete from CourseUser c where c.userId = ?1")
    void deleteCourseUserById(Long courseUserId);
}
