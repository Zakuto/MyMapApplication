package com.example.myapplication;

import androidx.fragment.app.FragmentActivity;

//import android.location.Address;
//import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
//import android.widget.Toast;

import com.example.myapplication.utils.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//import java.io.IOException;
//import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GPSTracker gpsTracker;
    //private static final int MAX_RESULTS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 17.5f;

        gpsTracker = new GPSTracker(this,MapsActivity.this);

        if(gpsTracker.getLocation() == null){
            Log.e("Location: ","null");
        }

        LatLng address = getLatLngFromGps(gpsTracker.getLocation());

        mMap.addMarker(new MarkerOptions().position(address).title("Marker in My Place"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(address, zoomLevel));

    }

    /*public LatLng getLatLngFromAddress(String address){
        Geocoder geocoder = new Geocoder(this);

        List<Address> addresses;

        try{
            addresses = geocoder.getFromLocationName(address, MAX_RESULTS);
            if(addresses ==  null){
                return null;
            }

            Address location = addresses.get(0);

            return new LatLng(location.getLatitude(), location.getLongitude());

        }catch(IOException e){
            Toast.makeText(this, "An error occured: " + e.toString(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return null;
    }*/

    private LatLng getLatLngFromGps(Location location){
        return new LatLng(location.getLatitude(), location.getLongitude());
    }
}
