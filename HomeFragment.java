package com.food.sistemas.sodapopapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import com.food.sistemas.sodapopapp.Realm.Detallepedidorealm;
import com.food.sistemas.sodapopapp.Realm.Operacionescondetallepedido;
import com.food.sistemas.sodapopapp.adapter.Adaptadordetallepedido;
import com.food.sistemas.sodapopapp.adapter.Adaptadorproductos;
import com.food.sistemas.sodapopapp.modelo.Detallepedido;
import com.food.sistemas.sodapopapp.modelo.Mesas;
import com.food.sistemas.sodapopapp.modelo.Pedido;
import com.food.sistemas.sodapopapp.modelo.Productos;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.R.attr.format;
import static com.facebook.FacebookSdk.getApplicationContext;
import static com.food.sistemas.sodapopapp.LoginActivity.CONNECTION_TIMEOUT;
import static com.food.sistemas.sodapopapp.LoginActivity.READ_TIMEOUT;



/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:33
 * Mail: specialcyci@gmail.com
 */
public class HomeFragment extends  Fragment implements   View.OnClickListener,RecyclerView.OnItemTouchListener  {
    String idalmacensf,idfacebook;
    Date fecharegistro;
    Realm realm = Realm.getDefaultInstance();
    String face;
    Toolbar toolbar;
    Typeface typeface;
    DrawerLayout mDrawer;
    ActionBarDrawerToggle mDrawerToggle;
    SharedPreferences prefs;String FileName ="myfile";
    private View view;
     private String[] strArrData = {"No Suggestions"};
    private RecyclerView recycler,recycler2;
    private RecyclerView.Adapter adapter,adapter2;
    private RecyclerView.LayoutManager lManager,lManager2;
    ArrayList<Productos> people=new ArrayList<>();
    ArrayList<Detallepedido> people2=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout, container, false);

        Resources res = getResources();
        prefs = getActivity().getSharedPreferences(FileName, Context.MODE_PRIVATE);
       face=prefs.getString("facebook","");

        String customFont = "Arbutus.ttf";
         typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), customFont);




// Obtener el Recycler PRODUCTOS
        recycler = (RecyclerView) view.findViewById(R.id.cardproductos);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);
