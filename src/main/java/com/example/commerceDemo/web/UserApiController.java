package com.example.commerceDemo.web;

import com.example.commerceDemo.domains.user.service.UserService;
import com.example.commerceDemo.web.dto.user.UserResponseDto;
import com.example.commerceDemo.web.dto.user.UserUpdateRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @GetMapping("/api/v1/users/{id}")
    public UserResponseDto findByID(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/api/v1/users/{id}/role/change")
    public Long updateRole(@PathVariable Long id, @RequestBody UserUpdateRoleDto userRoleDto) {
        return userService.updateRole(id, userRoleDto);
    }

}
