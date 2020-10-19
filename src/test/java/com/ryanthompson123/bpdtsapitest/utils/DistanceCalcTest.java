package com.ryanthompson123.bpdtsapitest.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DistanceCalcTest {

    @Test
    void distanceInMiles_shouldBeZeroForSameLocation() {
        // London Coordinates: 51.5074, -0.1277
        double distance = DistanceCalc.distanceInMiles(51.5074, -0.1277, 51.5074, -0.1277);
        assertEquals(0,distance);
    }

    @Test
    void distanceInMiles_shouldShowDistanceBetweenLondonAndNewcastle() {
        // London Coordinates: 51.5074, -0.1277
        // Newcastle Coordinates: 54.9783, -1.6178
        double expectedDistanceInMiles = 247.57674431106798; 
        double distance = DistanceCalc.distanceInMiles(51.5074, -0.1277, 54.9783, -1.6178);
        assertEquals(expectedDistanceInMiles,distance,2);
    }
}
