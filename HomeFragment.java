package com.food.sistemas.sodapopapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import com.food.sistemas.sodapopapp.adapter.Adaptadorproductos;
import com.food.sistemas.sodapopapp.modelo.Mesas;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.food.sistemas.sodapopapp.LoginActivity.CONNECTION_TIMEOUT;
import static com.food.sistemas.sodapopapp.LoginActivity.READ_TIMEOUT;



/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:33
 * Mail: specialcyci@gmail.com
 */
public class HomeFragment extends  Fragment implements View.OnClickListener {
    String idalmacensf;
    SharedPreferences prefs;
    String face;
    Toolbar toolbar;
    DrawerLayout mDrawer;
    ActionBarDrawerToggle mDrawerToggle;
    String FileName ="myfile";
    private View view;
     private String[] strArrData = {"No Suggestions"};
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    ArrayList<Productos> people=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout, container, false);

        Resources res = getResources();
        prefs = getActivity().getSharedPreferences(FileName, Context.MODE_PRIVATE);
       face=prefs.getString("facebook","");






// Obtener el Recycler
        recycler = (RecyclerView) view.findViewById(R.id.cardproductos);
        recycler.setHasFixedSize(true);
// Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(lManager);
// Crear un nuevo adaptador





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
                    new traerproductosporidalmacenidfamilia().execute(idalmacenactivo,"4");


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





        final TabHost tabs = (TabHost) view.findViewById(android.R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("mitabs1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Orden", ContextCompat.getDrawable(getActivity(), R.drawable.beer));

        tabs.addTab(spec);

        spec = tabs.newTabSpec("mitabs2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Menu ", res.getDrawable(R.drawable.ic_menu_send));
        tabs.addTab(spec);
        tabs.setCurrentTab(0);
        spec = tabs.newTabSpec("mitabs2");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Mesa ", res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        tabs.setCurrentTab(1);
        spec = tabs.newTabSpec("mitabs2");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Mov ", res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        tabs.setCurrentTab(2);
        /*spec = tabs.newTabSpec("mitabs2");
        spec.setContent(R.id.tab5);
        spec.setIndicator("Est ", res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        tabs.setCurrentTab(3);*/

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
                url = new URL("http://sodapop.ga/sugest/apitraerproductosporfamilia.php");
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
            Log.d("valoresult",result);
people.clear();


            ArrayList<String> dataList = new ArrayList<String>();
            Productos meso;
            if(result.equals("no rows")) {
                Toast.makeText(HomeFragment.this.getActivity(),"no existen datos a mostrar",Toast.LENGTH_LONG).show();

            }else{

                try {

                    JSONArray jArray = new JSONArray(result);


                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);

                        meso = new Productos(json_data.getInt("idproducto"), json_data.getString("nombreproducto"), json_data.getString("estadoproducto"), json_data.getString("ingredientes"),json_data.getDouble(("precventa")));
                        people.add(meso);
                    }
                    strArrData = dataList.toArray(new String[dataList.size()]);

                    adapter = new Adaptadorproductos(people,getActivity().getApplicationContext());
                    recycler.setAdapter(adapter);


                } catch (JSONException e) {
                }

            }

        }

    }

}
