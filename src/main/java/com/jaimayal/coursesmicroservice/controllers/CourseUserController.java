package com.jaimayal.coursesmicroservice.controllers;

import com.jaimayal.coursesmicroservice.entities.Course;
import com.jaimayal.coursesmicroservice.services.CourseUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/courses")
public class CourseUserController {
    
    private CourseUserService courseUserService;

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourseByIdWithUsers(@PathVariable Long courseId) {
        Course course = courseUserService.getCourseByIdWithUsers(courseId);
        return ResponseEntity.ok(course);
    }
    
    @PostMapping("/{courseId}/users/{userId}")
    public ResponseEntity<?> enrollUser(@PathVariable Long courseId,
                                        @PathVariable Long userId) {
        courseUserService.enrollUser(courseId, userId);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{courseId}/users/{userId}")
    public ResponseEntity<?> unrollUser(@PathVariable Long courseId,
                                        @PathVariable Long userId) {
        courseUserService.unrollUser(courseId, userId);
        return ResponseEntity.noContent().build();
    }
}
