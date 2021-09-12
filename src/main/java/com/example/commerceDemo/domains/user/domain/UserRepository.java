package com.example.commerceDemo.domains.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //소셜 로그인으로 반환 되는 값 중 이메일을 통해 이미 새성된 사용자인지 신규 유저인지 확인 하기 위한 메소드
    Optional<UserEntity> findByEmail(String Email);
}
