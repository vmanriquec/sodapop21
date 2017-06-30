package com.food.sistemas.sodapopapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.food.sistemas.sodapopapp.modelo.Almacen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
import java.util.ArrayList;
import java.util.Arrays;

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
private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


String sessionusuario,sessionnombre,sessionapemat,sessionapepat,correo;
    RequestQueue requestQueue;
    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private SimpleCursorAdapter myAdapter;
    private ProfileTracker mProfileTracker;
    Spinner spinnerResponsable,spin;
    SearchView searchView = null;
    private String[] strArrData = {"No Suggestions"};
String FileName ="myfile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new LoginActivity.cargaralmacen().execute();

  callbackManager = CallbackManager.Factory.create();
leershare();
final TextView nombreuser=(TextView) findViewById(R.id.phpnombreusuario);
        final TextView claveusuario=(TextView) findViewById(R.id.phpclaveusuario);

        Button loginadminist=(Button) findViewById(R.id.phpbtnloginphp);

        loginadminist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Hola :) ", Toast.LENGTH_SHORT).show();

            }
        });
        new LoginActivity.cargaralmacen().execute();

        loginButton=(LoginButton) findViewById(R.id.login_button);
       // LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));

        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if(Profile.getCurrentProfile() == null) {
                    mProfileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile profile, Profile profile2) {
                             mProfileTracker.stopTracking();
                            sessionusuario=profile2.getId();
                            sessionnombre=profile2.getName();
                            sessionapepat=profile2.getFirstName();
                            sessionapemat=profile2.getLastName();

                            Toast.makeText(getApplicationContext(),"Hola :) "+sessionnombre+" Disfruta la Aplicacion", Toast.LENGTH_SHORT).show();
                            guardarshare(sessionusuario,sessionnombre,sessionapepat,sessionapemat);
                        }
                    };


                    handlefacebookaccestocken(loginResult.getAccessToken());

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
                Toast.makeText(getApplicationContext(), "Ups! hay un error con facebook", Toast.LENGTH_SHORT).show();
            }
        });
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {

                if (currentAccessToken == null){
                }
            }
        };
        loginButton = (LoginButton) findViewById(R.id.login_button);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuthListener=new FirebaseAuth.AuthStateListener()
        {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if (user !=null){

                    ir();
                }
            }
        };


    }
    @Override
    protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);


    }
    @Override
    protected void onStop(){

        super.onStop();
        firebaseAuth.removeAuthStateListener(firebaseAuthListener);

    }
//si hay errores en el logueo de firebase aqui lo mkostramos
    private void handlefacebookaccestocken(AccessToken accessToken) {
        AuthCredential credencial= FacebookAuthProvider.getCredential((accessToken.getToken()));
        firebaseAuth.signInWithCredential(credencial).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful()){
    Toast.makeText(getApplicationContext(),"logueo exitoso en firebase",Toast.LENGTH_LONG).show();


}
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

private void ir(){
Intent intent= new Intent(this,Menuprincipal.class);
  startActivity(intent);

    }
    @Override
    public void onClick(View view) {

    }

    public void mostraralert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        alertDialog.setView(inflater.inflate(R.layout.dialgologin, null));
      alertDialog.setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                dialog.cancel(); // Your custom code
            }
        });
     alertDialog.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel(); // Your custom code
            }
        });
        alertDialog.show();
    }
      private  void guardarshare(String idusuario,String nombre,String apepat,String apemat){
        SharedPreferences sharedPreferences =getSharedPreferences(FileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("sessionid",idusuario);
        editor.putString("sessionnombre",nombre);
        editor.putString("sessionapepat",apepat);
        editor.putString("sessionapemat",apemat);

        editor.commit();
        Toast.makeText(this,"Session Guardada"+idusuario+nombre+apepat+apemat,Toast.LENGTH_LONG).show();
    }
private void leershare(){
    SharedPreferences sharedPreferences=getSharedPreferences(FileName,Context.MODE_PRIVATE);
      String session=sharedPreferences.getString("sessionid","");
    String nomb=sharedPreferences.getString("sessionnombre","");
    String apepa=sharedPreferences.getString("sessionapepat","");
    String apema=sharedPreferences.getString("sessionapemat","");
    if (session.equals(null) || session.equals("")){
        //Toast.makeText(this,"no existe session::::::"+session,Toast.LENGTH_LONG).show();
    }else {
        Toast.makeText(this,"Session activa "+nomb,Toast.LENGTH_LONG).show();
        ir();
    }}





    private class AsyncLogin extends AsyncTask<String, String, String>
    {
        ProgressDialog pdLoading = new ProgressDialog(LoginActivity.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();

        }
        @Override
        protected String doInBackground(String... params) {
            try {

                // Enter URL address where your php file resides
                url = new URL("http://localhost/test/login.inc.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return "exception";
            }
            try {
                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                // setDoInput and setDoOutput method depict handling of both send and receive
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Append parameters to URL
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("username", params[0])
                        .appendQueryParameter("password", params[1]);
                String query = builder.build().getEncodedQuery();

                // Open connection for sending data
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return "exception";
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
                    return(result.toString());

                }else{

                    return("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            } finally {
                conn.disconnect();
            }


        }

        @Override
        protected void onPostExecute(String result) {

            //this method will be running on UI thread

            pdLoading.dismiss();

            if(result.equalsIgnoreCase("true"))
            {
                /* Here launching another activity when login successful. If you persist login state
                use sharedPreferences of Android. and logout button to clear sharedPreferences.
                 */

                Intent intent = new Intent(LoginActivity.this,Menuprincipal.class);
                startActivity(intent);
                LoginActivity.this.finish();

            }else if (result.equalsIgnoreCase("false")){

                // If username and password does not match display a error message
                Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();

            } else if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {

                Toast.makeText(LoginActivity.this, "OOPs! Something went wrong. Connection Problem.", Toast.LENGTH_LONG).show();

            }
        }

    }

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
            spin=(Spinner) findViewById(R.id.spinnerio);
            try {
                url = new URL("http://sodapop.ga/sugest/fetch-all-fish.php");
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
            Almacen mes;
            if(result.equals("no rows")) {
                Toast.makeText(LoginActivity.this,"no existen datos a mostrar",Toast.LENGTH_LONG).show();

            }else{

                try {

                    JSONArray jArray = new JSONArray(result);

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        dataList.add(json_data.getString("nombrealm"));


                        mes = new Almacen(json_data.getInt("idalmacen"), json_data.getString("nombrealm"), json_data.getString("telfonoalm"), json_data.getString("correoalm"));
                        listaalmacen.add(mes);
                    }
                    strArrData = dataList.toArray(new String[dataList.size()]);

                    ArrayAdapter<Almacen> adaptadorl= new ArrayAdapter<Almacen>(LoginActivity.this, android.R.layout.simple_spinner_item,listaalmacen );

                    spin.setAdapter(adaptadorl);


                } catch (JSONException e) {
                }

            }

        }

    }



}






