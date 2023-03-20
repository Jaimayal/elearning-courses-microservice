package com.jaimayal.coursesmicroservice.services;

import com.jaimayal.coursesmicroservice.entities.Course;

public interface CourseUserService {
    
    Course getCourseByIdWithUsers(Long courseId);
    void enrollUser(Long courseId, Long userId);
    void unrollUser(Long courseId, Long userId);
}
