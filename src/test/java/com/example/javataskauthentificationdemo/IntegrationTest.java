package com.example.javataskauthentificationdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void unauthorizedUser() throws Exception {
        mockMvc.perform(get("/api/helloVip"))
                .andExpect(status().isUnauthorized()); //or: is4xxClientError()
    }

    @Test
    @WithMockUser(username = "Karl")
    void authenticated() throws Exception {
        mockMvc.perform(get("/api/users/me"))
                .andExpect(content().string("Karl"));
    }

    @Test
    @WithMockUser(roles = "VIP")
    void authorizedRole() throws Exception {
        mockMvc.perform(get("/api/helloVip"))
                .andExpect(status().isOk()); //or: is2xxSuccessful()
    }

    @Test
    @WithMockUser(username = "Karl", roles = "VIP")
    void UserWithAuthorizedRole() throws Exception {
        mockMvc.perform(get("/api/helloVip"))
                .andExpect(status().isOk()); //or: is2xxSuccessful()
    }
}
