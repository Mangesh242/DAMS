package com.dams.society.controller;

import com.dams.society.dto.FlatOwnerRequest;
import com.dams.society.dto.FlatOwnerResponse;
import com.dams.society.service.FlatOwnerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlatOwnerController.class)
@AutoConfigureMockMvc(addFilters = false)
class FlatOwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FlatOwnerService flatOwnerService;

    private FlatOwnerRequest flatOwnerRequest;
    private FlatOwnerResponse flatOwnerResponse;

    @BeforeEach
    void setUp() {
        flatOwnerRequest = new FlatOwnerRequest();
        flatOwnerRequest.setUsername("testuser");
        flatOwnerRequest.setPassword("password123");
        flatOwnerRequest.setFirstName("John");
        flatOwnerRequest.setLastName("Doe");
        flatOwnerRequest.setEmail("john.doe@example.com");
        flatOwnerRequest.setPhoneNumber("1234567890");
        flatOwnerRequest.setFlatNumber("101");
        flatOwnerRequest.setWing("A");
        flatOwnerRequest.setFloor(1);
        flatOwnerRequest.setMaintenanceAmount(5000.0);

        flatOwnerResponse = new FlatOwnerResponse();
        flatOwnerResponse.setId(1L);
        flatOwnerResponse.setUsername("testuser");
        flatOwnerResponse.setFirstName("John");
        flatOwnerResponse.setLastName("Doe");
        flatOwnerResponse.setEmail("john.doe@example.com");
        flatOwnerResponse.setPhoneNumber("1234567890");
        flatOwnerResponse.setFlatNumber("101");
        flatOwnerResponse.setWing("A");
        flatOwnerResponse.setFloor(1);
        flatOwnerResponse.setMaintenanceAmount(5000.0);
        flatOwnerResponse.setActive(true);
    }

    @Test
    void createFlatOwner_Success() throws Exception {
        when(flatOwnerService.createFlatOwner(any(FlatOwnerRequest.class)))
                .thenReturn(flatOwnerResponse);

        mockMvc.perform(post("/api/flat-owners")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(flatOwnerRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.flatNumber").value("101"));
    }

    @Test
    void getFlatOwnerById_Success() throws Exception {
        when(flatOwnerService.getFlatOwnerById(1L)).thenReturn(flatOwnerResponse);

        mockMvc.perform(get("/api/flat-owners/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void getAllFlatOwners_Success() throws Exception {
        List<FlatOwnerResponse> flatOwners = Arrays.asList(flatOwnerResponse);
        when(flatOwnerService.getAllFlatOwners()).thenReturn(flatOwners);

        mockMvc.perform(get("/api/flat-owners"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("testuser"));
    }

    @Test
    void updateFlatOwner_Success() throws Exception {
        when(flatOwnerService.updateFlatOwner(eq(1L), any(FlatOwnerRequest.class)))
                .thenReturn(flatOwnerResponse);

        mockMvc.perform(put("/api/flat-owners/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(flatOwnerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"));
    }

    @Test
    void deleteFlatOwner_Success() throws Exception {
        mockMvc.perform(delete("/api/flat-owners/1"))
                .andExpect(status().isNoContent());
    }
}
