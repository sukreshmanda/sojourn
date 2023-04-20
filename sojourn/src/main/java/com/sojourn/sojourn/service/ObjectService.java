package com.sojourn.sojourn.service;

import com.sojourn.sojourn.exceptions.AccessRestrictedException;
import com.sojourn.sojourn.exceptions.DataNotFoundException;
import com.sojourn.sojourn.models.DataObject;
import com.sojourn.sojourn.models.User;
import com.sojourn.sojourn.models.UserRoles;
import com.sojourn.sojourn.repository.ObjectRepository;
import com.sojourn.sojourn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class ObjectService {
    private final ObjectRepository objectRepository;

    private final UserRepository userRepository;

    private final MessageDigest sha256;

    @Autowired
    public ObjectService(ObjectRepository objectRepository, UserRepository userRepository, MessageDigest sha256) {
        this.objectRepository = objectRepository;
        this.userRepository = userRepository;
        this.sha256 = sha256;
    }

    public DataObject getDataObjectById(String userAccessing, String id) throws DataNotFoundException, AccessRestrictedException {
        User user = userRepository.loadUserByUserName(userAccessing);
        DataObject dataObject = objectRepository.findById(id).orElseThrow(() -> new DataNotFoundException(id));
        if (dataObject.isAccessibleTo(userAccessing) || user.isAdmin(UserRoles.ADMIN))
            return dataObject;
        else throw new AccessRestrictedException(dataObject.getId(), userAccessing);
    }

    public String createDataObject(String userCreated, Map<String, Object> map) {
        String id = bytesToHex(sha256.digest(LocalDateTime.now().toString().getBytes(StandardCharsets.UTF_8)));
        DataObject dataObject = new DataObject(id, map, LocalDateTime.now(), List.of(userCreated));
        DataObject savedObject = objectRepository.save(dataObject);
        return savedObject.getId();
    }

    public List<DataObject> getAllData(String userAccessing) throws AccessRestrictedException {
        User user = userRepository.loadUserByUserName(userAccessing);
        if (user.getUserRoles().contains(UserRoles.ADMIN)) return objectRepository.findAll();
        else throw new AccessRestrictedException("All data", userAccessing);
    }

    public void deleteDataObject(String id) {
        objectRepository.deleteById(id);
    }

    /*helper methods*/
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public List<DataObject> getAllUserData(String userAccessing) {
        return objectRepository.findAll().stream().filter(item -> item.getAccessibleTo() != null && item.getAccessibleTo().contains(userAccessing)).collect(Collectors.toList());
    }
}
