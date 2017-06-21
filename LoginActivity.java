package com.food.sistemas.sodapopapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Base64;
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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


String sessionusuario,sessionnombre,sessionapemat,sessionapepat,correo;
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

}






