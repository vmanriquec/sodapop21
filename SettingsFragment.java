package com.food.sistemas.sodapopapp;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nitrico.mapviewpager.MapViewPager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SettingsFragment extends Fragment implements OnMapReadyCallback{
    private View view;
    MapView mMapView;
    GoogleMap map;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mapa, container, false);
return view;

    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        SupportMapFragment mapFragment=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

        @Override
    public void onMapReady(GoogleMap googleMap) {
map=googleMap;
            LatLng pp=new LatLng(11.5448729,104.8921668);
            MarkerOptions options=new MarkerOptions();
            options.position(pp).title("yo");
            map.addMarker(options);
            map.moveCamera(CameraUpdateFactory.newLatLng(pp));
        }
}
