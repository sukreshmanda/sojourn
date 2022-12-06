package com.sojourn.sojourn.repository;

import com.sojourn.sojourn.entity.DataObject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Map;

public interface ObjectRepository extends MongoRepository<DataObject, String> {
}
