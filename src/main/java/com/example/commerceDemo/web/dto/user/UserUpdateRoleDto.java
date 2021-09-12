package com.example.commerceDemo.web.dto.user;

import com.example.commerceDemo.domains.user.domain.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRoleDto {

    private Role role;

    @Builder
    public UserUpdateRoleDto(Role role) {
        this.role = role;
    }
}
