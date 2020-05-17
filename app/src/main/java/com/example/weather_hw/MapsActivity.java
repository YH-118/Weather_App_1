package com.example.weather_hw;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



//    public void setLocation(){
//        String loc_lat = getIntent().getStringExtra(LAT);
//        lat = Double.parseDouble(loc_lat);
//        String loc_lng = getIntent().getStringExtra(LNG);
//        lng = Double.parseDouble(loc_lng);
//    }
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
        Intent receiveintent = getIntent();
        double lat = receiveintent.getDoubleExtra("lat", 0);
        double lng = receiveintent.getDoubleExtra("lng", 0);

        LatLng sydney = new LatLng(lat,lng);
        System.out.println(lat + "lat");
        System.out.println(lng + "long"); mMap.addMarker(new MarkerOptions().position(sydney).title("Location"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
