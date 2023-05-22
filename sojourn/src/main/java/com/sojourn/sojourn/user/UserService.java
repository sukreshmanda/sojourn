package com.sojourn.sojourn.user;

import com.sojourn.sojourn.exceptions.UserAlreadyExistsException;
import com.sojourn.sojourn.object.DataObject;
import com.sojourn.sojourn.object.ObjectService;
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

    private final UserRepository userRepository;

    private final ObjectService objectService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, ObjectService objectService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.objectService = objectService;
        this.passwordEncoder = passwordEncoder;
    }

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

    public String insertDataObject(Map<String, Object> dataObject, String userAccessing) {
        return objectService.createDataObject(userAccessing, dataObject);
    }
}
