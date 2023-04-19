package com.sojourn.sojourn;

import com.sojourn.sojourn.models.DataObject;
import com.sojourn.sojourn.models.User;
import com.sojourn.sojourn.models.UserRoles;
import com.sojourn.sojourn.models.UserSignUpRequest;
import com.sojourn.sojourn.models.dto.auth.AuthenticationRequest;
import com.sojourn.sojourn.models.dto.auth.AuthenticationResponse;

import java.time.LocalDateTime;
import java.util.Collections;

public class Builder {
    public static DataObject.DataObjectBuilder dataObjectBuilder(){
        return DataObject.builder()
                .createdAt(LocalDateTime.now());
    }

    public static UserSignUpRequest.UserSignUpRequestBuilder userSignUpRequestBuilder(){
        return UserSignUpRequest.builder()
                .username("abcd")
                .password("xyz");
    }

    public static User.UserBuilder userBuilder(){
        return User.builder()
                .userRoles(Collections.singletonList(UserRoles.USER))
                .password("asdvfdcasdwqsdfregredscsde")
                .username("username");
    }
    public static AuthenticationRequest.AuthenticationRequestBuilder authenticationRequest(){
        return AuthenticationRequest.builder()
                .username("username")
                .password("sdfwegrtrewdxcasdfg");
    }
    public static AuthenticationResponse.AuthenticationResponseBuilder authenticationResponseBuilder(){
        return AuthenticationResponse.builder()
                .token("wdfergthynyhgfdasAWEFRGTYUFGCSERT");
    }
}
