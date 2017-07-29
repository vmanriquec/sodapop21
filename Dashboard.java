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
import com.food.sistemas.sodapopapp.adapter.Adaptadordashboard;
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
import java.io.File;
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
public class Dashboard extends  Fragment implements   View.OnClickListener,RecyclerView.OnItemTouchListener  {
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
    private RecyclerView recycler,recycler2,recycler3;
    private RecyclerView.Adapter adapter,adapter2,adapter3;
    private RecyclerView.LayoutManager lManager,lManager2,lManager3;
    TextView fechadehoy;
    ArrayList<Productos> people=new ArrayList<>();
    ArrayList<Detallepedido> people2=new ArrayList<>();
    ArrayList<Dashboardpedido> people3=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dashboard, container, false);

        Resources res = getResources();
        prefs = getActivity().getSharedPreferences(FileName, Context.MODE_PRIVATE);
        face=prefs.getString("facebook","");

        String customFont = "Arbutus.ttf";
        typeface = Typeface.createFromAsset(getApplicationContext().getAssets(), customFont);

        //RECICLER  PEDIDO
        recycler3 = (RecyclerView) view.findViewById(R.id.cardalmacenesis);
        recycler3.setHasFixedSize(true);
        lManager3 = new GridLayoutManager(getApplicationContext(),2);
        recycler3.setLayoutManager(lManager3);


        for (int w=1;w<4;w++ ){

            new traerpedidosadashboard().execute(String.valueOf(w));


        }





        return view;


    }


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

    @Override
    public void onClick(View v) {

    }

    private class traerpedidosadashboard extends AsyncTask<String, String, String> {

        HttpURLConnection conne;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {

            try {
                url = new URL("http://sodapop.ga/sugest/traerpedidosalmacenes.php");
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

                        .appendQueryParameter("idalmacen", params[0]);
                Log.d("pedozazova",params[0]);
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
            Log.d("ped",result);



            ArrayList<String> dataList = new ArrayList<String>();
            Dashboardpedido meso;
            if(result.equals("no rows")) {
                Toast.makeText(getApplicationContext(),"no existen datos a mostrar",Toast.LENGTH_LONG).show();

            }else{

                try {


                    JSONArray jArray = new JSONArray(result);


                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.optJSONObject(i);

                        meso = new Dashboardpedido( json_data.getDouble("totalentradas"), json_data.getString("nombrealm"),json_data.getDouble("totalsalidas"),json_data.getDouble("totalpedidos"));
                        people3.add(meso);
                        adapter3 = new Adaptadordashboard(people3,getApplicationContext());
                    }

                    recycler3.setAdapter(adapter3);

                } catch (JSONException e) {

                }

            }

        }

    }

}