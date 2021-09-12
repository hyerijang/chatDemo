package com.example.commerceDemo.web;

import com.example.commerceDemo.domain.Role;
import com.example.commerceDemo.domain.User;
import com.example.commerceDemo.domain.UserRepository;
import com.example.commerceDemo.web.dto.user.UserResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApiControllerTest {

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
        User savedUser = User.builder()
                .name(name)
                .email("email")
                .picture("picture url")
                .role(Role.GUEST)
                .build();

        Long userId = userRepository.save(savedUser).getId();

        //when
        String url = "http://localhost:" + port + "/api/v1/users/" + userId;

        MvcResult result = mvc.perform(get(url))
                .andExpect(status().isOk()).andReturn();

        //then
        String content = result.getResponse().getContentAsString();
        UserResponseDto responseDto = new ObjectMapper().readValue(content, UserResponseDto.class);

        assertThat(savedUser.getName()).isEqualTo(responseDto.getName());
        assertThat(savedUser.getEmail()).isEqualTo(responseDto.getEmail());

    }
}