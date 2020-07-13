package com.myapplicationdev.android.l8problemstatement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.android.gms.maps.GoogleMap;


public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);
        spinner = findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                UiSettings ui = map.getUiSettings();

                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);
                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                Object item = adapterView.getItemAtPosition(position);
                LatLng South = new LatLng(1.289440 , 103.849983);
                LatLng North = new LatLng(1.465390, 103.817140);
                LatLng Central = new LatLng(1.332700, 103.799179);
                if (position == 0) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(North,
                            15));
                } else if (position == 1) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(Central,
                            15));
                } else if (position == 2) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(South,
                            15));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });
        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                LatLng North = new LatLng(1.465390, 103.817140);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(North,
                        15));

                LatLng north= new LatLng(1.436065, 103.786263);
                Marker n = map.addMarker(new
                        MarkerOptions()
                        .position(north)
                        .title("North-HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654 ")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.star)));


                LatLng central = new LatLng(1.332700, 103.799179);
                Marker c = map.addMarker(new
                        MarkerOptions()
                        .position(central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 ")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                LatLng south = new LatLng(1.289440 , 103.849983);
                Marker s = map.addMarker(new
                        MarkerOptions()
                        .position(south)
                        .title("South")
                        .snippet("Block 555, Tampines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            }
        });



    }




}