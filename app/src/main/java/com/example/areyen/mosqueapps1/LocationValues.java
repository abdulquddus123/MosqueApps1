package com.example.areyen.mosqueapps1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Android Dev on 4/27/2017.
 */

public class LocationValues implements LocationListener {

    private LatLng meccaPoints, currLocation;
    private Context context;
    private Location userLocation;

    public LocationValues(Context context) {
        this.context = context;
        this.meccaPoints = new LatLng(21.416667, 39.816667);
    }

    public LatLng getMeccaPoints() {
        return meccaPoints;
    }

    public void setMeccaPoints(LatLng meccaPoints) {
        this.meccaPoints = meccaPoints;
    }

    public LatLng getCurrLocation() {
        return currLocation;
    }

    public void setCurrLocation(LatLng currLocation) {
        this.currLocation = currLocation;
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }


    public Location calcCurrentLocation() {


        LocationManager service = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = service.getBestProvider(criteria, true);

        userLocation = service.getLastKnownLocation(provider);
        if (userLocation != null) {
            currLocation = new LatLng(userLocation.getLatitude(),
                    userLocation.getLongitude());

        } else {
            service.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
            userLocation = service.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }
        return userLocation;
    }




    @Override
    public void onLocationChanged(Location loc) {
        if (loc != null) {
            userLocation = loc;

        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }
}
