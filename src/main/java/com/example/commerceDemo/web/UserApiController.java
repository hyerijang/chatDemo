package com.example.commerceDemo.web;

import com.example.commerceDemo.service.UserService;
import com.example.commerceDemo.web.dto.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @GetMapping("/api/v1/users/{id}")
    public UserResponseDto findByID(@PathVariable Long id) {
        return userService.findById(id);
    }


}
