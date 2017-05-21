package com.food.sistemas.sodapopapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.food.sistemas.sodapopapp.adapter.Adaptadorsqlite;
import com.food.sistemas.sodapopapp.database.DBHelper;
import com.food.sistemas.sodapopapp.database.UsuarioHelper;
import com.food.sistemas.sodapopapp.database.Usuariosql;
import com.food.sistemas.sodapopapp.modelo.Almacen;
import com.food.sistemas.sodapopapp.modelo.Usuarios;
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
    Button insert, show,  btnclick,btnclickver,btnrefrescar;
    Almacen mes;
    Spinner spinnerResponsable;
    DBHelper mydb;
  private RecyclerView recycler;
    private Adaptadorsqlite adapter;
    private RecyclerView.LayoutManager lManager;
    private  List<Usuarios> listaItemsUsuarios;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usuariossqlite, container, false);
        final Button btnrefrescar =(Button) view.findViewById(R.id.sqliterefrescar);

        final TextView nombreusuario = (TextView) view.findViewById(R.id.nombreusuario);
        final TextView claveusuario = (TextView) view.findViewById(R.id.claveusuario);
        final TextView listasql = (TextView) view.findViewById(R.id.textsqlite);

        final String allmacenes[] = {"huaral ","chancay","huacho","barranca"};
        final Spinner salmacenes=(Spinner) view.findViewById(R.id.spinneralmacenes);


        final RecyclerView recycler = (RecyclerView) view.findViewById(R.id.reciclador);

        recycler.setHasFixedSize(true);



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

        btnclickver =(Button) view.findViewById(R.id.sqlitever);
        btnclickver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res =mydb.traerUsarios();
                StringBuffer stringBuffer=new StringBuffer();
                if (res!=null && res.getCount()>0){
                    while (res.moveToNext()){
                        stringBuffer.append("idusuario     :"+res.getString(0)+"\n");
                        stringBuffer.append("nombreusuario :"+res.getString(1)+"\n");
                        stringBuffer.append("claveusuario  :"+res.getString(2)+"\n");
                        stringBuffer.append("almacen       :"+res.getString(3)+"\n");

                    }
listasql.setText(stringBuffer.toString());

                     String[] co = new String[] {res.getString(1),res.getString(2),res.getString(3)};
                    List items = new ArrayList();


                    items.add(new Usuarios(   res.getString(1),res.getString(2),res.getString(3)) );

                    Adaptadorsqlite adapterrecicler= new Adaptadorsqlite( items,getContext());
recycler.setAdapter(adapterrecicler);
                    Toast.makeText(getContext(),"listado",Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(getContext(),"no hay datos",Toast.LENGTH_LONG).show();

                }
            }
        });

        return view;

    }





}
