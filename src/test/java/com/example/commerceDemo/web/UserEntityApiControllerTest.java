package com.example.commerceDemo.web;

import com.example.commerceDemo.domains.user.domain.Role;
import com.example.commerceDemo.domains.user.domain.UserEntity;
import com.example.commerceDemo.domains.user.domain.UserRepository;
import com.example.commerceDemo.web.dto.user.UserResponseDto;
import com.example.commerceDemo.web.dto.user.UserUpdateRoleDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserEntityApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser(roles = "USER")
    void ID로_유저_조회() throws Exception {
        //given
        String name = "username";
        UserEntity savedUserEntity = UserEntity.builder()
                .name(name)
                .email("email")
                .picture("picture url")
                .role(Role.GUEST)
                .build();

        Long userId = userRepository.save(savedUserEntity).getId();

        //when
        String url = "http://localhost:" + port + "/api/v1/users/" + userId;

        MvcResult result = mvc.perform(get(url))
                .andExpect(status().isOk()).andReturn();

        //then
        String content = result.getResponse().getContentAsString();
        UserResponseDto responseDto = new ObjectMapper().readValue(content, UserResponseDto.class);

        assertThat(savedUserEntity.getName()).isEqualTo(responseDto.getName());
        assertThat(savedUserEntity.getEmail()).isEqualTo(responseDto.getEmail());

    }


    @Test
    @WithMockUser(roles = "USER")
    void 권한_변경_테스트() throws Exception {
        //given
        String name = "username";
        UserEntity savedUserEntity = UserEntity.builder()
                .name(name)
                .email("email")
                .picture("picture url")
                .role(Role.GUEST)
                .build();

        Long userId = userRepository.save(savedUserEntity).getId();

        //when
        String url = "http://localhost:" + port + "/api/v1/users/" + userId + "/role/change";


        UserUpdateRoleDto updateRoleDto = UserUpdateRoleDto.builder().role(Role.USER).build();

        HttpEntity<UserUpdateRoleDto> requestEntity = new HttpEntity<>((updateRoleDto));
        mvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(updateRoleDto)))
                .andExpect(status().isOk());

        //then
        savedUserEntity = userRepository.findById(userId).get();
        assertThat(savedUserEntity.getRole()).isEqualTo(Role.USER);

    }
}