package com.example.commerceDemo.common.config.auth.dto;

import com.example.commerceDemo.domains.user.domain.UserEntity;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(UserEntity userEntity) {
        this.name = userEntity.getName();
        this.email = userEntity.getEmail();
        this.picture = userEntity.getPicture();
    }
}