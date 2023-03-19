package com.jaimayal.coursesmicroservice.controllers;

import com.jaimayal.coursesmicroservice.entities.Course;
import com.jaimayal.coursesmicroservice.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    
    private CourseService courseService;
    
    @GetMapping("")
    public ResponseEntity<?> getCourses() {
        List<Course> courses = courseService.getCourses();
        return ResponseEntity.ok(courses);
    }
    
    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable Long courseId) {
        Course course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }
    
    @PostMapping("")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        URI uri = URI.create("http://localhost:8080/api/v1/users/" + createdCourse.getId());
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId,
                                          @RequestBody Course course) {
        Course updatedCourse = courseService.updateCourse(courseId, course);
        return ResponseEntity.ok(updatedCourse);
    }
    
    @PatchMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId,
                                          @RequestBody Map<String, String> updates) {
        Course updatedCourse = courseService.updateCourse(courseId, updates);
        return ResponseEntity.ok(updatedCourse);
    }
    
    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

}