// RECICLER DETALLE PEDIDO
        recycler2 = (RecyclerView) view.findViewById(R.id.carddetallepedido);
        recycler2.setHasFixedSize(true);
        lManager2=new LinearLayoutManager(getActivity());
        //lManager2 = new GridLayoutManager(getActivity(),2);
        recycler2.setLayoutManager(lManager2);

        final TabHost tabs = (TabHost) view.findViewById(android.R.id.tabhost);


       //cargardetalle();





        ImageView mipo=(ImageView)view.findViewById(R.id.mipollo);
        mipo.setOnClickListener(this);
        mipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (face.equals("si")){

                    String nombre = prefs.getString("sessionnombre", "");
                    String almacenactivo = prefs.getString("almacenactivo", "");
                    String idalmacenactivo = prefs.getString("idalmacenactivo", "");
                  //  Toast.makeText(HomeFragment.this.getActivity(),"con facebook"+nombre+almacenactivo+idalmacenactivo,Toast.LENGTH_LONG).show();
                    new traerproductosporidalmacenidfamilia().execute(idalmacenactivo,"7");


                }else if(face.equals("no")){
                    String nombre = prefs.getString("nombreusuario", "");
                    String almacenactivosf = prefs.getString("almacenactivosf", "");
                    String claveusuario=prefs.getString("claveusuario","");
                    idalmacensf=prefs.getString("idalmacenactivosf","");

                   // Toast.makeText(HomeFragment.this.getActivity(),"login normal"+nombre+almacenactivosf+claveusuario+idalmacensf,Toast.LENGTH_LONG).show();




                }

            }
        });
        ImageView migaseosa=(ImageView)view.findViewById(R.id.migaseosa);
        migaseosa.setOnClickListener(this);
        migaseosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (face.equals("si")){

                    String nombre = prefs.getString("sessionnombre", "");
                    String almacenactivo = prefs.getString("almacenactivo", "");
                    String idalmacenactivo = prefs.getString("idalmacenactivo", "");
                    //  Toast.makeText(HomeFragment.this.getActivity(),"con facebook"+nombre+almacenactivo+idalmacenactivo,Toast.LENGTH_LONG).show();
                    new traerproductosporidalmacenidfamilia().execute(idalmacenactivo,"2");


                }else if(face.equals("no")){
                    String nombre = prefs.getString("nombreusuario", "");
                    String almacenactivosf = prefs.getString("almacenactivosf", "");
                    String claveusuario=prefs.getString("claveusuario","");
                    idalmacensf=prefs.getString("idalmacenactivosf","");

                    // Toast.makeText(HomeFragment.this.getActivity(),"login normal"+nombre+almacenactivosf+claveusuario+idalmacensf,Toast.LENGTH_LONG).show();




                }

            }
        });


        ImageView micerveza=(ImageView)view.findViewById(R.id.micerveza);
        micerveza.setOnClickListener(this);
        micerveza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (face.equals("si")){

                    String nombre = prefs.getString("sessionnombre", "");
                    String almacenactivo = prefs.getString("almacenactivo", "");
                    String idalmacenactivo = prefs.getString("idalmacenactivo", "");
                    //  Toast.makeText(HomeFragment.this.getActivity(),"con facebook"+nombre+almacenactivo+idalmacenactivo,Toast.LENGTH_LONG).show();
                    new traerproductosporidalmacenidfamilia().execute(idalmacenactivo,"8");


                }else if(face.equals("no")){
                    String nombre = prefs.getString("nombreusuario", "");
                    String almacenactivosf = prefs.getString("almacenactivosf", "");
                    String claveusuario=prefs.getString("claveusuario","");
                    idalmacensf=prefs.getString("idalmacenactivosf","");

                    // Toast.makeText(HomeFragment.this.getActivity(),"login normal"+nombre+almacenactivosf+claveusuario+idalmacensf,Toast.LENGTH_LONG).show();




                }

            }
        });



        ImageView mihamburguesa=(ImageView)view.findViewById(R.id.mihamburguesa);
        mihamburguesa.setOnClickListener(this);
        mihamburguesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (face.equals("si")){

                    String nombre = prefs.getString("sessionnombre", "");
                    String almacenactivo = prefs.getString("almacenactivo", "");
                    String idalmacenactivo = prefs.getString("idalmacenactivo", "");
                    //  Toast.makeText(HomeFragment.this.getActivity(),"con facebook"+nombre+almacenactivo+idalmacenactivo,Toast.LENGTH_LONG).show();
                    new traerproductosporidalmacenidfamilia().execute(idalmacenactivo,"3");


                }else if(face.equals("no")){
                    String nombre = prefs.getString("nombreusuario", "");
                    String almacenactivosf = prefs.getString("almacenactivosf", "");
                    String claveusuario=prefs.getString("claveusuario","");
                    idalmacensf=prefs.getString("idalmacenactivosf","");

                    // Toast.makeText(HomeFragment.this.getActivity(),"login normal"+nombre+almacenactivosf+claveusuario+idalmacensf,Toast.LENGTH_LONG).show();




                }

            }
        });







        if (face.equals("si")){

            String nombre = prefs.getString("sessionnombre", "");
            String almacenactivo = prefs.getString("almacenactivo", "");

            TextView mesero=(TextView)view.findViewById(R.id.textViewmesero);
            mesero.setText(nombre);
            TextView almacen=(TextView)view.findViewById(R.id.textalmacen);
            almacen.setText(almacenactivo);

            new cargarmesas().execute(nombre);
        }else if(face.equals("no")){


            String nombre = prefs.getString("nombreusuario", "");
            String almacenactivosf = prefs.getString("almacenactivosf", "");
            String claveusuario=prefs.getString("claveusuario","");
            idalmacensf=prefs.getString("idalmacenactivosf","");


            TextView mesero=(TextView)view.findViewById(R.id.textViewmesero);
            mesero.setText(nombre);
            TextView almacen=(TextView)view.findViewById(R.id.textalmacen);
            almacen.setText(almacenactivosf);
new cargarmesassinfacebook().execute(nombre,claveusuario);


        }


        TextView fechadehoy =(TextView)view.findViewById(R.id.textViewfechadehoy);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateandTime = sdf.format(new Date());
        fechadehoy.setText(currentDateandTime);





        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("mitabs1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Pedido", ContextCompat.getDrawable(getActivity(), R.drawable.beer));
        tabs.addTab(spec);
        tabs.setCurrentTab(0);
        spec = tabs.newTabSpec("mitabs2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Menu", res.getDrawable(R.drawable.ic_menu_send));
        tabs.addTab(spec);
        tabs.setCurrentTab(1);
        spec = tabs.newTabSpec("mitabs2");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Mesas", res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        tabs.setCurrentTab(1);
        spec = tabs.newTabSpec("mitabs2");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Cambio", res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        tabs.setCurrentTab(1);





       tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
           @Override public void onTabChanged(String tabId) {
               int iy;
               iy = tabs.getCurrentTab();

               if (iy == 0) {
                   cargardetalle();


               } else if (iy ==1)
               {
               } } });



        TextView ty=(TextView) view.findViewById(R.id.txtobseravciones);


        ty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog(HomeFragment.this.getActivity(),"mensaje a");





            }
        });
        return view;


    }



    @Override
    public void onClick(View v) {

            switch (v.getId()) {

                case R.id.mipollo:

                    if (face.equals("si")){

                        String nombre = prefs.getString("sessionnombre", "");
                        String almacenactivo = prefs.getString("almacenactivo", "");

                        Toast.makeText(HomeFragment.this.getActivity(),"con facebook"+nombre+almacenactivo,Toast.LENGTH_LONG).show();
         }else if(face.equals("no")){
                        String nombre = prefs.getString("nombreusuario", "");
                        String almacenactivosf = prefs.getString("almacenactivosf", "");
                        String claveusuario=prefs.getString("claveusuario","");
                        idalmacensf=prefs.getString("idalmacenactivosf","");

                        Toast.makeText(HomeFragment.this.getActivity(),"login normal"+nombre+almacenactivosf+claveusuario+idalmacensf,Toast.LENGTH_LONG).show();
 }
                   //Toast.makeText(HomeFragment.this,"carga pollos de almacen",Toast.LENGTH_LONG).show();
                    // tareaporproductosporfamiliapollo.execute(listajson1);
                    break;

    }}

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private class cargarmesas extends AsyncTask<String, String, String> {

         HttpURLConnection conne;
        URL url = null;
        ArrayList<Mesas> listaalmaceno = new ArrayList<Mesas>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {

            try {
                url = new URL("http://sodapop.ga/sugest/apimesas.php");
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {
                conne = (HttpURLConnection) url.openConnection();
                conne.setReadTimeout(READ_TIMEOUT);
                conne.setConnectTimeout(CONNECTION_TIMEOUT);
                conne.setRequestMethod("POST");
                conne.setDoInput(true);
                conne.setDoOutput(true);

                // Append parameters to URL
                Log.d("valore","ttt");

                Log.d("valor",params[0]);
                Uri.Builder builder = new Uri.Builder()

                        .appendQueryParameter("nombre", params[0]);

                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conne.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conne.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }
            try {
                int response_code = conne.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK) {

                    InputStream input = conne.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);

                    }
                    return (result.toString());

                } else {
                    return("Connection error");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conne.disconnect();
            }
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d("waaaaaaa",result);
            Spinner spin=(Spinner)view.findViewById(R.id.spinnermesas);


            ArrayList<String> dataList = new ArrayList<String>();
            Mesas meso;
            if(result.equals("no rows")) {
                Toast.makeText(HomeFragment.this.getActivity(),"no existen datos a mostrar",Toast.LENGTH_LONG).show();

            }else{

                try {

                    JSONArray jArray = new JSONArray(result);


                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        dataList.add(json_data.getString("estadomesa"));
                          meso = new Mesas(json_data.getInt("idmesa"), json_data.getInt("numeromesa"), json_data.getString("estadomesa"), json_data.getInt("sillasmesa"));

                        listaalmaceno.add(meso);

                    }
                    strArrData = dataList.toArray(new String[dataList.size()]);
                    ArrayAdapter<Mesas> adaptadorl= new ArrayAdapter<Mesas>(HomeFragment.this.getContext(), android.R.layout.simple_spinner_item,listaalmaceno );

                    spin.setAdapter(adaptadorl);

                } catch (JSONException e) {
                }

            }

        }

    }
    private class cargarmesassinfacebook extends AsyncTask<String, String, String> {

        HttpURLConnection conne;
        URL url = null;
        ArrayList<Mesas> listaalmaceno = new ArrayList<Mesas>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {

            try {
                url = new URL("http://sodapop.ga/sugest/apimesassinfacebook.php");
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {
                conne = (HttpURLConnection) url.openConnection();
                conne.setReadTimeout(READ_TIMEOUT);
                conne.setConnectTimeout(CONNECTION_TIMEOUT);
                conne.setRequestMethod("POST");
                conne.setDoInput(true);
                conne.setDoOutput(true);

                // Append parameters to URL
                Log.d("valore","ttt");

                Log.d("valor",params[0]);
                Uri.Builder builder = new Uri.Builder()

                        .appendQueryParameter("nombre", params[0])
                        .appendQueryParameter("claveusuario",params[1]);

                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conne.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conne.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }
            try {
                int response_code = conne.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK) {

                    InputStream input = conne.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);

                    }
                    return (result.toString());

                } else {
                    return("Connection error");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conne.disconnect();
            }
        }
        @Override
        protected void onPostExecute(String result) {
            Log.d("waaaaaaa",result);
            Spinner spin=(Spinner)view.findViewById(R.id.spinnermesas);


            ArrayList<String> dataList = new ArrayList<String>();
            Mesas meso;
            if(result.equals("no rows")) {
                Toast.makeText(HomeFragment.this.getActivity(),"no existen datos a mostrar",Toast.LENGTH_LONG).show();

            }else{

                try {

                    JSONArray jArray = new JSONArray(result);


                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        dataList.add(json_data.getString("estadomesa"));
                        meso = new Mesas(json_data.getInt("idmesa"), json_data.getInt("numeromesa"), json_data.getString("estadomesa"), json_data.getInt("sillasmesa"));

                        listaalmaceno.add(meso);

                    }
                    strArrData = dataList.toArray(new String[dataList.size()]);
                    ArrayAdapter<Mesas> adaptadorl= new ArrayAdapter<Mesas>(HomeFragment.this.getContext(), android.R.layout.simple_spinner_item,listaalmaceno );

                    spin.setAdapter(adaptadorl);

                } catch (JSONException e) {
                }

            }

        }

    }
    private class traerproductosporidalmacenidfamilia extends AsyncTask<String, String, String> {

        HttpURLConnection conne;
        URL url = null;
        ArrayList<Productos> listaalmaceno = new ArrayList<Productos>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {

            try {
                url = new URL("http://sodapop.ga/sugest/apitraerproductosporfamiliaalmacen.php");
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {
                conne = (HttpURLConnection) url.openConnection();
                conne.setReadTimeout(READ_TIMEOUT);
                conne.setConnectTimeout(CONNECTION_TIMEOUT);
                conne.setRequestMethod("POST");
                conne.setDoInput(true);
                conne.setDoOutput(true);

                // Append parameters to URL


                Log.d("valor",params[0]);
                Log.d("valor",params[1]);
                Uri.Builder builder = new Uri.Builder()

                        .appendQueryParameter("idalmacen", params[0])
                        .appendQueryParameter("idfamilia",params[1]);

                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conne.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conne.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }
            try {
                int response_code = conne.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream input = conne.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);

                    }
                    return (

                            result.toString()


                    );

                } else {
                    return("Connection error");
                }
            } catch (IOException e) {
                e.printStackTrace()                ;
                Log.d("valorito",e.toString());
                return e.toString();
            } finally {
                conne.disconnect();
            }
        }


        @Override
        protected void onPostExecute(String result) {
            Log.d("valores",result);
people.clear();


            ArrayList<String> dataList = new ArrayList<String>();
            Productos meso;
            if(result.equals("no rows")) {
                Toast.makeText(HomeFragment.this.getActivity(),"no existen datos a mostrar",Toast.LENGTH_LONG).show();

            }else{

                try {


                    JSONArray jArray = new JSONArray(result);


                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.optJSONObject(i);

                        meso = new Productos(json_data.getInt("idproducto"), json_data.getString("nombreproducto"), json_data.getString("estadoproducto"), json_data.getString("ingredientes"),json_data.getDouble(("precventa")),json_data.getString("descripcion"));
                        people.add(meso);}
                        strArrData = dataList.toArray(new String[dataList.size()]);


                    adapter = new Adaptadorproductos(people,getActivity().getApplicationContext());
                    recycler.setAdapter(adapter);


                } catch (JSONException e) {
                    Log.d("valorerror",e.toString());
                }

            }

        }

    }
    public void realmgrbarenbasedatos(String nombre, int cantidad, Double precio,int idproducto,String imagen){

        Detallepedidorealm det=new Detallepedidorealm();

        //CarDb car = new CarDb();
        det.setCantidadrealm(cantidad);
        det.setNombreproductorealm(nombre);
        det.setPrecventarealm(precio);
        det.setIdproductorealm(idproducto);
        det.setImagenrealm(imagen);
        realm.beginTransaction();
        realm.commitTransaction();


    }
    private class grabarpedido extends AsyncTask<Pedido, Void, String> {
        String resultado;
        HttpURLConnection conne;
        URL url = null;
        Pedido ped;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(Pedido... params) {
            ped=params[0];
            try {
                url = new URL("http://sodapop.ga/sugest/apigrabapedido.php");
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
            try {
                conne = (HttpURLConnection) url.openConnection();
                conne.setReadTimeout(READ_TIMEOUT);
                conne.setConnectTimeout(CONNECTION_TIMEOUT);
                conne.setRequestMethod("POST");
                conne.setDoInput(true);
                conne.setDoOutput(true);

                // Append parameters to URL

                Log.d("valor",String.valueOf(ped.getIdcliente()));
                Log.d("valor",String.valueOf(ped.getIdmesa()));
                Uri.Builder builder = new Uri.Builder()


                        .appendQueryParameter("idcliente",String.valueOf(ped.getIdcliente()))
                        .appendQueryParameter("idmesa", String.valueOf(ped.getIdmesa()))
                       .appendQueryParameter("totalpedido", String.valueOf(ped.getTotalpedido()))
                        .appendQueryParameter("estadopedido", String.valueOf(ped.getEstadopedido()))
                        .appendQueryParameter("fecharegistro",ped.getFechapedido().toString())
                        .appendQueryParameter("idusuario",String.valueOf(ped.getIdusuario()))
                        .appendQueryParameter("idalmacen", String.valueOf(ped.getIdalmacen()))
                        .appendQueryParameter("idfacebook", String.valueOf(ped.getIdfacebook()));

                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conne.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conne.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return null;
            }
            try {
                int response_code = conne.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {
                    InputStream input = conne.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);

                    }
                    resultado=result.toString();
                    return resultado;

                } else {

                }
            } catch (IOException e) {
                e.printStackTrace()                ;
                Log.d("valorito",e.toString());
                return null;
            } finally {
                conne.disconnect();
            }
            return resultado;
        }


        @Override
        protected void onPostExecute(String resultado) {

            super.onPostExecute(resultado);
            Log.d("paso",resultado.toString());
            if(resultado.equals("true")){
                Log.d("ii", "insertado");


            }else{
                String ii =resultado.toString();
                Log.d("jj", "usuario valido");


                // lanzarsistema();
            }



        }


    }
    public void ejecutarcapturaryguardarpedido(){
        ArrayList<CarDb> list = new ArrayList(realm.where(CarDb.class).findAll());
        RealmResults<CarDb> resulta=realm.where(CarDb.class).findAll();
        resulta.toArray(new CarDb[resulta.size()]);
        if(resulta.size()>0){
double st=0.0;
            double tq=0.0;
            for(int u=0;u<resulta.size();u++){
                Double prevta=resulta.get(u).getprecio();
                int cnt= resulta.get(u).getcantidadapedir();
                int idal=1;
                int idpro=resulta.get(u).getidproducto();
                String img=resulta.get(u).getimagen();
                String nombrprod=resulta.get(u).getnombreproducto();
 tq=cnt* prevta;
                st=st+tq;
            }
             String idalmacenactivo = prefs.getString("idalmacenactivo", "");


            Spinner spinner = (Spinner)view.findViewById(R.id.spinnermesas);
            String valToSet = spinner.getSelectedItem().toString();
             String mesei=valToSet;
            int g= mesei.length();
            String mesi = mesei.substring(0,1);
            String  idi=mesi.trim();

            SimpleDateFormat sdff = new SimpleDateFormat("yyyyMMdd");
            String hj = sdff.format(new Date());
            try {
                fecharegistro= sdff.parse(hj);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Toast.makeText(HomeFragment.this.getActivity(),"TOTAL DE PEDIDO"+String.valueOf(st)+"alma"+idalmacenactivo+"mesa"+idi+"fecha"+hj,Toast.LENGTH_LONG).show();
            idfacebook=prefs.getString("sessionid","");
          Pedido pedido = new Pedido(2, Integer.parseInt(idi), st, "generado", fecharegistro, 0,Integer.parseInt(idalmacenactivo),idfacebook);

            new grabarpedido().execute(pedido);




        }else {
            // Toast.makeText(HomeFragment.this.getActivity(),"aun no hay datos",Toast.LENGTH_LONG).show();

        }




    }
public void cargardetalle(){

int pp=recycler2.getChildCount();
    for(int ee=0;ee<pp;ee++){


    }
    people2.clear();
    ArrayList<CarDb> list = new ArrayList(realm.where(CarDb.class).findAll());

    RealmResults<CarDb> resulta=realm.where(CarDb.class).findAll();
    resulta.toArray(new CarDb[resulta.size()]);

    if(resulta.size()>0){


        for(int u=0;u<resulta.size();u++){
            int cnt= resulta.get(u).getcantidadapedir();
            int idal=1;
            int idpro=resulta.get(u).getidproducto();
            String nombrprod=resulta.get(u).getnombreproducto();
            Double prevta=resulta.get(u).getprecio();
            String img=resulta.get(u).getimagen();

            Detallepedido meso2 = new Detallepedido(idpro,idpro,cnt,prevta,0.0,0,nombrprod,idal,img);
            people2.add(meso2);
        }

        recycler2.setAdapter(null);

        adapter2 = new Adaptadordetallepedido(people2,getActivity().getApplicationContext());
        recycler2.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();



    }else {
       // Toast.makeText(HomeFragment.this.getActivity(),"aun no hay datos",Toast.LENGTH_LONG).show();

    }




}

        public void showDialog(Activity activity, String msg){
            final Dialog dialog = new Dialog(activity);

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.observaciones);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            TextView text = (TextView) dialog.findViewById(R.id.descri);
            TextView text2= (TextView) dialog.findViewById(R.id.txtenviar);
            text2.setTypeface(typeface);


            Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
            dialogButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ejecutarcapturaryguardarpedido();
                    dialog.dismiss();
                }
            });

            dialog.show();


    }
}
