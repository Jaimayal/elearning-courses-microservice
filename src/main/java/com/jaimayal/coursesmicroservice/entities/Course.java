package com.jaimayal.coursesmicroservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jaimayal.coursesmicroservice.dtos.UserDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;
    private String description;
    private String author;
    private String category;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseUser> userIds;
    
    @Transient
    private List<UserDTO> users;
    
    public Course() {
        this.users = new ArrayList<>();
        this.userIds = new ArrayList<>();
    }
    
    public void addUserId(Long userId) {
        CourseUser courseUser = new CourseUser();
        courseUser.setUserId(userId);
        userIds.add(courseUser);
    }
    
    public void removeUserId(Long userId) {
        userIds.removeIf(courseUser -> courseUser.getUserId().equals(userId));
    }
    
    public boolean containsUserId(Long userId) {
        return userIds.stream()
                .anyMatch(courseUser -> courseUser.getUserId().equals(userId));
    }
}
