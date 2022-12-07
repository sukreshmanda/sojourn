package com.sojourn.sojourn.service;

import com.sojourn.sojourn.models.User;
import com.sojourn.sojourn.models.UserRoles;
import com.sojourn.sojourn.models.UserSignUpRequest;
import com.sojourn.sojourn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username tried to login " + username);
        User user = userRepository.loadUserByUserName(username);
        System.out.println(user);
        return user;
    }

    public boolean checkIfUserExists(String username) {
        if (userRepository.loadUserByUserName(username) != null) return true;
        return false;
    }

    public void createUserWith(UserSignUpRequest userSignUpRequest) {
        userRepository.save(
                User.builder()
                        .username(userSignUpRequest.getUsername())
                        .password(passwordEncoder.encode(userSignUpRequest.getPassword()))
                        .userRoles(List.of(UserRoles.USER)).build()
        );
    }
}
