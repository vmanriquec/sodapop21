package com.food.sistemas.sodapopapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.food.sistemas.sodapopapp.modelo.Almacen;
import com.food.sistemas.sodapopapp.response.RedemnorteApiAdapter;
import com.food.sistemas.sodapopapp.response.ResponsableResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    CallbackManager callbackManager;
    LoginButton loginButton;
    Almacen mes;
    TextView result;
    Button boton;
    Spinner spineralmaceno;

String sessionusuario;
    RequestQueue requestQueue;



    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private SimpleCursorAdapter myAdapter;
    private ProfileTracker mProfileTracker;
    SearchView searchView = null;
    private String[] strArrData = {"No Suggestions"};
String FileName ="myfile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();

leershare();
        final ArrayList<Almacen> listaalmacen = new ArrayList<Almacen>();

        new cargaralmacen().execute();

        loginButton=(LoginButton) findViewById(R.id.login_button);

       // boton = (Button) findViewById(R.id.btnnormal);
        //boton.setOnClickListener(this);


        loginButton.setReadPermissions("user_friends");
        loginButton.setReadPermissions("public_profile");
        loginButton.setReadPermissions("email");


        loginButton.setReadPermissions("user_birthday");
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                if(Profile.getCurrentProfile() == null) {
                    mProfileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                            // profile2 is the new profile

                            mProfileTracker.stopTracking();
                          //  Log.v("facebook - profile", profile2.getFirstName());
                            Log.v("facebook - id", profile2.getId());
                            //Log.v("facebook - profile", profile2.getLastName());
                            //Log.v("facebook - profile", profile2.getName());
sessionusuario=profile2.getId();
                            Toast.makeText(getApplicationContext(),"sessipojn iniciada con id"+sessionusuario, Toast.LENGTH_SHORT).show();

                            guardarshare(sessionusuario);
                        }
                    };
                    // no need to call startTracking() on mProfileTracker
                    // because it is called by its constructor, internally.
                    ir();
                }
                else {
                    Profile profile = Profile.getCurrentProfile();
                    //Log.v("facebook - profile", profile.getFirstName());




                }



            }

            @Override
            public void onCancel() {
              //  Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                //Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null){
                    limpiarshared();
                }
            }
        };
        loginButton = (LoginButton) findViewById(R.id.login_button);

        setContentView(R.layout.activity_login);





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }




private void ir(){
Intent intent= new Intent(this,Vaio.class);
  startActivity(intent);
    }


    @Override
    public void onClick(View view) {

    }
    public void buttonClick(View view) {
        if (view.getId() == R.id.btnnormal) {

           mostraralert();



        }
    }

    public void mostraralert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        alertDialog.setView(inflater.inflate(R.layout.dialgologin, null));

/* When positive (yes/ok) is clicked */
        alertDialog.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                dialog.cancel(); // Your custom code
            }
        });
/* When negative (No/cancel) button is clicked*/
        alertDialog.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel(); // Your custom code
            }
        });
        alertDialog.show();

    }



    // Create class AsyncFetch

    private class cargaralmacen extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(LoginActivity.this);
        HttpURLConnection conn;
        URL url = null;
        ArrayList<Almacen> listaalmacen = new ArrayList<Almacen>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {
            spineralmaceno=(Spinner) findViewById(R.id.spinalmacen);

            try {

                // Enter URL address where your php file resides or your JSON file address
                url = new URL("http://sodapop.ga/sugest/fetch-all-fish.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we receive data
                conn.setDoOutput(true);
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
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

            //this method will be running on UI thread
            ArrayList<String> dataList = new ArrayList<String>();

            Almacen mes;

            if(result.equals("no rows")) {


                Toast.makeText(LoginActivity.this,"no existen datos a mostrar",Toast.LENGTH_LONG).show();

            }else{

                try {

                    JSONArray jArray = new JSONArray(result);
                    // Extract data from json and store into ArrayList
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        dataList.add(json_data.getString("nombrealm"));

                        Toast.makeText(LoginActivity.this,json_data.getString("nombrealm"),Toast.LENGTH_LONG).show();

                        mes = new Almacen(json_data.getInt("idalmacen"), json_data.getString("nombrealm"), json_data.getString("telfonoalm"), json_data.getString("correoalm"));
                        listaalmacen.add(mes);

                    }

                    strArrData = dataList.toArray(new String[dataList.size()]);



                    ArrayAdapter<Almacen> adaptadorl= new ArrayAdapter<Almacen>(LoginActivity.this, android.R.layout.simple_spinner_item,listaalmacen );
                    spineralmaceno.setAdapter(adaptadorl);
                } catch (JSONException e) {
                    // You to understand what actually error is and handle it appropriately

                }

            }

        }

    }
    private  void guardarshare(String sesion){
        SharedPreferences sharedPreferences =getSharedPreferences(FileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("nombresession",sesion);
        editor.commit();
        Toast.makeText(this,"guardadoooooooooooo"+sesion,Toast.LENGTH_LONG).show();
    }
private void leershare(){
    SharedPreferences sharedPreferences=getSharedPreferences(FileName,Context.MODE_PRIVATE);
      String session=sharedPreferences.getString("nombresession","");
    if (session.equals(null) || session.equals("")){
        Toast.makeText(this,"no existe session::::::"+session,Toast.LENGTH_LONG).show();
    }else {
        Toast.makeText(this,"ya habias inicioado session en   "+session,Toast.LENGTH_LONG).show();
        ir();
    }}
private void limpiarshared(){
        SharedPreferences sharedPreferences =getSharedPreferences(FileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
    editor.clear();
    editor.commit();}

}





