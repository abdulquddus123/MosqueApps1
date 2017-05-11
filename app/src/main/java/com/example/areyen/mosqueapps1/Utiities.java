package com.example.areyen.mosqueapps1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;

/**
 * Created by Android Dev on 4/27/2017.
 */

public class Utiities {
    public static boolean checkCompatibility(Context context) {
        PackageManager pm = context.getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)) {
            System.out.println(Log.d("checkCompatibility",
                    "compass unavailable"));
            return false;
        } else {
            System.out.println(Log
                    .d("checkCompatibility", "compass available."));
            return true;
        }
    }

    public static float findQiblaAngle(LocationValues loc) {
        double sourceLon, targetLon, sourceLat, targetLat;

        if (loc != null) {
            if (loc.getMeccaPoints() != null && loc.getCurrLocation() != null) {
                targetLat = loc.getMeccaPoints().latitude;
                targetLon = loc.getMeccaPoints().longitude;
                sourceLat = loc.getCurrLocation().latitude;
                sourceLon = loc.getCurrLocation().longitude;

                // Get the current location
                Location startingLocation = new Location("starting point");
                startingLocation.setLatitude(sourceLat);
                startingLocation.setLongitude(sourceLon);

                // Get the target location
                Location endingLocation = new Location("ending point");
                endingLocation.setLatitude(targetLat);
                endingLocation.setLongitude(targetLon);

                // Find the Bearing from current location to next location
                float targetBearing = startingLocation
                        .bearingTo(endingLocation);
                return targetBearing;
            } else
                return 0f;
        } else
            return 0f;
    }






}

