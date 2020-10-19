package com.ryanthompson123.bpdtsapitest.client;

import com.ryanthompson123.bpdtsapitest.model.User;
import java.util.List;

public interface GetUsers {
    List<User> allUsers();
    List<User> usersByCity(String city);
}
