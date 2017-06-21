package com.food.sistemas.sodapopapp;
import  android.content.Context;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

/**
 * Created by Perseo on 03/08/2014.
 */
public class Pruebaconexio  extends AppCompatActivity {

    ProgressDialog pds ;
    WifiManager wifiManager;
    String FileName ="myfile";
    String session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



      /* COMPROBAR KEYHASSH
        try {
              PackageInfo info = getPackageManager().getPackageInfo(
                    "com.food.sistemas.sodapopapp",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHashooooooo:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/
        setContentView(R.layout.splashverifica);
          if (verificarinternet()) {
               espera();
              pds.dismiss();
leershare();

                } else {
              mostraralerta();   }

                    }





        public void espera(){
pds= new ProgressDialog(Pruebaconexio.this);
                pds.setProgressStyle(ProgressDialog.STYLE_SPINNER); //Set style
                pds.setMessage("comprobando conexion a internet..."); //Message
                pds.setIndeterminate(true);
                pds.setCancelable(true);
                pds.show();}

    public void mostraralerta(){

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            this);

                    // set title
                    alertDialogBuilder.setTitle("No tienes Conexion a Internet!!");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("Deseas activar WIFI?")
                            .setCancelable(false)
                            .setPositiveButton("Si",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    //enable wifi
                                    wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                                    wifiManager.setWifiEnabled(true);
                               leershare();
                              }
                            })
                            .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    //disable wifi
                                    wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                                    wifiManager.setWifiEnabled(false);
                                    Intent salida=new Intent( Intent.ACTION_MAIN); //Llamando a la activity principal
                                    finish(); // La cerramos.
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                }

    public void pasar(){
        Intent i = new Intent(this, Menuprincipal.class);
        startActivity(i);

        finish();
    }
    public void pasaralogin(){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);


    }
    public Boolean verificarinternet() {
        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");
            int val           = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    private void leershare(){
        SharedPreferences sharedPreferences=getSharedPreferences(FileName, Context.MODE_PRIVATE);
        session=sharedPreferences.getString("sessionid","");
        String nomb=sharedPreferences.getString("sessionnombre","");
        String apepa=sharedPreferences.getString("sessionapepat","");
        String apema=sharedPreferences.getString("sessionapemat","");
        if (session.equals(null) || session.equals("")){

            pasaralogin();

        }else {
            Toast.makeText(this,"Hola "+ nomb +" tu session esta activa :)",Toast.LENGTH_LONG).show();
pasar();
        }}
}
