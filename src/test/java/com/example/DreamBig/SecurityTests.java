package com.example.DreamBig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = {"USER"})
    public void givenUserRole_whenAccessPublicEndpoint_thenOk() throws Exception {
        mockMvc.perform(get("/api/session/visit"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"TRAINER"})
    public void givenTrainerRole_whenAccessTrainerEndpoint_thenOk() throws Exception {
        mockMvc.perform(get("/api/session/arrange"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void givenUserRole_whenAccessAdminEndpoint_thenForbidden() throws Exception {
        mockMvc.perform(get("/api/session/arrange"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void givenAdminRole_whenAccessAdminEndpoint_thenOk() throws Exception {
        mockMvc.perform(get("/api/secret"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoUser_whenAccessSecuredEndpoint_thenUnauthorized() throws Exception {
        mockMvc.perform(get("/api"))
                .andExpect(status().isUnauthorized());
    }
}