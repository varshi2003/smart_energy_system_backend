//package com.trustrace.smart_energy_backend.testing.controller;
//
//
//
//import com.trustrace.smart_energy_backend.controller.UserController;
//import com.trustrace.smart_energy_backend.model.User;
//import com.trustrace.smart_energy_backend.repository.UserRepository;
//import com.trustrace.smart_energy_backend.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//class UserControllerTest {
//
//    @InjectMocks
//    private UserController userController;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Mock
//    private UserService userService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testRegisterUser_UsernameExists() {
//        User user = new User();
//        user.setUsername("existingUser");
//        user.setPassword("password");
//
//        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
//
//        ResponseEntity<String> response = userController.registerUser(user);
//
//        assertEquals(400, response.getStatusCodeValue());
//        assertEquals("Username already exists.", response.getBody());
//    }
//
//    @Test
//    void testRegisterUser_Success() {
//        User user = new User();
//        user.setUsername("newUser");
//        user.setPassword("password");
//        user.setRoles(null); // No role provided
//
//        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
//        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
//        when(userRepository.save(any(User.class))).thenReturn(user);
//
//        ResponseEntity<String> response = userController.registerUser(user);
//
//        assertEquals(200, response.getStatusCodeValue());
//        assertEquals("User registered successfully.", response.getBody());
//        verify(userRepository).save(any(User.class)); // Verify that save is called
//    }
//}
