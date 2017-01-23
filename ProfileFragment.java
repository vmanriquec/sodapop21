package com.food.sistemas.sodapopapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.food.sistemas.sodapopapp.modelo.Almacen;

import java.util.ArrayList;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:31
 * Mail: specialcyci@gmail.com
 */
public class ProfileFragment extends Fragment {
    String showUrl = "http://www.sodapop1978.pe.hu/apiandroidrecuperaalmacenes.php";
    TextView result;
    RequestQueue requestQueue;
    Button insert, show;
    Almacen mes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile, container, false);
        result = (TextView) view.findViewById(R.id.textView3);
        result.setText("holaaaa");
        final Spinner list;
        list =(Spinner) view.findViewById(R.id.spinner);
        final ArrayList<Almacen> listaalmacen = new ArrayList<Almacen>();


        show = (Button) view.findViewById(R.id.buttonRegister);

        return view;

    }

}
