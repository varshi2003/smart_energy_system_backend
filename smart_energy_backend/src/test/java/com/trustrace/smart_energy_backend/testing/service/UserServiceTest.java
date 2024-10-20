//package com.trustrace.smart_energy_backend.testing.service;
//
//
//import static org.mockito.Mockito.any;
//
//import com.trustrace.smart_energy_backend.model.User;
//import com.trustrace.smart_energy_backend.repository.UserRepository;
//import com.trustrace.smart_energy_backend.service.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//class UserServiceTest {
//
//    @InjectMocks
//    private UserService userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testRegisterUser() {
//        User user = new User();
//        user.setUsername("newUser");
//        user.setPassword("password");
//
//        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
//        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
//            User savedUser = invocation.getArgument(0);
//            savedUser.setId(1L); // Simulate generated ID
//            savedUser.setCreatedAt(LocalDateTime.now());
//            return savedUser;
//        });
//
//        User registeredUser = userService.registerUser(user);
//
//        assertEquals("encodedPassword", registeredUser.getPassword());
//        assertEquals(1L, registeredUser.getId());
//        assertEquals(LocalDateTime.now().getDayOfYear(), registeredUser.getCreatedAt().getDayOfYear()); // Check timestamp
//    }
//}
