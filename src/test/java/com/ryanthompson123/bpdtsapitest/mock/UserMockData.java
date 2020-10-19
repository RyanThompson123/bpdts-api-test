package com.ryanthompson123.bpdtsapitest.mock;

import com.ryanthompson123.bpdtsapitest.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserMockData {
    public static List<User> getUsers(int count){
        List<User> users = new ArrayList<>(count);

        for (int i = 0; i < count; i++) {
            User user = User.builder()
                    .id(i+1)
                    .firstName("Test" + (i+1))
                    .lastName("User" + (i+1))
                    .latitude(i%180)
                    .longitude(i%180)
                    .build();
            users.add(user);
        }

        return users;
    }

    public static List<User> getFourUsersNearLondon(){
        int recordCount = 12;

        return Stream.concat(getUsersWithFourNearLondon().stream(),getUsers(recordCount).stream())
                    .collect(Collectors.toList());
    }

    public static List<User> getUsersWithFourNearLondon(){
        List<User> users = new ArrayList<>();

        users.add(User.builder()
                    .id(1)
                    .firstName("Test1")
                    .lastName("User1")
                    .latitude(51.6074)
                    .longitude(-0.2278)
                    .build());

        users.add(User.builder()
                    .id(2)
                    .firstName("Test2")
                    .lastName("User2")
                    .latitude(51.7074)
                    .longitude(-0.3278)
                    .build());

        users.add(User.builder()
                    .id(2)
                    .firstName("Test3")
                    .lastName("User3")
                    .latitude(51.7144)
                    .longitude(-0.3338)
                    .build());

		users.add(User.builder()
                    .id(2)
                    .firstName("Test4")
                    .lastName("User4")
                    .latitude(51.7224)
                    .longitude(-0.3178)
                    .build());

        return users;
    }
}
