package com.ryanthompson123.bpdtsapitest.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.ryanthompson123.bpdtsapitest.model.User;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class GetUsersImpl implements GetUsers {
    @Value ("${bpdts.api}")
    private String apihost;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    private RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public List<User> allUsers() {
        ResponseEntity<User[]> response = getUsersFromAPI(apihost + "users");
        User[] users = response.getBody();

        return Arrays.asList(users);
    }

    @Override
    public List<User> usersByCity(String city) {
        ResponseEntity<User[]> response = getUsersFromAPI(apihost + "city/" + city + "/users");
        User[] users = response.getBody();

        return Arrays.asList(users);
    }

    protected ResponseEntity<User[]> getUsersFromAPI(String url){
        return restTemplate.getForEntity(url, User[].class);
    }
}
