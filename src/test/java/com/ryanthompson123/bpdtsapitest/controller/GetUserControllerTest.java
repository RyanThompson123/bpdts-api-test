package com.ryanthompson123.bpdtsapitest.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import com.ryanthompson123.bpdtsapitest.mock.UserMockData;
import com.ryanthompson123.bpdtsapitest.model.User;
import com.ryanthompson123.bpdtsapitest.service.GetUserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    GetUserService getUserService;

    GetUserController getUserController;

    @BeforeEach
    void beforeEach() {
        getUserController = new GetUserController(getUserService);
    }

    @Test
    void getLondonUsers_shouldReturnUserInAndNearLondon() {
        List<User> usersInLondon = UserMockData.getUsers(20);
        List<User> usersNearLondon = UserMockData.getUsersWithFourNearLondon();
        int expectedSize = usersInLondon.size() + usersNearLondon.size();

        when(getUserService.getUsersInLondon()).thenReturn(usersInLondon);
        when(getUserService.getUsersNearLondon()).thenReturn(usersNearLondon);

        ResponseEntity<List<User>> response = getUserController.getLondonUsers();

        verify(getUserService, times(1)).getUsersInLondon();
        verify(getUserService, times(1)).getUsersNearLondon();

        assertEquals(expectedSize, response.getBody().size());
    }
}
