package com.incubyte.sweetshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubyte.sweetshop.dto.SweetRequest;
import com.incubyte.sweetshop.model.User;
import com.incubyte.sweetshop.repository.UserRepository;
import com.incubyte.sweetshop.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SweetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private String adminToken;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
        admin.setRole("ROLE_ADMIN");

        userRepository.save(admin);

        adminToken = "Bearer " + jwtUtil.generateToken("admin", "ROLE_ADMIN");
    }

    @Test
    void shouldCreateSweet_whenAdmin() throws Exception {

        SweetRequest request = new SweetRequest();
        request.setName("Gulab Jamun");
        request.setCategory("Indian");
        request.setPrice(20.0);
        request.setQuantity(50);

        mockMvc.perform(post("/api/sweets")
                        .header("Authorization", adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
