package com.ryanthompson123.bpdtsapitest.utils;

public class DistanceCalc {
    public static double distanceInMiles(double latitude1, double longitude1, double latitude2, double longitude2) {
        if (latitude1 == latitude2 && longitude1 == longitude2) {
            return 0;
        } else {
            double theta = longitude1 - longitude2;
            double distance = Math.sin(Math.toRadians(latitude1)) * Math.sin(Math.toRadians(latitude2)) + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) * Math.cos(Math.toRadians(theta));
            distance = Math.acos(distance);
            distance = Math.toDegrees(distance);
            return distance * 60 * 1.1515;
        }
    }
}
