package com.sojourn.sojourn.service;

import com.sojourn.sojourn.exceptions.UserAlreadyExistsException;
import com.sojourn.sojourn.models.DataObject;
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
import java.util.Map;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectService objectService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.loadUserByUserName(username);
    }

    public User createUserWith(UserSignUpRequest userSignUpRequest) throws UserAlreadyExistsException {
        if (userRepository.loadUserByUserName(userSignUpRequest.getUsername()) != null)
            throw new UserAlreadyExistsException(userSignUpRequest.getUsername());
        return userRepository.save(
                User.builder()
                        .username(userSignUpRequest.getUsername())
                        .password(passwordEncoder.encode(userSignUpRequest.getPassword()))
                        .userRoles(List.of(UserRoles.USER)).build()
        );
    }

    public List<DataObject> getAllUserData(String userAccessing) {
        return objectService.getAllUserData(userAccessing);
    }

    public void insertDataObject(Map<String, Object> dataObject, String userAccessing) {
        objectService.createDataObject(userAccessing, dataObject);
    }
}
