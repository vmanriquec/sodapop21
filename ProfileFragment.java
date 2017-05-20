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
import com.food.sistemas.sodapopapp.database.DBHelper;
import com.food.sistemas.sodapopapp.database.UsuarioHelper;
import com.food.sistemas.sodapopapp.database.Usuariosql;
import com.food.sistemas.sodapopapp.modelo.Almacen;
import com.food.sistemas.sodapopapp.response.RedemnorteApiAdapter;
import com.food.sistemas.sodapopapp.response.ResponsableResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ProfileFragment extends Fragment {
    String showUrl = "http://www.sodapop1978.pe.hu/apiandroidrecuperaalmacenes.php";
    TextView result;
    RequestQueue requestQueue;
    Button insert, show;
    Almacen mes;
    Spinner spinnerResponsable;
    DBHelper mydb;
    Button btnclick;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.usuariossqlite, container, false);

        final TextView nombreusuario = (TextView) view.findViewById(R.id.nombreusuario);
        final TextView claveusuario = (TextView) view.findViewById(R.id.claveusuario);

        final String allmacenes[] = {"huaral ","chancay","huacho","barranca"};
        final Spinner salmacenes=(Spinner) view.findViewById(R.id.spinneralmacenes);

ArrayAdapter <String> aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,allmacenes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view

        salmacenes.setAdapter(aa);
mydb=new DBHelper(getContext());
        btnclick =(Button) view.findViewById(R.id.sqliteinsertar);
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String usuario=nombreusuario.getText().toString();
                String clave=claveusuario.getText().toString();
String almacen=salmacenes.getItemAtPosition(salmacenes.getSelectedItemPosition()).toString();
                String esdt="1";
                Boolean result= mydb.insertarUsuario(usuario,clave,almacen,esdt);
            if (result==true){
                Toast.makeText(getContext(),"usuario insertado",Toast.LENGTH_LONG).show();

            }else {
                    Toast.makeText(getContext(),"nooo se completo la operacion",Toast.LENGTH_LONG).show();

                }
            }
        });


          return view;

    }




}
