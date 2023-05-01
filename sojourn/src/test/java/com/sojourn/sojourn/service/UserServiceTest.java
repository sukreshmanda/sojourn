package com.sojourn.sojourn.service;

import com.sojourn.sojourn.models.User;
import com.sojourn.sojourn.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    public static final String FAKE_USERNAME = "fake_username";

    @Test
    void shouldLoadUserByUsername() {
        User user = new User();
        UserRepository userRepository = mock(UserRepository.class);
        ObjectService objectService = mock(ObjectService.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        when(userRepository.loadUserByUserName(FAKE_USERNAME)).thenReturn(user);
        UserService userService = new UserService(userRepository, objectService, passwordEncoder);

        UserDetails userDetails = userService.loadUserByUsername(FAKE_USERNAME);

        assertEquals(user, userDetails);
    }

    @Test
    void createUserWith() {
    }

    @Test
    void getAllUserData() {
    }

    @Test
    void insertDataObject() {
    }
}