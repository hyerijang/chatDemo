package com.example.commerceDemo.domains.user.service;

import com.example.commerceDemo.domains.user.domain.UserEntity;
import com.example.commerceDemo.domains.user.domain.UserRepository;
import com.example.commerceDemo.web.dto.user.UserResponseDto;
import com.example.commerceDemo.web.dto.user.UserUpdateRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new UserResponseDto(entity);
    }

    @Transactional
    public Long updateRole(Long id, UserUpdateRoleDto userRoleDto) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        entity.updateRole(userRoleDto.getRole());

        return id;
    }
}
