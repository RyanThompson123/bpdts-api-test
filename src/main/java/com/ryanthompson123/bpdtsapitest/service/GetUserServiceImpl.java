package com.ryanthompson123.bpdtsapitest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.ryanthompson123.bpdtsapitest.client.GetUsers;
import com.ryanthompson123.bpdtsapitest.model.User;
import com.ryanthompson123.bpdtsapitest.utils.DistanceCalc;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GetUserServiceImpl implements GetUserService {
    // Asumption that LONDON_LATITUDE and LONDON_LONGITUDE are correct from google search
    public static final double LONDON_LATITUDE = 51.5074;
    public static final double LONDON_LONGITUDE = -0.1277;
    public static final double CLOSE_LONDON_DISTANCE = 60;

    private GetUsers getUsersClient;

    public GetUserServiceImpl(GetUsers usersClient) {
        this.getUsersClient = usersClient;
    }

    @Override
    public List<User> getUsersInLondon() {
        List<User> users = getUsersClient.usersByCity("London");

        return users;
    }

    @Override
    public List<User> getUsersNearLondon() {
        List<User> users = getUsersClient.allUsers();

        List<User> usersNearLondon =
                users.stream().filter(user -> isNearLondon(user.getLatitude(),user.getLongitude())).collect(Collectors.toList());

        return usersNearLondon;
    }

    private boolean isNearLondon(double latitude, double longitude){
        double distance = DistanceCalc.distanceInMiles(LONDON_LATITUDE, LONDON_LONGITUDE, latitude, longitude);
        
        return distance < CLOSE_LONDON_DISTANCE;
    }
}
