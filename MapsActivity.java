package com.food.sistemas.sodapopapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    String session,nombreususrio,almacenactivo;
    String FileName ="myfile";
    private GoogleMap mMap;
    private PicassoMarker target;
    @Override
    protected void onCreate(Bundle savedInstanceState) {    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SharedPreferences sharedPreferences=getSharedPreferences(FileName, Context.MODE_PRIVATE);
        session=sharedPreferences.getString("sessionid","");
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

        LatLng sydney = new LatLng(-12.0167, -77.1167);
/*        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        PicassoMarker marker = new PicassoMarker(mMap);
        Picasso.with(MapsActivity.this).load(icon_url).into(marker);
*/

        String imgUrl = "https://graph.facebook.com/"+session+"/picture?type=large";
        MarkerOptions markerOne = new MarkerOptions().position(sydney).title("hola");
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        target = new PicassoMarker(mMap.addMarker(markerOne));
        Picasso.with(MapsActivity.this).load(imgUrl).transform(new CropCircleTransformation()).resize(50, 50).into(target);

    }




}
