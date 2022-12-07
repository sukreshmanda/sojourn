package com.sojourn.sojourn.controller;

import com.sojourn.sojourn.models.DataObject;
import com.sojourn.sojourn.exceptions.DataNotFoundException;
import com.sojourn.sojourn.service.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class ObjectController {

    @Autowired
    private ObjectService objectService;

    @GetMapping("/data")
    @CrossOrigin(origins = "http://localhost:3000")
    List<DataObject> getAllData(){
        return objectService.getAllData();
    }

    @GetMapping("/data/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    DataObject getDataObject(@PathVariable("id") String id) throws DataNotFoundException {
       return objectService.getDataObjectById(id);
    }

    @PostMapping("/data")
    @CrossOrigin(origins = "http://localhost:3000")
    String putDataObject(@RequestBody Map map){
        return objectService.createDataObject(map);
    }
}
