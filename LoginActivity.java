package com.food.sistemas.sodapopapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.food.sistemas.sodapopapp.modelo.Almacen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {

    CallbackManager callbackManager;
    LoginButton loginButton;
    Almacen mes;
    TextView result;

    String showUrl = "http://www.sodapop1978.pe.hu/apiandroidrecuperaalmacenes.php";
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();

        final Spinner list;
        list =(Spinner) findViewById(R.id.spinner2);
        final ArrayList<Almacen> listaalmacen = new ArrayList<Almacen>();

        loginButton=(LoginButton) findViewById(R.id.login_button);
        final Button normal;
        normal =(Button) findViewById(R.id.inicionormal);
        normal.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"Veificar usuarios",Toast.LENGTH_LONG);



            }
        });


        result = (TextView) findViewById(R.id.textView4);
        result.setText("afuera");
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




        requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                showUrl, new Response.Listener<JSONObject>() {
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

                    ArrayAdapter<Almacen> asa = new ArrayAdapter<Almacen>(LoginActivity.this, android.R.layout.simple_spinner_item,listaalmacen );
                    // return asa;

                    list.setAdapter(asa);
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
}


