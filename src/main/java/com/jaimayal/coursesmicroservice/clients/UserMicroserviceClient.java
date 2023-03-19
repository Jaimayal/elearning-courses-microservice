package com.jaimayal.coursesmicroservice.clients;

import com.jaimayal.coursesmicroservice.dtos.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "users-microservice", url = "localhost:8081/api/v1/users")
public interface UserMicroserviceClient {
    
    @GetMapping("")
    List<UserDTO> getUsersByIdsIn(@RequestParam List<Long> userIds);

    @GetMapping("/{userId}")
    UserDTO getUser(@PathVariable Long userId);
}
