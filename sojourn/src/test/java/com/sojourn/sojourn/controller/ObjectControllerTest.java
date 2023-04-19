package com.sojourn.sojourn.controller;


import com.sojourn.sojourn.exceptions.AccessRestrictedException;
import com.sojourn.sojourn.exceptions.DataNotFoundException;
import com.sojourn.sojourn.models.DataObject;
import com.sojourn.sojourn.service.ObjectService;
import org.junit.jupiter.api.Test;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static com.sojourn.sojourn.Builder.dataObjectBuilder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ObjectControllerTest {

    public static final ObjectService objectServiceMock = mock(ObjectService.class);
    public static final Principal principalMock = mock(Principal.class);
    public static final String DUMMY_USER = "DummyUser";
    public static final String FAKE_ID = "123";

    @Test
    public void shouldGetData() throws AccessRestrictedException {
        ObjectController objectController = new ObjectController(objectServiceMock);
        when(principalMock.getName()).thenReturn(DUMMY_USER);
        when(objectServiceMock.getAllData(DUMMY_USER)).thenReturn(Collections.singletonList(
                dataObjectBuilder().build()
        ));

        List<DataObject> allData = objectController.getAllData(principalMock);

        assertEquals(1, allData.size());
    }

    @Test
    public void shouldGetDataObject() throws AccessRestrictedException, DataNotFoundException {
        ObjectController objectController = new ObjectController(objectServiceMock);
        when(principalMock.getName()).thenReturn(DUMMY_USER);
        when(objectServiceMock.getDataObjectById(DUMMY_USER, FAKE_ID)).thenReturn(dataObjectBuilder().id(FAKE_ID).build());

        DataObject dataObject = objectController.getDataObject(principalMock, FAKE_ID);

        assertEquals(FAKE_ID, dataObject.getId());
    }

    @Test
    public void shouldPutDataObject() {
        ObjectController objectController = new ObjectController(objectServiceMock);
        when(principalMock.getName()).thenReturn(DUMMY_USER);
        when(objectServiceMock.createDataObject(DUMMY_USER, new HashMap<>())).thenReturn(FAKE_ID);

        String idGenerated = objectController.putDataObject(principalMock, new HashMap<>());

        assertEquals(FAKE_ID, idGenerated);
    }
}