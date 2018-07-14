package org.robertlyon.memorableplaces;


import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Marker mainMarker;
    int extraTag;

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
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        extraTag = getIntent().getIntExtra("tag", 0);
        if(extraTag > 0)
        {
            setLocation();

        }

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                mainMarker = mMap.addMarker(new MarkerOptions().position(latLng));

                List<Address> addresses;

                Geocoder geocoder = new Geocoder(getApplicationContext());
                try {
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

                    String address = addresses.get(0).getAddressLine(0);
                    double latitude = addresses.get(0).getLatitude();
                    double longitude = addresses.get(0).getLongitude();

                    MainActivity.placeList.add(new Place(address, latitude, longitude));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });
    }



    public void setLocation()
    {
        extraTag = getIntent().getIntExtra("tag", 0);
        Place aPlace = MainActivity.placeList.get(extraTag);

        LatLng aLocation = new LatLng(aPlace.getLatitude(),
        aPlace.getLongitude());
        mainMarker = mMap.addMarker(new MarkerOptions().position(aLocation));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(aLocation, 13.0f));
    }
}
