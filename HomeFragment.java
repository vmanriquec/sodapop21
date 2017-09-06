package com.food.sistemas.sodapopapp;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import com.food.sistemas.sodapopapp.adapter.Adaptadordetallepedido;
import com.food.sistemas.sodapopapp.adapter.Adaptadorproductos;
import com.food.sistemas.sodapopapp.modelo.Dashboardpedido;
import com.food.sistemas.sodapopapp.modelo.Detallepedido;
import com.food.sistemas.sodapopapp.modelo.Mesas;
import com.food.sistemas.sodapopapp.modelo.Pedido;
import com.food.sistemas.sodapopapp.modelo.Productos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

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
    String idfacebook;
    Date fecharegistro;
    Realm realm = Realm.getDefaultInstance();
    String face;
    Toolbar toolbar;
    Typeface typeface;
    DrawerLayout mDrawer;
    ActionBarDrawerToggle mDrawerToggle;
    SharedPreferences prefs;
    SharedPreferences prefse;
    String correo;
    String FileName ="myfile";
    int em1,em2,em3,em4,em5,em6,em7,em8,em9,em10,em11,em12,em13,em14,em15;
    private View view;
    private String[] strArrData = {"No Suggestions"};
    private RecyclerView recycler,recycler2,recycler3;
    private RecyclerView.Adapter adapter,adapter2,adapter3;
    private RecyclerView.LayoutManager lManager,lManager2,lManager3;
    TextView fechadehoy;
    String nombre,almacenactivosf,claveusuario,idalmacensf,idalmacenactivo,almacenactivo;
    ArrayList<Productos> people=new ArrayList<>();
    ArrayList<Pedido> people4=new ArrayList<>();
    ArrayList<Detallepedido> people2=new ArrayList<>();
    ArrayList<Dashboardpedido> people3=new ArrayList<>();
    Button boton1,boton2,boton3,boton4,boton5,boton6,boton7,boton8,boton9,boton10,boton11,boton12,boton13,boton14,boton15;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout, container, false);
  prefse =getApplicationContext().getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
         correo = prefse.getString("editando", "");






        boton1 = (Button) view.findViewById(R.id.buno);
        boton2 = (Button) view.findViewById(R.id.bdos);
        boton3 = (Button) view.findViewById(R.id.btres);
        boton4 = (Button) view.findViewById(R.id.bcuatro);
        boton5 = (Button) view.findViewById(R.id.bcinco);
        boton6 = (Button) view.findViewById(R.id.bseis);
        boton7 = (Button) view.findViewById(R.id.bsiete);
        boton8 = (Button) view.findViewById(R.id.bocho);
        boton9 = (Button) view.findViewById(R.id.bnueve);
        boton10 = (Button) view.findViewById(R.id.bdiez);
        boton11 = (Button) view.findViewById(R.id.bonce);
        boton12 = (Button) view.findViewById(R.id.bdoce);
        boton13 = (Button) view.findViewById(R.id.btrece);
        boton14 = (Button) view.findViewById(R.id.bcatorce);
        boton15 = (Button) view.findViewById(R.id.bquince);
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

