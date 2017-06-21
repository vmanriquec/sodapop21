package com.food.sistemas.sodapopapp;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午3:28
 * Mail: specialcyci@gmail.com
 */
public class SettingsFragment extends Fragment {

    private View parentView;
    private com.food.sistemas.sodapopapp.special.ResideMenu.ResideMenu resideMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.layout, container, false);
        setUpViews();


        return parentView;

    }

    private void setUpViews() {
        Vaio parentActivity = (Vaio) getActivity();
        resideMenu = parentActivity.getResideMenu();



        // add gesture operation's ignored views

    }

}
