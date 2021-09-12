package com.example.commerceDemo.web.dto.user;

import com.example.commerceDemo.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private Long id;
    private String name;
    private String email;
    private String picture;

    @Builder
    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