//


        final TabHost tabs = (TabHost) view.findViewById(android.R.id.tabhost);


        //cargardetalle();





        ImageView mipo=(ImageView)view.findViewById(R.id.mipollo);
        mipo.setOnClickListener(this);
        mipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (face.equals("si")){

                    nombre = prefs.getString("sessionnombre", "");
                    almacenactivo = prefs.getString("almacenactivo", "");
                    idalmacenactivo = prefs.getString("idalmacenactivo", "");
                    //  Toast.makeText(HomeFragment.this.getActivity(),"con facebook"+nombre+almacenactivo+idalmacenactivo,Toast.LENGTH_LONG).show();
                    new traerproductosporidalmacenidfamilia().execute(idalmacenactivo,"7");


                }else if(face.equals("no")){
                    nombre = prefs.getString("nombreusuario", "");
                    almacenactivosf = prefs.getString("almacenactivosf", "");
                    claveusuario=prefs.getString("claveusuario","");
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

                    nombre = prefs.getString("sessionnombre", "");
                    almacenactivo = prefs.getString("almacenactivo", "");
                    idalmacenactivo = prefs.getString("idalmacenactivo", "");
                    //  Toast.makeText(HomeFragment.this.getActivity(),"con facebook"+nombre+almacenactivo+idalmacenactivo,Toast.LENGTH_LONG).show();
                    new traerproductosporidalmacenidfamilia().execute(idalmacenactivo,"2");


                }else if(face.equals("no")){
                    nombre = prefs.getString("nombreusuario", "");
                    almacenactivosf = prefs.getString("almacenactivosf", "");
                    claveusuario=prefs.getString("claveusuario","");
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

                    nombre = prefs.getString("sessionnombre", "");
                    almacenactivo = prefs.getString("almacenactivo", "");
                    idalmacenactivo = prefs.getString("idalmacenactivo", "");
                    //  Toast.makeText(HomeFragment.this.getActivity(),"con facebook"+nombre+almacenactivo+idalmacenactivo,Toast.LENGTH_LONG).show();
                    new traerproductosporidalmacenidfamilia().execute(idalmacenactivo,"8");


                }else if(face.equals("no")){
                    nombre = prefs.getString("nombreusuario", "");
                    almacenactivosf = prefs.getString("almacenactivosf", "");
                    claveusuario=prefs.getString("claveusuario","");
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

                    nombre = prefs.getString("sessionnombre", "");
                    almacenactivo = prefs.getString("almacenactivo", "");
                    idalmacenactivo = prefs.getString("idalmacenactivo", "");
                    //  Toast.makeText(HomeFragment.this.getActivity(),"con facebook"+nombre+almacenactivo+idalmacenactivo,Toast.LENGTH_LONG).show();
                    new traerproductosporidalmacenidfamilia().execute(idalmacenactivo,"3");


                }else if(face.equals("no")){
                    nombre = prefs.getString("nombreusuario", "");
                    almacenactivosf = prefs.getString("almacenactivosf", "");
                    claveusuario=prefs.getString("claveusuario","");
                    idalmacensf=prefs.getString("idalmacenactivosf","");

                    // Toast.makeText(HomeFragment.this.getActivity(),"login normal"+nombre+almacenactivosf+claveusuario+idalmacensf,Toast.LENGTH_LONG).show();




                }





            }
        });







        if (face.equals("si")){

            nombre = prefs.getString("sessionnombre", "");
            almacenactivo = prefs.getString("almacenactivo", "");

            TextView mesero=(TextView)view.findViewById(R.id.textViewmesero);
            mesero.setText(nombre);
            TextView almacen=(TextView)view.findViewById(R.id.textalmacen);
            almacen.setText(almacenactivo);

            new cargarmesas().execute(nombre);
            new cargarmesasdisponibilidad().execute(nombre);
        }else if(face.equals("no")){


            nombre = prefs.getString("nombreusuario", "");
            almacenactivosf = prefs.getString("almacenactivosf", "");
            claveusuario=prefs.getString("claveusuario","");
            idalmacensf=prefs.getString("idalmacenactivosf","");


            TextView mesero=(TextView)view.findViewById(R.id.textViewmesero);
            mesero.setText(nombre);
            TextView almacen=(TextView)view.findViewById(R.id.textalmacen);
            almacen.setText(almacenactivosf);
            new cargarmesassinfacebook().execute(nombre,claveusuario);


        }


        fechadehoy =(TextView)view.findViewById(R.id.textViewfechadehoy);
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




        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em1==1){
                    showDialogomesas(HomeFragment.this.getActivity(),"1");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em2==1){
                    showDialogomesas(HomeFragment.this.getActivity(),"2");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em3==3){
                    showDialogomesas(HomeFragment.this.getActivity(),"3");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em4==4){
                    showDialogomesas(HomeFragment.this.getActivity(),"4");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });
        boton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em5==5){
                    showDialogomesas(HomeFragment.this.getActivity(),"5");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });
        boton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em6==6){
                    showDialogomesas(HomeFragment.this.getActivity(),"6");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });
        boton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em7==7){
                    showDialogomesas(HomeFragment.this.getActivity(),"7");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });
        boton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em8==8){
                    showDialogomesas(HomeFragment.this.getActivity(),"8");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });
        boton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em9==9){
                    showDialogomesas(HomeFragment.this.getActivity(),"9");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });
        boton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em10==10){
                    showDialogomesas(HomeFragment.this.getActivity(),"10");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });
        boton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em11==11){
                    showDialogomesas(HomeFragment.this.getActivity(),"11");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });
        boton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em12==12){
                    showDialogomesas(HomeFragment.this.getActivity(),"12");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });
        boton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em13==13){
                    showDialogomesas(HomeFragment.this.getActivity(),"13");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });
        boton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em14==14){
                    showDialogomesas(HomeFragment.this.getActivity(),"14");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
            }

        });
        boton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (em15==15){
                    showDialogomesas(HomeFragment.this.getActivity(),"15");
                }
                else{
                    Toast.makeText(HomeFragment.this.getActivity(), "esta mesa no tiene pedidos", Toast.LENGTH_SHORT).show();}
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
                url = new URL("http://sodapop.space/sugest/apimesas.php");
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
    private class cargarmesasdisponibilidad extends AsyncTask<String, String, String> {

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
                url = new URL("http://sodapop.space/sugest/apimesasdealmacen.php");
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

                    Drawable mesalibre = getResources().getDrawable(R.drawable.buttonmesasbackground);
                    Drawable mesaocupada = getResources().getDrawable(R.drawable.buttonmesasocupadasbackground);


                    int cc = listaalmaceno.size();
                    for (int i = 0; i < cc; i++) {
                        int nummesa = listaalmaceno.get(i).getNumeromesa();
                        String estadomesa = listaalmaceno.get(i).getEstadomesa().toString();

                        switch (nummesa) {
                            case 1:
                                if (estadomesa.equals("generado")) {

                                    boton1.setBackground(mesaocupada);


                                    em1=1;
                                } else {

                                    boton1.setBackground(mesalibre);
                                    em1=0;
                                }

                                break;
                            case 2:
                                if (estadomesa.equals("generado")) {
                                    em2=1;
                                    boton2.setBackground(mesaocupada);
                                } else {
                                    em2=0;
                                    boton2.setBackground(mesalibre);
                                }
                                break;

                            case 3:
                                if (estadomesa.equals("generado")) {
                                    em3=1;
                                    boton3.setBackground(mesaocupada);
                                } else {
                                    em3=0;
                                    boton3.setBackground(mesalibre);
                                }
                                break;

                            case 4:
                                if (estadomesa.equals("generado")) {
                                    em4=1;
                                    boton4.setBackground(mesaocupada);
                                } else {
                                    em4=0;
                                    boton4.setBackground(mesalibre);
                                }
                                break;

                            case 5:
                                if (estadomesa.equals("generado")) {
                                    em5=1;
                                    boton5.setBackground(mesaocupada);
                                } else {
                                    em5=0;
                                    boton5.setBackground(mesalibre);
                                }

                                break;

                            case 6:
                                if (estadomesa.equals("generado")) {
                                    em6=1;
                                    boton6.setBackground(mesaocupada);
                                } else {
                                    em6=0;
                                    boton6.setBackground(mesalibre);
                                }
                                break;

                            case 7:
                                if (estadomesa.equals("generado")) {
                                    em7=1;
                                    boton7.setBackground(mesaocupada);
                                } else {
                                    em7=0;
                                    boton7.setBackground(mesalibre);
                                }
                                break;

                            case 8:
                                if (estadomesa.equals("generado")) {
                                    em8=1;
                                    boton8.setBackground(mesaocupada);
                                } else {
                                    em8=0;
                                    boton8.setBackground(mesalibre);
                                }
                                break;

                            case 9:
                                if (estadomesa.equals("generado")) {
                                    em9=1;
                                    boton9.setBackground(mesaocupada);
                                } else {
                                    em9=0;
                                    boton9.setBackground(mesalibre);
                                }
                                break;

                            case 10:
                                if (estadomesa.equals("generado")) {
                                    em10=1;
                                    boton10.setBackground(mesaocupada);
                                } else {
                                    em10=0;
                                    boton10.setBackground(mesalibre);
                                }
                                break;

                            case 11:
                                if (estadomesa.equals("generado")) {
                                    em11=1;
                                    boton11.setBackground(mesaocupada);
                                } else {
                                    em11=0;
                                    boton11.setBackground(mesalibre);
                                }
                                break;

                            case 12:
                                if (estadomesa.equals("generado")) {
                                    em12=1;
                                    boton12.setBackground(mesaocupada);
                                } else {
                                    em12=0;
                                    boton12.setBackground(mesalibre);
                                }
                                break;

                            case 13:
                                if (estadomesa.equals("generado")) {
                                    em13=1;
                                    boton13.setBackground(mesaocupada);
                                } else {
                                    em13=0;
                                    boton13.setBackground(mesalibre);
                                }
                                break;

                            case 14:
                                if (estadomesa.equals("generado")) {
                                    em14=1;
                                    boton14.setBackground(mesaocupada);
                                } else {
                                    em14=0;
                                    boton14.setBackground(mesalibre);
                                }
                                break;

                            case 15:
                                if (estadomesa.equals("generado")) {
                                    em15=1;
                                    boton15.setBackground(mesaocupada);
                                } else {
                                    em15=0;
                                    boton15.setBackground(mesalibre);
                                }

                                break;
                        }}
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
                url = new URL("http://sodapop.space/sugest/apimesassinfacebook.php");
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
                url = new URL("http://sodapop.space/sugest/apitraerproductosporfamiliaalmacen.php");
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

                return e.toString();
            } finally {
                conne.disconnect();
            }
        }


        @Override
        protected void onPostExecute(String result) {

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

                }

            }

        }

    }
    public void realmgrbarenbasedatos(String nombre, int cantidad, Double precio,int idproducto,String imagen){

        Detallepedidorealm det=new Detallepedidorealm();

        CarDb car = new CarDb();
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
                url = new URL("http://sodapop.space/androidinsertarpedido.php");
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



                Uri.Builder builder = new Uri.Builder()


                        .appendQueryParameter("idcliente",String.valueOf(ped.getIdcliente()))
                        .appendQueryParameter("idmesa", String.valueOf(ped.getIdmesa()))
                        .appendQueryParameter("totalpedido", String.valueOf(ped.getTotalpedido()))
                        .appendQueryParameter("estadopedido", String.valueOf(ped.getEstadopedido()))

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

                return null;
            } finally {
                conne.disconnect();
            }
            return resultado;
        }


        @Override
        protected void onPostExecute(String resultado) {

            super.onPostExecute(resultado);

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

        String idalmacenactivo = prefs.getString("idalmacenactivo", "");
        resulta.toArray(new CarDb[resulta.size()]);


        if(resulta.size()>0){

            if (correo.equals("")){

                Log.d("ioooojojo","nulo");
                double st=0.0;
                double tq=0.0;
                ArrayList<Detallepedido> detalledebasededatos=new ArrayList<>();

                for(int u=0;u<resulta.size();u++){
                    Double prevta=resulta.get(u).getprecio();
                    int cnt= resulta.get(u).getcantidadapedir();
                    int idal=1;
                    int idpro=resulta.get(u).getidproducto();
                    String img=resulta.get(u).getimagen();
                    String nombrprod=resulta.get(u).getnombreproducto();
                    tq=cnt* prevta;
                    st=st+tq;
                    Detallepedido f =new Detallepedido( 0,resulta.get(u).getidproducto(),resulta.get(u).getcantidadapedir(),resulta.get(u).getprecio(),tq,0,resulta.get(u).getnombreproducto(), Integer.parseInt(idalmacenactivo),"" );
                    //  detalledebasededatos.add(f);
                    new grabardetallepedido().execute(f);
                }

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

                Spinner spinner = (Spinner)view.findViewById(R.id.spinnermesas);
                String valToSet = spinner.getSelectedItem().toString();
                String mesei=valToSet;
                int g= mesei.length();
                String mesi = mesei.substring(0,1);
                String  idi=mesi.trim();


                Date date = null;
                String str_date=fechadehoy.getText().toString();
                DateFormat formatter ;

                formatter = new SimpleDateFormat("dd-MMM-yy");
                try {
                    date = formatter.parse(str_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                //Toast.makeText(HomeFragment.this.getActivity(),"TOTAL DE PEDIDO"+String.valueOf(st)+"alma"+idalmacenactivo+"mesa"+idi+"fecha"+hj,Toast.LENGTH_LONG).show();
                idfacebook=prefs.getString("sessionid","");
                Pedido pedido = new Pedido(1, Integer.parseInt(idi), st, "generado",  date, 0,Integer.parseInt(idalmacenactivo),idfacebook);

                new grabarpedido().execute(pedido);

                ArrayList<CarDb> listu = new ArrayList(realm.where(CarDb.class).findAll());
                realm.beginTransaction();
                boolean resultau=realm.where(CarDb.class).findAll().deleteFirstFromRealm();


                realm.commitTransaction();
                recycler2.setAdapter(null);
                adapter2.notifyDataSetChanged();





                new cargarmesas().execute(nombre);
                new cargarmesasdisponibilidad().execute(nombre);

            }else{

                //aqui tenemos que eliminar el detalle y grabar nuevamente con el id de pedido
                Log.d("iooooeditat",correo);
                Toast.makeText(getContext(),"se actualizara pedido",Toast.LENGTH_LONG).show();
                SharedPreferences prefse =
                        getApplicationContext().getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefse.edit();
                editor.putString("editando", "");

                editor.commit();





            }


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
        TextView detalle = (TextView) dialog.findViewById(R.id.txtdetalle);
        TextView total = (TextView) dialog.findViewById(R.id.txttotalpedido);
        TextView text2= (TextView) dialog.findViewById(R.id.txtenviar);
        ArrayList<CarDb> list = new ArrayList(realm.where(CarDb.class).findAll());
        RealmResults<CarDb> resulta=realm.where(CarDb.class).findAll();

        String idalmacenactivo = prefs.getString("idalmacenactivo", "");
        resulta.toArray(new CarDb[resulta.size()]);
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
            Detallepedido f =new Detallepedido( 0,resulta.get(u).getidproducto(),resulta.get(u).getcantidadapedir(),resulta.get(u).getprecio(),tq,0,resulta.get(u).getnombreproducto(), Integer.parseInt(idalmacenactivo),"" );
            //  detalledebasededatos.add(f);
            detalle.append(resulta.get(u).getcantidadapedir()+" ---  "+resulta.get(u).getnombreproducto()+"\n");
        }

        total.setText("Total:  "+st);
        text2.setTypeface(typeface);


        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarcapturaryguardarpedido();
                dialog.dismiss();
            }
        });

        Button dialogButtonatras = (Button) dialog.findViewById(R.id.atras);
        dialogButtonatras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });

        dialog.show();


    }
    private class grabardetallepedido extends AsyncTask<Detallepedido, Void, String> {
        String resultado;
        HttpURLConnection conne;
        URL url = null;
        Detallepedido ped;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(Detallepedido... params) {
            ped=params[0];
            try {
                url = new URL("http://sodapop.space/sugest/androidinsertardetalledepedido.php");
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


                Log.d("valor",String.valueOf(ped.getNombreproducto()));
                Uri.Builder builder = new Uri.Builder()



                        .appendQueryParameter("idproducto",String.valueOf(ped.getIdproducto()))

                        .appendQueryParameter("cantidad", String.valueOf

                                (ped.getCantidad()))
                        .appendQueryParameter("precventa", String.valueOf(ped.getPrecventa()))
                        .appendQueryParameter("subtotal", String.valueOf(ped.getSubtotal()))
                        .appendQueryParameter("idalmacen",String.valueOf(ped.getIdalmacen()));



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
                    Log.d("paso",resultado.toString());
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

            if(resultado.equals("true")){
                Log.d("ii", resultado);


            }else{
                String ii =resultado.toString();
                Log.d("jj", "usuario valido");


                // lanzarsistema();
            }



        }
    }
    public void showDialogomesas(Activity activity, final String msgo){
        final Dialog dialog = new Dialog(activity);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.popupmesasopcion);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button dialogButton = (Button) dialog.findViewById(R.id.btnatrasmnumesa);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        Button cobrar = (Button) dialog.findViewById(R.id.btncobrarpedido);
        cobrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = null;
                String str_date=fechadehoy.getText().toString();
                DateFormat formatter ;

                formatter = new SimpleDateFormat("dd-MMM-yy");
                try {
                    date = formatter.parse(str_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String idalmacenactivo = prefs.getString("idalmacenactivo", "");
                Spinner spinner = (Spinner)view.findViewById(R.id.spinnermesas);
                String valToSet = spinner.getSelectedItem().toString();
                String mesei=valToSet;
                int g= mesei.length();
                String mesi = mesei.substring(0,1);
                String  idi=mesi.trim();
                Pedido pedido = new Pedido( 0, Integer.parseInt(msgo), 0.0, "",  date, 0,Integer.parseInt(idalmacenactivo),idfacebook);
                new cobrarpedido().execute(pedido);


                new cargarmesasdisponibilidad().execute(nombre);
                new cargarmesas().execute(nombre);
                dialog.dismiss();
            }
        });



        Button anula = (Button) dialog.findViewById(R.id.btneliminaredido);
        anula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = null;
                String str_date=fechadehoy.getText().toString();
                DateFormat formatter ;

                formatter = new SimpleDateFormat("dd-MMM-yy");
                try {
                    date = formatter.parse(str_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String idalmacenactivo = prefs.getString("idalmacenactivo", "");
                Spinner spinner = (Spinner)view.findViewById(R.id.spinnermesas);
                String valToSet = spinner.getSelectedItem().toString();
                String mesei=valToSet;
                int g= mesei.length();
                String mesi = mesei.substring(0,1);
                String  idi=mesi.trim();

                Pedido pedido = new Pedido( 0, Integer.parseInt(msgo), 0.0, "",  date, 0,Integer.parseInt(idalmacenactivo),idfacebook);
                new anularpedido().execute(pedido);


                new cargarmesasdisponibilidad().execute(nombre);
                new cargarmesas().execute(nombre);
                dialog.dismiss();
            }
        });

        Button edit = (Button) dialog.findViewById(R.id.btneditarpedido);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = null;
                String str_date=fechadehoy.getText().toString();
                DateFormat formatter ;

                formatter = new SimpleDateFormat("dd-MMM-yy");
                try {
                    date = formatter.parse(str_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String idalmacenactivo = prefs.getString("idalmacenactivo", "");
                Spinner spinner = (Spinner)view.findViewById(R.id.spinnermesas);
                String valToSet = spinner.getSelectedItem().toString();
                String mesei=valToSet;
                int g= mesei.length();
                String mesi = mesei.substring(0,1);
                String  idi=mesi.trim();

                Pedido pedido = new Pedido( 0, Integer.parseInt(msgo), 0.0, "",  date, 0,Integer.parseInt(idalmacenactivo),idfacebook);
                new traerpedidoaeditar().execute(pedido);
                new traerdetallepedidoaeditar().execute(pedido);

                //new cargarmesasdisponibilidad().execute(nombre);
                //new cargarmesas().execute(nombre);
                dialog.dismiss();
            }
        });

        dialog.show();


    }
    private class cobrarpedido extends AsyncTask<Pedido, Void, String> {
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
                url = new URL("http://sodapop.space/sugest/apicobrarpedido.php");
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



                Uri.Builder builder = new Uri.Builder()


                        .appendQueryParameter("idmesa",String.valueOf(ped.getIdmesa()))

                        .appendQueryParameter("idalmacen", String.valueOf(ped.getIdalmacen()));

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
                    Log.d("muestra",resultado);
                    return resultado;

                } else {

                }
            } catch (IOException e) {
                e.printStackTrace()                ;

                return null;
            } finally {
                conne.disconnect();
            }
            return resultado;
        }


        @Override
        protected void onPostExecute(String resultado) {

            super.onPostExecute(resultado);

            if(resultado.equals("true")){
                Log.d("ii", "insertado");


            }else{
                String ii =resultado.toString();
                Log.d("jj", "usuario valido");


                // lanzarsistema();
            }



        }


    }
    private class anularpedido extends AsyncTask<Pedido, Void, String> {
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
                url = new URL("http://sodapop.space/sugest/apianularpedido.php");
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

                Log.d("iooo", String.valueOf(ped.getIdmesa()));
                Log.d("iooo", String.valueOf(ped.getIdalmacen()));

                Uri.Builder builder = new Uri.Builder()


                        .appendQueryParameter("numerodemesa",String.valueOf(ped.getIdmesa()))

                        .appendQueryParameter("idalmacen", String.valueOf(ped.getIdalmacen()));

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
                    Log.d("ioooo",resultado);
                    return resultado;

                } else {

                }
            } catch (IOException e) {
                e.printStackTrace()                ;

                return null;
            } finally {
                conne.disconnect();
            }
            return resultado;
        }


        @Override
        protected void onPostExecute(String resultado) {

            super.onPostExecute(resultado);

            if(resultado.equals("true")){
                Log.d("ioooo", "eliminado");


            }else{
                String ii =resultado.toString();
                Log.d("ioooo", "usuario valido");


                // lanzarsistema();
            }



        }


    }
    private class traerpedidoaeditar extends AsyncTask<Pedido, Void, String> {
        Pedido ped;

        HttpURLConnection conne;
        URL url = null;
        ArrayList<Pedido> listaalmaceno = new ArrayList<Pedido>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(Pedido... params) {
            ped=params[0];
            try {
                url = new URL("http://sodapop.space/sugest/apieditarpedido.php");

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


                Log.d("ioooo",String.valueOf(ped.getIdmesa()));
                Log.d("iooooo",String.valueOf(ped.getIdalmacen()));
                Uri.Builder builder = new Uri.Builder()

                        .appendQueryParameter("numerodemesa", String.valueOf(ped.getIdmesa()))

                        .appendQueryParameter("idalmacen",String.valueOf(ped.getIdalmacen()));

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

                return e.toString();
            } finally {
                conne.disconnect();
            }
        }


        @Override
        protected void onPostExecute(String result) {

//            people.clear();

            Log.d("ioooo",result);
            ArrayList<String> dataList = new ArrayList<String>();
            Pedido meso;
            if(result.equals("no rows")) {
                Toast.makeText(HomeFragment.this.getActivity(),"no existen datos a mostrar",Toast.LENGTH_LONG).show();

            }else{

                try {


                    JSONArray jArray = new JSONArray(result);


                    Date date = null;
                    String str_date=fechadehoy.getText().toString();
                    DateFormat formatter ;

                    formatter = new SimpleDateFormat("dd-MMM-yy");
                    try {
                        date = formatter.parse(str_date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.optJSONObject(i);
                        meso   = new Pedido( json_data.getInt("idpedido"),json_data.getInt("idcliente"),json_data.getInt("idmesa"),json_data.getDouble("totalpedido"),json_data.getString("estadopedido"),date,json_data.getInt("idusuario"),
                                json_data.getInt("idalmacen"),json_data.getString("idfacebook"));
Toast.makeText(getContext(),String.valueOf(json_data.getInt("idpedido")),Toast.LENGTH_LONG).show();


                        SharedPreferences prefse =
                                getApplicationContext().getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);

                        SharedPreferences.Editor editor = prefse.edit();
                        editor.putString("editando", String.valueOf(json_data.getInt("idpedido")));

                        editor.commit();


//                        people4.add(meso);
                    }
                    //strArrData = dataList.toArray(new String[dataList.size()]);


                    //  adapter = new Adaptadorproductos(people,getActivity().getApplicationContext());
                    //recycler.setAdapter(adapter);


                } catch (JSONException e) {

                }

            }

        }

    }
    private class traerdetallepedidoaeditar extends AsyncTask<Pedido, Void, String> {
        Pedido ped;

        HttpURLConnection conne;
        URL url = null;
        ArrayList<Pedido> listaalmaceno = new ArrayList<Pedido>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(Pedido... params) {
            ped=params[0];
            try {
                url = new URL("http://sodapop.space/sugest/apieditardetallepedido.php");

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


                Log.d("ioooo",String.valueOf(ped.getIdmesa()));
                Log.d("iooooo",String.valueOf(ped.getIdalmacen()));
                Uri.Builder builder = new Uri.Builder()

                        .appendQueryParameter("numerodemesa", String.valueOf(ped.getIdmesa()))

                        .appendQueryParameter("idalmacen",String.valueOf(ped.getIdalmacen()));

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

                return e.toString();
            } finally {
                conne.disconnect();
            }
        }


        @Override
        protected void onPostExecute(String result) {

//            people.clear();

            Log.d("ioooo",result);
            ArrayList<String> dataList = new ArrayList<String>();
            Detallepedido meso;
            if(result.equals("no rows")) {
                Toast.makeText(HomeFragment.this.getActivity(),"no existen datos a mostrar",Toast.LENGTH_LONG).show();

            }else{

                try {


                    JSONArray jArray = new JSONArray(result);


                    Date date = null;
                    String str_date=fechadehoy.getText().toString();
                    DateFormat formatter ;

                    formatter = new SimpleDateFormat("dd-MMM-yy");
                    try {
                        date = formatter.parse(str_date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    vaciardatosdedetallepedidorealm();
                    Log.d("ioooo","vaciado");

                    for (int i = 0; i < jArray.length(); i++) {
                        Log.d("ioooooooo",String.valueOf(jArray.get(0)));

                        JSONObject json_data = jArray.optJSONObject(i);
                        Log.d("iooooooooooooooo",String.valueOf(json_data.get("nombreproducto")));

                        meso   = new Detallepedido( json_data.getInt("iddetallepedido"),json_data.getInt("idproducto"),json_data.getInt("cantidad"),json_data.getDouble("precventa"),json_data.getDouble("subtotal"),json_data.getInt("idpedido"),
                                json_data.getString("nombreproducto"),json_data.getInt("idalmacen"),json_data.getString("descripcion"));


//llenar datos a la base de datos
                        //  realmgrbarenbasedatos(meso.getNombreproducto(), meso.getCantidad(), meso.getPrecventa(),meso.getIdproducto(),meso.getImagen());
                        //realmgrbarenbasedatos(meso.getIddetallepedido(), meso.getIdproducto(), meso.getCantidad(),meso.getPrecventa(),meso.getNombreproducto(), meso.getIdalmacen());
                        Realm realm = Realm.getDefaultInstance();

                        realm.beginTransaction();
                        CarDb car = realm.createObject(CarDb.class);


                        car.setprecio(meso.getPrecventa());
                        car.setidproducto(meso.getIdproducto());
                        car.setIddetallepedido(meso.getIddetallepedido());
                        car.setcantidadapedir(meso.getCantidad());
                        car.setImagen(meso.getImagen());
                        car.setnombreproducto(meso.getNombreproducto());
                        realm.commitTransaction();




//                        people4.add(meso);
                    }


                    Log.d("iooooe","orejacargardetalle");

                    cargardetalle();

                } catch (JSONException e) {

                }

            }

        }

    }
    private void  vaciardatosdedetallepedidorealm(){

        realm.beginTransaction();
        RealmResults<CarDb> results = realm.where(CarDb.class).findAll();
        results.deleteAllFromRealm();
        realm.commitTransaction();


    }
    public void realmgrbarenbasedatos(int iddetallepedido, int idproducto, int cantidad, Double precventa,  String nombreproducto, int idalmacen )
    {

        Realm realm = Realm.getDefaultInstance();


        Detallepedidorealm det = new Detallepedidorealm();

        det.setIddetallepedidorealm(idproducto);
        det.setIdproductorealm(idproducto);
        det.setCantidadrealm(cantidad);
        det.setPrecventarealm(precventa);
        det.setNombreproductorealm(nombreproducto);
        det.setIdalmacenrealm(idalmacen);

        realm.beginTransaction();
        realm.copyToRealm((Iterable<RealmModel>) det);
        realm.commitTransaction();

    }
}