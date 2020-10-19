package com.ryanthompson123.bpdtsapitest.service;

import com.ryanthompson123.bpdtsapitest.model.User;

import java.util.List;

public interface GetUserService {
    List<User> getUsersInLondon();
    List<User> getUsersNearLondon();
}
