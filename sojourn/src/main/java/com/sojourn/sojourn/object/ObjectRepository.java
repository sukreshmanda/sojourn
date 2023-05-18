package com.sojourn.sojourn.object;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ObjectRepository extends MongoRepository<DataObject, String> {
}
