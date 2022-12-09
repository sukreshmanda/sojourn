package com.sojourn.sojourn.controller;

import com.sojourn.sojourn.exceptions.AccessRestrictedException;
import com.sojourn.sojourn.models.DataObject;
import com.sojourn.sojourn.exceptions.DataNotFoundException;
import com.sojourn.sojourn.service.ObjectService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

@RestController
public class ObjectController {

    @Autowired
    private ObjectService objectService;

    Logger logger = LoggerFactory.getLogger(ObjectController.class);

    @GetMapping("/data")
    List<DataObject> getAllData(Principal principal) throws AccessRestrictedException {
        logger.info("tried to access all data {}", principal.getName());
        return objectService.getAllData(principal.getName());
    }

    @GetMapping("/data/{id}")
    DataObject getDataObject(Principal principal, @PathVariable("id") String id) throws DataNotFoundException, AccessRestrictedException {
        logger.info("tried to access data with id {} by the user {}", id, principal.getName());
        DataObject dataObjectById = objectService.getDataObjectById(principal.getName(), id);
        logger.info("successfully got data with id {}", id);
        return dataObjectById;
    }

    @PostMapping("/data")
    String putDataObject(Principal principal, @RequestBody Map map) {
        logger.info("tried to insert a data object by user {}", principal.getName());
        return objectService.createDataObject(principal.getName(), map);
    }
}
