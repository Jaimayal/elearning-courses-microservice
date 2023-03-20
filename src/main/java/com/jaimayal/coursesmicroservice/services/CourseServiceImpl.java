package com.jaimayal.coursesmicroservice.services;

import com.jaimayal.coursesmicroservice.clients.UserMicroserviceClient;
import com.jaimayal.coursesmicroservice.dtos.UserDTO;
import com.jaimayal.coursesmicroservice.entities.Course;
import com.jaimayal.coursesmicroservice.entities.CourseUser;
import com.jaimayal.coursesmicroservice.exception.EntityNotFoundException;
import com.jaimayal.coursesmicroservice.exception.UserNotEnrolledException;
import com.jaimayal.coursesmicroservice.repositories.CourseRepository;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService, CourseUserService {
    
    private CourseRepository courseRepository;
    private UserMicroserviceClient userMicroserviceClient;
    
    @Override
    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found"));
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

    @Override
    public void enrollUser(Long courseId, Long userId) {
        boolean userExistsById = this.userExistsById(userId);
        if (!userExistsById) {
            throw new EntityNotFoundException("User not found");
        }

        Course course = this.getCourseById(courseId);
        course.addUserId(userId);
        courseRepository.save(course);
    }

    @Override
    public void unrollUser(Long courseId, Long userId) {
        boolean userExistsById = this.userExistsById(userId);
        if (!userExistsById) {
            throw new EntityNotFoundException("User not found");
        }
        
        Course course = this.getCourseById(courseId);
        
        boolean isUserEnrolled = course.containsUserId(userId);
        if (!isUserEnrolled) {
            throw new UserNotEnrolledException("User not enrolled in course");
        }
        
        course.removeUserId(userId);
        courseRepository.save(course);
    }

    @Override
    public Course getCourseByIdWithUsers(Long courseId) {
        Course course = this.getCourseById(courseId);
        List<Long> userIds = course.getUserIds().stream()
                .map(CourseUser::getUserId)
                .toList();
        List<UserDTO> users = userMicroserviceClient.getUsersByIdsIn(userIds);
        course.setUsers(users);
        return course;
    }

    private boolean userExistsById(Long userId) {
        try {
            UserDTO user = userMicroserviceClient.getUser(userId);
            return true;
        } catch (FeignException e) {
            return false;
        }
    }
}
