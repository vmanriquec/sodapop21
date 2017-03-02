package com.food.sistemas.sodapopapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:33
 * Mail: specialcyci@gmail.com
 */
public class HomeFragment extends  Fragment {

    private View parentView;
    private com.food.sistemas.sodapopapp.special.ResideMenu.ResideMenu resideMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.home, container, false);
        setUpViews();


        return parentView;

    }

    private void setUpViews() {
        Vaio parentActivity = (Vaio) getActivity();
        resideMenu = parentActivity.getResideMenu();



        // add gesture operation's ignored views

    }
}
