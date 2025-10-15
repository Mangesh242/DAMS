package com.dams.society.service;

import com.dams.society.dto.FlatOwnerRequest;
import com.dams.society.dto.FlatOwnerResponse;
import com.dams.society.entity.FlatOwner;
import com.dams.society.repository.FlatOwnerRepository;
import com.dams.society.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlatOwnerServiceTest {

    @Mock
    private FlatOwnerRepository flatOwnerRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private FlatOwnerService flatOwnerService;

    private FlatOwnerRequest flatOwnerRequest;
    private FlatOwner flatOwner;

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

        flatOwner = new FlatOwner();
        flatOwner.setId(1L);
        flatOwner.setUsername("testuser");
        flatOwner.setFirstName("John");
        flatOwner.setLastName("Doe");
        flatOwner.setEmail("john.doe@example.com");
        flatOwner.setPhoneNumber("1234567890");
        flatOwner.setFlatNumber("101");
        flatOwner.setWing("A");
        flatOwner.setFloor(1);
        flatOwner.setMaintenanceAmount(5000.0);
    }

    @Test
    void createFlatOwner_Success() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(flatOwnerRepository.existsByFlatNumber(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(flatOwnerRepository.save(any(FlatOwner.class))).thenReturn(flatOwner);

        FlatOwnerResponse response = flatOwnerService.createFlatOwner(flatOwnerRequest);

        assertNotNull(response);
        assertEquals("testuser", response.getUsername());
        assertEquals("John", response.getFirstName());
        assertEquals("101", response.getFlatNumber());
        verify(flatOwnerRepository, times(1)).save(any(FlatOwner.class));
    }

    @Test
    void createFlatOwner_UsernameExists_ThrowsException() {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> 
            flatOwnerService.createFlatOwner(flatOwnerRequest)
        );
        verify(flatOwnerRepository, never()).save(any(FlatOwner.class));
    }

    @Test
    void getFlatOwnerById_Success() {
        when(flatOwnerRepository.findById(1L)).thenReturn(Optional.of(flatOwner));

        FlatOwnerResponse response = flatOwnerService.getFlatOwnerById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("testuser", response.getUsername());
    }

    @Test
    void getFlatOwnerById_NotFound_ThrowsException() {
        when(flatOwnerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> 
            flatOwnerService.getFlatOwnerById(1L)
        );
    }
}
