package com.food.sistemas.sodapopapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.food.sistemas.sodapopapp.modelo.Almacen;
import com.food.sistemas.sodapopapp.response.RedemnorteApiAdapter;
import com.food.sistemas.sodapopapp.response.ResponsableResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    Spinner spinnerResponsable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile, container, false);

        result = (TextView) view.findViewById(R.id.textView3);
        result.setText("holaaaa");
        final Spinner list;
        list =(Spinner) view.findViewById(R.id.spinner);
        final ArrayList<Almacen> listaalmacen = new ArrayList<Almacen>();
        spinnerResponsable=(Spinner) view.findViewById(R.id.spinalmacen);


        show = (Button) view.findViewById(R.id.buttonRegister);

        obtenerDatosResponsables();
        return view;

    }

    private void poblarSpinnerResponsables(ArrayList<Almacen> almacen) {

        List<String> list = new ArrayList<String>();
        for (Almacen r : almacen) {
            list.add(r.getNombrealm());
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, list);
        // spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerResponsable.setAdapter(spinnerArrayAdapter);
    }

    private void obtenerDatosResponsables() {
        Call<ResponsableResponse> call = RedemnorteApiAdapter.getApiService().getResponsables();
        call.enqueue(new ResponsablesCallback());
    }

    class ResponsablesCallback implements Callback<ResponsableResponse> {

        @Override
        public void onResponse(Call<ResponsableResponse> call, Response<ResponsableResponse> response) {
            if (response.isSuccessful()) {
                ResponsableResponse responsableResponse = response.body();
                poblarSpinnerResponsables(responsableResponse.getResponsables());
            }   else {
                Toast.makeText(getContext(), "Error en el formato de respuesta", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponsableResponse> call, Throwable t) {
            Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}
