package com.jaimayal.coursesmicroservice.services;

import com.jaimayal.coursesmicroservice.entities.Course;
import com.jaimayal.coursesmicroservice.exception.CourseNotFoundException;
import com.jaimayal.coursesmicroservice.repositories.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {
    
    private CourseRepository courseRepository;
    
    @Override
    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course not found"));
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long courseId, Course course) {
        Course toUpdate = this.getCourseById(courseId);
        toUpdate.setName(course.getName());
        toUpdate.setDescription(course.getDescription());
        toUpdate.setAuthor(course.getAuthor());
        toUpdate.setCategory(course.getCategory());
        Course updatedCourse = courseRepository.save(toUpdate);
        return updatedCourse;
    }

    @Override
    public Course updateCourse(Long courseId, Map<String, String> updates) {
        Course toUpdate = this.getCourseById(courseId);
        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> toUpdate.setName(value);
                case "description" -> toUpdate.setDescription(value);
                case "author" -> toUpdate.setAuthor(value);
                case "category" -> toUpdate.setCategory(value);
            }
        });
        Course updatedCourse = courseRepository.save(toUpdate);
        return updatedCourse;
    }

    @Override
    public void deleteCourse(Long courseId) {
        Course toDelete = this.getCourseById(courseId);
        courseRepository.delete(toDelete);
    }
}
