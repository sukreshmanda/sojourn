package com.sojourn.sojourn.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{username:'?0'}")
    User loadUserByUserName(String username);
}
