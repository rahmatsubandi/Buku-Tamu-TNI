package com.Mabes.datatamu.activities;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.Mabes.datatamu.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

        // Add a marker in Sydney and move the camera
        LatLng Mabes = new LatLng(-6.330713523368367, 106.90697284876383);
        mMap.addMarker(new MarkerOptions().position(Mabes).title("Pintu 2 Mabes"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Mabes));

        // Add a marker in Sydney and move the camera
        LatLng Mabesal = new LatLng(-6.329014346226007, 106.91151881226628);
        mMap.addMarker(new MarkerOptions().position(Mabesal).title("Mabes Angkatan Laut"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Mabes));

        // Add a marker in Sydney and move the camera
        LatLng Mabesau = new LatLng(-6.328874023706275, 106.91506635627083);
        mMap.addMarker(new MarkerOptions().position(Mabesau).title("Mabes Angkatan Udara"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Mabes));

        // Add a marker in Sydney and move the camera
        LatLng Mabestni = new LatLng(-6.3348015460985145, 106.91270304049065);
        mMap.addMarker(new MarkerOptions().position(Mabestni).title("Mabes Tentara Nasional Indonesia"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Mabes));
    }
}