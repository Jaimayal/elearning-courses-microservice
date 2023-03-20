package com.jaimayal.coursesmicroservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jaimayal.coursesmicroservice.dtos.UserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;
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
    @JoinColumn(name = "course_id")
    private Collection<CourseUser> userIds;
    
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
