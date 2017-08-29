package com.food.sistemas.sodapopapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.food.sistemas.sodapopapp.adapter.Adaptadorsqlite;
import com.food.sistemas.sodapopapp.database.DBHelper;
import com.food.sistemas.sodapopapp.modelo.Almacen;
import com.food.sistemas.sodapopapp.modelo.Usuarios;

import java.util.ArrayList;
import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

import static com.food.sistemas.sodapopapp.R.id.nuevo;


public class ProfileFragment extends Fragment {
    String showUrl = "http://www.sodapop.space/apiandroidrecuperaalmacenes.php";
    String imagePath="";
    TextView result;
    private Button showDialog,alert_edittext;
    private CharSequence[] items = null;
    private Resources res = null;
    private android.app.AlertDialog alert;

    String FileName ="myfile";
    Button cargarimagen,insert, show,  btnclick,btnclickver,btnrefrescar,imqagenclkick;
    Almacen mes;
    Spinner spinnerResponsable;
    DBHelper mydb;
    ImageView imagensqlites;
  private RecyclerView recycler;
    private Adaptadorsqlite adapter;
    private RecyclerView.LayoutManager lManager;
    private  List<Usuarios> listaItemsUsuarios;
    private static int LOAD_IMAGE_RESULTS = 1;
    List itemso = new ArrayList();
    String session;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.usuariossqlite, container, false);
        final RecyclerView recycler = (RecyclerView) view.findViewById(R.id.reciclador);
        mydb=new DBHelper(getContext());
        try {
    Cursor res = mydb.traerUsarios();
    StringBuffer stringBuffer = new StringBuffer();
    if (res != null && res.getCount() > 0) {
        while (res.moveToNext()) {
            stringBuffer.append("idusuario     :" + res.getString(0) + "\n");
            stringBuffer.append("nombreusuario :" + res.getString(1) + "\n");
            stringBuffer.append("claveusuario  :" + res.getString(2) + "\n");
            stringBuffer.append("almacen       :" + res.getString(3) + "\n");
            stringBuffer.append("imagen       :" +  res.getString(4));
            lManager = new LinearLayoutManager(getContext());
            recycler.setLayoutManager(lManager);

            itemso.add(new Usuarios(res.getString(0), res.getString(1), res.getString(2), res.getString(3), res.getString(4)));
        }
//listasql.setText(stringBuffer.toString());
        Adaptadorsqlite adapterrecicler = new Adaptadorsqlite(itemso, getContext());
        recycler.setAdapter(adapterrecicler);

        imagePath = "";
    } else {
        Toast.makeText(getContext(), "no hay datos", Toast.LENGTH_LONG).show();
    }


}
catch (Exception e){
    System.err.println("Caught IOException: " + e.getMessage());
}


        FabSpeedDial fabSpeedDial = (FabSpeedDial) view.findViewById(R.id.menufloat);
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem item) {
                //TODO: Start some activity

                switch(item.getItemId()) {
                    case nuevo:
lanzar();
                        return true;


                    default:
                        return super.onMenuItemSelected( item);


                }

            }

            private void lanzar() {

                Intent intent = new Intent(getActivity(), CamaraActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void leershare(){
        SharedPreferences sharedPreferences=getContext().getSharedPreferences(FileName,Context.MODE_PRIVATE);
        String session=sharedPreferences.getString("sessionid","");
        String nomb=sharedPreferences.getString("sessionnombre","");
        String apepa=sharedPreferences.getString("sessionapepat","");
        String apema=sharedPreferences.getString("sessionapemat","");
        if (session.equals(null) || session.equals("")){
            Toast.makeText(getContext(),"no existe session::::::"+session,Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(getContext(),"Session activa "+nomb,Toast.LENGTH_LONG).show();

        }}

}
