package com.sojourn.sojourn.service;

import com.sojourn.sojourn.exceptions.AccessRestrictedException;
import com.sojourn.sojourn.exceptions.DataNotFoundException;
import com.sojourn.sojourn.models.DataObject;
import com.sojourn.sojourn.models.UserRoles;
import com.sojourn.sojourn.repository.ObjectRepository;
import com.sojourn.sojourn.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.security.MessageDigest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.sojourn.sojourn.Builder.dataObjectBuilder;
import static com.sojourn.sojourn.Builder.userBuilder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ObjectServiceTest {

    public static final ObjectRepository objectRepositoryMock = mock(ObjectRepository.class);
    public static final UserRepository userRepositoryMock = mock(UserRepository.class);
    public static final MessageDigest messageDigestMock = mock(MessageDigest.class);
    public static final String FAKE_USER = "FakeUser";
    public static final String FAKE_ID = "Fake_id";
    public static final String FAKE_DIGEST = "asdfgt56erfwerfgby654erv rtyytr";

    @Test
    void shouldGetDataObjectById() throws DataNotFoundException, AccessRestrictedException {
        ObjectService objectService = new ObjectService(objectRepositoryMock, userRepositoryMock, messageDigestMock);
        when(userRepositoryMock.loadUserByUserName(FAKE_USER)).thenReturn(userBuilder().username(FAKE_USER).build());
        when(objectRepositoryMock.findById(FAKE_ID)).thenReturn(
                Optional.ofNullable(dataObjectBuilder().accessibleTo(Collections.singletonList(FAKE_USER)).build()));

        DataObject dataObjectById = objectService.getDataObjectById(FAKE_USER, FAKE_ID);

        assertTrue(dataObjectById.getAccessibleTo().contains(FAKE_USER));
    }
    @Test
    void shouldThrowDataNotFoundExceptionWhenGetDataObjectByIdNotFound() {
        ObjectService objectService = new ObjectService(objectRepositoryMock, userRepositoryMock, messageDigestMock);
        when(userRepositoryMock.loadUserByUserName(FAKE_USER)).thenReturn(userBuilder().username(FAKE_USER).build());
        when(objectRepositoryMock.findById(FAKE_ID)).thenReturn(Optional.empty());

        assertThrows(DataNotFoundException.class, () -> objectService.getDataObjectById(FAKE_USER, FAKE_ID));
    }

    @Test
    void shouldThrowAccessRestrictedExceptionWhenGetDataObjectByIdNotAccessible() {
        ObjectService objectService = new ObjectService(objectRepositoryMock, userRepositoryMock, messageDigestMock);
        when(userRepositoryMock.loadUserByUserName(FAKE_USER)).thenReturn(userBuilder().username(FAKE_USER).build());
        when(objectRepositoryMock.findById(FAKE_ID)).thenReturn(
                Optional.ofNullable(dataObjectBuilder().accessibleTo(Collections.emptyList()).build()));

        assertThrows(AccessRestrictedException.class, () -> objectService.getDataObjectById(FAKE_USER, FAKE_ID));
    }
    @Test
    void shouldGetDataObjectByIdForAdminWhenDataIsNotAllowedToView() throws DataNotFoundException, AccessRestrictedException {
        ObjectService objectService = new ObjectService(objectRepositoryMock, userRepositoryMock, messageDigestMock);
        when(userRepositoryMock.loadUserByUserName(FAKE_USER)).thenReturn(userBuilder().userRoles(Collections.singletonList(UserRoles.ADMIN)).username(FAKE_USER).build());
        when(objectRepositoryMock.findById(FAKE_ID)).thenReturn(
                Optional.ofNullable(dataObjectBuilder().accessibleTo(Collections.emptyList()).build()));

        DataObject dataObjectById = objectService.getDataObjectById(FAKE_USER, FAKE_ID);

        assertNotNull(dataObjectById);
    }
    @Test
    void shouldCreateDataObject() {
        ObjectService objectService = new ObjectService(objectRepositoryMock, userRepositoryMock, messageDigestMock);
        when(objectRepositoryMock.save(any(DataObject.class))).thenReturn(dataObjectBuilder().id(FAKE_ID).build());
        when(messageDigestMock.digest(any())).thenReturn(FAKE_DIGEST.getBytes());

        String dataObjectId = objectService.createDataObject(FAKE_USER, new HashMap<>());

        assertEquals(dataObjectId, FAKE_ID);
    }

    @Test
    void shouldGetAllDataWhenAdminAccess() throws AccessRestrictedException {
        List<DataObject> emptyList = Collections.emptyList();
        ObjectService objectService = new ObjectService(objectRepositoryMock, userRepositoryMock, messageDigestMock);
        when(userRepositoryMock.loadUserByUserName(FAKE_USER)).thenReturn(userBuilder().username(FAKE_USER)
                .userRoles(Collections.singletonList(UserRoles.ADMIN)).build());
        when(objectRepositoryMock.findAll()).thenReturn(emptyList);

        List<DataObject> allData = objectService.getAllData(FAKE_USER);

        assertEquals(allData, emptyList);
    }
    @Test
    void shouldThrowAccessRestrictedExceptionGetAllDataAccessedByNonAdmin() throws AccessRestrictedException {
        List<DataObject> emptyList = Collections.emptyList();
        ObjectService objectService = new ObjectService(objectRepositoryMock, userRepositoryMock, messageDigestMock);
        when(userRepositoryMock.loadUserByUserName(FAKE_USER)).thenReturn(userBuilder().username(FAKE_USER).build());
        when(objectRepositoryMock.findAll()).thenReturn(emptyList);

        assertThrows(AccessRestrictedException.class, () -> objectService.getAllData(FAKE_USER));

    }

    @Test
    void deleteDataObject() {
    }

    @Test
    void getAllUserData() {
    }
}