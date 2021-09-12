package com.example.commerceDemo.domains.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column
    private String picture;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public UserEntity(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    // ==== 비즈니스 로직 ====
    public UserEntity update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }


    public void updateRole(Role role) {
        this.role = role;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
