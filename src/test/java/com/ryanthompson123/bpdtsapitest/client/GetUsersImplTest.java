package com.ryanthompson123.bpdtsapitest.client;

import lombok.Setter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ryanthompson123.bpdtsapitest.mock.UserMockData;
import com.ryanthompson123.bpdtsapitest.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ConfigurationProperties(value = "codetest.users", ignoreUnknownFields = false)
@ExtendWith(MockitoExtension.class)
class GetUsersImplTest {

    private static final String LONDON = "London";

    @Setter
    private String apihost;

    @Autowired
    @Spy
    private GetUsersImpl client;

    @Test
    void allUsers() {
        int mockResultCount = 200;

        List<User> mockUsers = UserMockData.getUsers(mockResultCount);
        ResponseEntity<User[]> mockResponse = new ResponseEntity(mockUsers.stream().toArray(User[]::new),
                HttpStatus.OK);
        doReturn(mockResponse).when(client).getUsersFromAPI(apihost + "users");

        List<User> users = client.allUsers();

        assertEquals(mockResultCount, users.size());
    }

    @Test
    void usersByCity() {
        var city = LONDON;
        int mockResultCount = 20;

        List<User> mockUsers = UserMockData.getUsers(mockResultCount);
        ResponseEntity<User[]> mockResponse = new ResponseEntity(mockUsers.stream().toArray(User[]::new), HttpStatus.OK);
        doReturn(mockResponse).when(client).getUsersFromAPI(apihost + "city/" + city + "/users");

        List<User> users = client.usersByCity(city);

        assertEquals(mockResultCount, users.size());
    }
}