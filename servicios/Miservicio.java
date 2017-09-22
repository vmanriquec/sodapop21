package com.food.sistemas.sodapopapp.servicios;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sistemas on 20/09/2017.
 */

public class Miservicio extends Service implements LocationListener {
    private final Context ctx;
    double latitud;
    double longitud;
    Location location;
    LocationManager locationManager;
    boolean gpsactivo;
    TextView texto;
    private DatabaseReference chat_data_ref;
    private DatabaseReference user_name_ref;
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth firebaseAuth;
    public Miservicio() {

        super();
        this.ctx = this.getApplicationContext();
    }

    public Miservicio(Context c) {
        super();
        this.ctx = c;
        getlocation();
    }

    public void setView(View v) {
        texto = (TextView) v;
        texto.setText("Coordenadas" + latitud + "," + longitud);

    }

    public void getlocation() {


        try {
            locationManager = (LocationManager) this.ctx.getSystemService(LOCATION_SERVICE);
            gpsactivo = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);


        } catch (Exception e) {


        }
        if (gpsactivo) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                   return;
            }
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 60000, 10, this);
            location=locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            location.getLongitude();
            location.getLatitude();
            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("chat_data");
            mAuth= FirebaseAuth.getInstance();

            chat_data_ref= FirebaseDatabase.getInstance().getReference().child("gps");

           // user_name_ref=FirebaseDatabase.getInstance().getReference().child("chat_users").child(mAuth.getCurrentUser().getUid()).child("name").child("idfacebook");

            chat_data_ref.push().setValue(String.valueOf(latitud));

}
}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
