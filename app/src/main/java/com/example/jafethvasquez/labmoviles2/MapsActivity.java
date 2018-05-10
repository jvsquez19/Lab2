package com.example.jafethvasquez.labmoviles2;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private DBManager dbManager;
    private ArrayList<SedeDTO> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        this.dbManager = new DBManager(this);
        this.dbManager.chargeData();

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
        LatLng costaRica = new LatLng(10, -84);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(costaRica, 9.0f));
        this.data = this.dbManager.getListData();
        //Toast.makeText(this, ""+data.size(), Toast.LENGTH_SHORT).show();
        for( SedeDTO element : this.data){
            LatLng pos = new LatLng(element.getLatitude(),element.getLongitude());
            mMap.addMarker(new MarkerOptions().position(pos).title(element.getName())).setTag(element);
            //Toast.makeText(this, element.getName(), Toast.LENGTH_SHORT).show();

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                              @Override
                                              public boolean onMarkerClick(Marker marker) {
                                                  SedeDTO sede = (SedeDTO) marker.getTag();
                                                  Toast.makeText(MapsActivity.this, sede.getDescription(), Toast.LENGTH_SHORT).show();
                                                  return false;
                                              }
                                          }

            );
        }





    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
