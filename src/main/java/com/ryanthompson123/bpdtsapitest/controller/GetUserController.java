package com.ryanthompson123.bpdtsapitest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ryanthompson123.bpdtsapitest.model.User;
import com.ryanthompson123.bpdtsapitest.service.GetUserService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequestMapping("/get-users")
@RestController
public class GetUserController {
    private GetUserService getUserService;

    public GetUserController(GetUserService getUserService) {
        this.getUserService = getUserService;
    }

    @GetMapping("/get-users/london")
    public ResponseEntity<List<User>> getLondonUsers(){
        List<User> usersInLondon = getUserService.getUsersInLondon();
        List<User> usersNearLondon = getUserService.getUsersNearLondon();
        List<User> londonUsers = Stream.concat(usersInLondon.stream(), usersNearLondon.stream())
                .distinct()
                .collect(Collectors.toList());

        return new ResponseEntity<>(londonUsers, HttpStatus.OK);
    }
}
