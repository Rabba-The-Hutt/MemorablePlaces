package org.robertlyon.memorableplaces;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
Anyone reading this please note, comments may be unnecessary for most people.
They are purely a learning tool for me that i use to aid me whilst learning to
create programs.
 */

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;

    //Recycler View and the Adapter that inflates the view
    public RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;

    //A list of the places that the user has clicked on in the Map Activity.
    static List<Place> placeList;

    public void cardClick(View v)
    {
        //Finds the Text View Associated with the card that has been clicked
        CardView c = (CardView) v;
        RelativeLayout rl = (RelativeLayout) c.getChildAt(0);
        TextView t = (TextView) rl.getChildAt(0);
        Log.i("Info", t.getTag().toString());


        //Switches the activity to the MapActivity
        Intent anIntent = new Intent(getApplicationContext(), MapsActivity.class);
        anIntent.putExtra("tag", Integer.parseInt(t.getTag().toString()));
        startActivity(anIntent);
    }

    //Updates the Recycler view when the user presses the bac button to move back to the MainActivity
    public void onResume()
    {
        super.onResume();
        mRecyclerView.setAdapter(mAdapter);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        placeList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.placeRecyView);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

       locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        //Adds an initial prompt to allow the user to navigate to the Map activity for the first time


        placeList.add(new Place("Add a new memorable place...", locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLatitude(),
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER).getLongitude()));

        //Sets the adapter to use the information that is contained within placeList
        mAdapter = new PlaceAdapter(this, placeList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
