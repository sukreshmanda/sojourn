package com.sojourn.sojourn.service;

import com.sojourn.sojourn.entity.DataObject;
import com.sojourn.sojourn.exceptions.DataNotFoundException;
import com.sojourn.sojourn.repository.ObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
public class ObjectService {
    @Autowired
    private ObjectRepository objectRepository;

    @Autowired
    private MessageDigest sha256;

    public DataObject getDataObjectById(String id) throws DataNotFoundException {
        return objectRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    public String createDataObject(Map map) {
        String id = bytesToHex(sha256.digest(LocalDateTime.now().toString().getBytes(StandardCharsets.UTF_8)));
        DataObject dataObject = new DataObject(id, map, LocalDateTime.now());
        DataObject savedObject = objectRepository.save(dataObject);
        return savedObject.getId();
    }

    public List<DataObject> getAllData() {
        return objectRepository.findAll();
    }

    public void deleteDataObject(String id) {
        objectRepository.deleteById(id);
    }
    /*helper methods*/
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
