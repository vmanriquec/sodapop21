package com.food.sistemas.sodapopapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.food.sistemas.sodapopapp.modelo.Almacen;
import com.food.sistemas.sodapopapp.modelo.Mesas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.food.sistemas.sodapopapp.LoginActivity.CONNECTION_TIMEOUT;
import static com.food.sistemas.sodapopapp.LoginActivity.READ_TIMEOUT;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:33
 * Mail: specialcyci@gmail.com
 */
public class HomeFragment extends  Fragment {
    String FileName ="myfile";
    private View view;
     private String[] strArrData = {"No Suggestions"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout, container, false);
        Spinner spin;
        Resources res = getResources();
        SharedPreferences prefs = getActivity().getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String nombre = prefs.getString("sessionnombre", "");




        TextView mesero=(TextView)view.findViewById(R.id.textViewmesero);
      mesero.setText(nombre);


       new cargarmesas().execute();




        final TabHost tabs = (TabHost) view.findViewById(android.R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("mitabs1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Orden",
                res.getDrawable(android.R.drawable.ic_input_add));
        tabs.addTab(spec);

        spec = tabs.newTabSpec("mitabs2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Menu ", res.getDrawable(android.R.drawable.ic_dialog_map));
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
        tabs.setCurrentTab(3);
*/

        return view;


    }

    private class cargarmesas extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(HomeFragment.this.getContext());
        HttpURLConnection conn;
        URL url = null;
        ArrayList<Mesas> listaalmacen = new ArrayList<Mesas>();

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
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");
                conn.setDoOutput(true);
                conn.connect();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }
            try {
                int response_code = conn.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK) {

                    InputStream input = conn.getInputStream();
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
                conn.disconnect();
            }
        }
        @Override
        protected void onPostExecute(String result) {

            ArrayList<String> dataList = new ArrayList<String>();
            Mesas mes;
            if(result.equals("no rows")) {
                Toast.makeText(HomeFragment.this.getContext(),"no existen datos a mostrar",Toast.LENGTH_LONG).show();

            }else{

                try {

                    JSONArray jArray = new JSONArray(result);

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        dataList.add(json_data.getString("numeromesa"));

                        Log.d("TAGito", json_data.getString("estadomesa"));
                        mes = new Mesas(json_data.getInt("idmesas"), json_data.getInt("numeromesa"), json_data.getString("estadomesa"), json_data.getInt("sillasmesa"));
                        listaalmacen.add(mes);
                    }
                    strArrData = dataList.toArray(new String[dataList.size()]);
                    ArrayAdapter<Mesas> adaptadorl= new ArrayAdapter<Mesas>(HomeFragment.this.getContext(), android.R.layout.simple_spinner_item,listaalmacen );
                   Spinner spin=(Spinner)view.findViewById(R.id.spinnermesas);
                    spin.setAdapter(adaptadorl);

                } catch (JSONException e) {
                }

            }

        }

    }

}
