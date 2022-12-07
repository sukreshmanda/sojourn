package com.sojourn.sojourn.repository;

import com.sojourn.sojourn.models.DataObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ObjectRepository extends MongoRepository<DataObject, String> {
}
