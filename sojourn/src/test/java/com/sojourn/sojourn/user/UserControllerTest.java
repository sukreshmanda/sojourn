package com.sojourn.sojourn.user;

import com.sojourn.sojourn.auth.jwt.JWTUtil;
import com.sojourn.sojourn.exceptions.UserAlreadyExistsException;
import com.sojourn.sojourn.object.DataObject;
import com.sojourn.sojourn.user.User;
import com.sojourn.sojourn.user.UserController;
import com.sojourn.sojourn.user.UserSignUpRequest;
import com.sojourn.sojourn.auth.AuthenticationResponse;
import com.sojourn.sojourn.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.sojourn.sojourn.Builder.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {

    public static final UserService userServiceMock = mock(UserService.class);
    public static final AuthenticationManager mockAuthenticationManager = mock(AuthenticationManager.class);
    public static final JWTUtil JWTUtilMock = mock(JWTUtil.class);
    public static final Principal principal = mock(Principal.class);
    public static final String FAKE_USER_NAME = "FakeUserName";
    public static final String FAKE_ID = "Fake_ID";
    public static final String FAKE_TOKEN = "Fake_Token";

    @Test
    void shouldSignUp() throws UserAlreadyExistsException {
        UserController userController = new UserController(userServiceMock, mockAuthenticationManager, JWTUtilMock);
        UserSignUpRequest userSignUpRequest = userSignUpRequestBuilder().username(FAKE_USER_NAME).build();
        when(userServiceMock.createUserWith(userSignUpRequest)).thenReturn(userBuilder().username(FAKE_USER_NAME).build());

        String userName = userController.signUp(userSignUpRequest);

        assertEquals(FAKE_USER_NAME, userName);
    }

    @Test
    void shouldGetAllUserData() {
        UserController userController = new UserController(userServiceMock, mockAuthenticationManager, JWTUtilMock);
        List<DataObject> dataObjectList = Collections.singletonList(dataObjectBuilder().build());
        when(principal.getName()).thenReturn(FAKE_USER_NAME);
        when(userServiceMock.getAllUserData(FAKE_USER_NAME)).thenReturn(dataObjectList);

        List<DataObject> allUserData = userController.getAllUserData(principal);

        assertEquals(dataObjectList, allUserData);
    }

    @Test
    void shouldInsertUserData() {
        HashMap<String, Object> dataObject = new HashMap<>();
        UserController userController = new UserController(userServiceMock, mockAuthenticationManager, JWTUtilMock);
        when(principal.getName()).thenReturn(FAKE_USER_NAME);
        when(userServiceMock.insertDataObject(dataObject, FAKE_USER_NAME)).thenReturn(FAKE_ID);

        String insertedDataId = userController.insertUserData(principal, dataObject);

        assertEquals(FAKE_ID, insertedDataId);
    }

    @Test
    void shouldAuthenticate() {
        User user = userBuilder().build();
        UserController userController = new UserController(userServiceMock, mockAuthenticationManager, JWTUtilMock);
        AuthenticationResponse expectedAuthenticationResponse = authenticationResponseBuilder().token(FAKE_TOKEN).build();
        when(userServiceMock.loadUserByUsername(FAKE_USER_NAME)).thenReturn(user);
        when(JWTUtilMock.generateToken(user)).thenReturn(FAKE_TOKEN);

        AuthenticationResponse authenticationResponse = userController.authenticate(authenticationRequest().username(FAKE_USER_NAME).build());

        assertEquals(expectedAuthenticationResponse, authenticationResponse);
    }
}