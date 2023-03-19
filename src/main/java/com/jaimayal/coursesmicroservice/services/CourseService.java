package com.jaimayal.coursesmicroservice.services;

import com.jaimayal.coursesmicroservice.entities.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    
    List<Course> getCourses();
    Course getCourseById(Long courseId);
    Course createCourse(Course course);
    Course updateCourse(Long courseId, Course course);
    Course updateCourse(Long courseId, Map<String, String> updates);
    void deleteCourse(Long courseId);
}
