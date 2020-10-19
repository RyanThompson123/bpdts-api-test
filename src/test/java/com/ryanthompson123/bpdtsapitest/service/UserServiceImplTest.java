package com.ryanthompson123.bpdtsapitest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ryanthompson123.bpdtsapitest.client.GetUsers;
import com.ryanthompson123.bpdtsapitest.mock.UserMockData;
import com.ryanthompson123.bpdtsapitest.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private GetUsers getUserClient;

    private GetUserService getUserService;

    @BeforeEach
    void beforeTests(){
        getUserService = new GetUserServiceImpl(getUserClient);
    }

    @Test
    void getUsersInLondon_shouldCallUserByCityOnce() {
        getUserService.getUsersInLondon();
        verify(getUserClient, times(1)).usersByCity("London");
    }

    @Test
    void getUsersNearLondon_shouldGetAllUsersWithNoneNearLondon() {
        when(getUserClient.allUsers()).thenReturn(UserMockData.getUsers(20));
        List<User> users = getUserService.getUsersNearLondon();

        assertEquals(0, users.size());
    }

    @Test
    void getUsersNearLondon_shouldGetAllUsersWithFourNearLondon() {
        when(getUserClient.allUsers()).thenReturn(UserMockData.getFourUsersNearLondon());
        List<User> users = getUserService.getUsersNearLondon();

        assertEquals(4, users.size());
    }
}