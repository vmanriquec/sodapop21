package com.food.sistemas.sodapopapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.food.sistemas.sodapopapp.modelo.Almacen;
import com.food.sistemas.sodapopapp.modelo.Usuarios;
import com.food.sistemas.sodapopapp.response.RedemnorteApiAdapter;
import com.food.sistemas.sodapopapp.response.ResponsableResponse;
import com.vansuita.library.Icon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

//import com.google.android.gms.appindexing.Action;
//import com.google.android.gms.appindexing.AppIndex;
//import com.google.android.gms.common.api.GoogleApiClient;

public class Login extends AppCompatActivity {
    Usuarios usuarioingresa,usuariobasedatos;
    String yy,zz;
    private EditText nombreusuario,claveusuario;
    private EditText clave;
    private ProgressDialog loading;
    String showAlmacen = "https://sodapop.000webhostapp.com/apiandroidrecuperaalmacenes.php";
    String showUsuario = "https://sodapop.000webhostapp.com/apiandroidrecuperausuarios.php";
    RequestQueue requestQueue;
    Almacen mes;
    private  Spinner listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


      /*  normal.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
               // Toast.makeText(Login.this,"traer usuarios",Toast.LENGTH_LONG);
             //   listar =(Spinner) findViewById(R.id.spinner3);
               // String y= Integer.toString(listar.getAdapter().getCount());
         //Toast.makeText(Login.this,"almacenes"+y,Toast.LENGTH_LONG).show();
                //traerusuarios();
                ir();
            }
        });




        listaalmacenes();



         nombreusuario = (EditText) findViewById(R.id.txtnombreusuario);
        Icon.left(nombreusuario, R.drawable.userlogin);
        claveusuario = (EditText) findViewById(R.id.txtclaveusuario);
        Icon.left(claveusuario, R.drawable.llavelogin);

                String nombre = nombreusuario.getText().toString();
        String clave = claveusuario.getText().toString();



         if(TextUtils.isEmpty(nombre)) {
            nombreusuario.setError("Debes especificar clave");
            if (TextUtils.isEmpty(clave)){
                nombreusuario.setError("Debes especificar usuario");
                }

        }

   **/
    }
    String ii;
    private void ir(){
        Intent i= new Intent(this,Vaio.class);
        i.putExtra("nomusuario",nombreusuario.getText().toString() );
        i.putExtra("claveusuario",claveusuario.getText().toString() );
          startActivity(i);
    }
    private void getData() {
        String nombre = nombreusuario.getText().toString().trim();
        String clave = claveusuario.getText().toString().trim();

        if (nombre.equals("") ) {
            Toast.makeText(this, "esta vacio", Toast.LENGTH_LONG).show();
            return;
        }

        String url =  Config.DATA_URL+nombreusuario.getText().toString().trim();
        loading = ProgressDialog.show(this," Espere ",url,false,false);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                Toast.makeText(Login.this, "entrandooooooo", Toast.LENGTH_LONG).show();

                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       Toast.makeText(Login.this,"error",Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String response){
        String nombreusuario="";
        String claveusuario="";
        String idalmacen = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            nombreusuario = collegeData.getString(Config.KEY_NAME);
            claveusuario = collegeData.getString(Config.KEY_ADDRESS);
            idalmacen = collegeData.getString(Config.KEY_VC);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(this,claveusuario+nombreusuario+idalmacen,Toast.LENGTH_LONG);
    }
    public void listaalmacenes(){

        final Spinner list;
       // list =(Spinner) findViewById(R.id.spinner3);
        final ArrayList<Almacen> listaalmacen = new ArrayList<Almacen>();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                showAlmacen, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                try {
                    JSONArray students = response.getJSONArray("students");
                    for (int i = 0; i < students.length(); i++) {
                        JSONObject student = students.getJSONObject(i);

                        String firstname = student.getString("idalmacen");
                        String lastname = student.getString("nombrealm");
                        String age = student.getString("correoalm");
                        mes = new Almacen(Integer.parseInt(firstname), lastname,age,"1234");
                        listaalmacen.add(mes);
                        //        result.append(nombrealm + " " +  " \n");


                    }

                    ArrayAdapter<Almacen> asa = new ArrayAdapter<Almacen>(Login.this, android.R.layout.simple_spinner_item,listaalmacen );
                    // return asa;

           //         list.setAdapter(asa);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.append(error.getMessage());

            }

        });
        requestQueue.add(jsonObjectRequest);






    }
    public void traerusuarios(){

Usuarios usu;

            requestQueue = Volley.newRequestQueue(getApplicationContext());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    showUsuario, new Response.Listener<JSONObject>() {
                @Override

                public void onResponse(JSONObject response) {
                    System.out.println(response.toString());
                    try {
                        JSONArray students = response.getJSONArray("students");

                        for (int i = 0; i < students.length(); i++) {
                            JSONObject student = students.getJSONObject(i);

                            yy= student.getString("nombreusuario");
                            zz = student.getString("claveusuario");

if (yy.equals(nombreusuario.getText().toString()) && (zz.equals(claveusuario.getText().toString()))){
ir();
}
                        }
                       } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.append(error.getMessage());
                }

            });
            requestQueue.add(jsonObjectRequest);
        }







    }



