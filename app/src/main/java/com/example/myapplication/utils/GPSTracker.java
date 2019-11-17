package com.example.myapplication.utils;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class GPSTracker extends Service implements LocationListener {

    private final Context mContext;
    private final Activity activity;

    boolean isGpsEnabled = false;

    boolean isNetworkEnabled = false;

    boolean canGetLocation = false;

    Location location;
    double latitude;
    double longitude;

    // The minimum
    private static final long MIN_DISTANCE_FOR_UPDATES = 10;

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute



    protected LocationManager locationManager;

    public GPSTracker(Context mContext, Activity activity) {
        this.mContext = mContext;
        this.activity = activity;
        getLocation();

    }

    public Location getLocation(){
        try{
            if(mContext ==  null){
                Log.d("Context","null");
            }

            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);


            if(isGpsEnabled || isNetworkEnabled){
                this.canGetLocation = true;

                if(isNetworkEnabled){
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_TIME_BW_UPDATES,MIN_DISTANCE_FOR_UPDATES, this);
                    Log.d("Network", "Network");

                    if(locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        if(location != null){
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }

                if(isGpsEnabled){
                    if(location == null){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME_BW_UPDATES, MIN_DISTANCE_FOR_UPDATES, this);
                        Log.d("GPS: ","Enabled");
                        if(location != null){
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
            }
        }catch (SecurityException e){
            Log.e("GPSTracker", "Error creating location service: " + e.getMessage());
        }

        return location;
    }

    public void stopUsingGps(){
        if(locationManager!=null){
            locationManager.removeUpdates(this);
        }
    }

    public boolean canGetLocation(){
        return this.canGetLocation;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
