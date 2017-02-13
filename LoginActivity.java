package com.food.sistemas.sodapopapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.food.sistemas.sodapopapp.modelo.Almacen;
import com.food.sistemas.sodapopapp.response.RedemnorteApiAdapter;
import com.food.sistemas.sodapopapp.response.ResponsableResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    CallbackManager callbackManager;
    LoginButton loginButton;
    Almacen mes;
    TextView result;
    Button boton;
    Spinner spineralmacen;


    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        final Spinner list;

        final ArrayList<Almacen> listaalmacen = new ArrayList<Almacen>();
        spineralmacen=(Spinner) findViewById(R.id.spinalmacen);


        loginButton=(LoginButton) findViewById(R.id.login_button);

       // boton = (Button) findViewById(R.id.btnnormal);
        //boton.setOnClickListener(this);



        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                ir();

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

           logout();



        }
    }

    public void logout(){
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

    private void poblarSpinnerResponsables(ArrayList<Almacen> almacen) {

        List<String> list = new ArrayList<String>();
        for (Almacen r : almacen) {
            list.add(r.getNombrealm());
            Toast.makeText(LoginActivity.this,r.getNombrealm(),Toast.LENGTH_LONG);
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list);
        //spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spineralmacen.setAdapter(spinnerArrayAdapter);
    }

    private void obtenerDatosResponsables() {
        Call<ResponsableResponse> call = RedemnorteApiAdapter.getApiService().getResponsables();
        call.enqueue(new LoginActivity.ResponsablesCallback());
    }

    class ResponsablesCallback implements Callback<ResponsableResponse> {

        @Override
        public void onResponse(Call<ResponsableResponse> call, Response<ResponsableResponse> response) {
            if (response.isSuccessful()) {
                ResponsableResponse responsableResponse = response.body();
                poblarSpinnerResponsables(responsableResponse.getResponsables());
            }   else {
                Toast.makeText(LoginActivity.this, "Error en el formato de respuesta", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponsableResponse> call, Throwable t) {
            Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}




