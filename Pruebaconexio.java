package com.food.sistemas.sodapopapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

/**
 * Created by Perseo on 03/08/2014.
 */
public class Pruebaconexio  extends AppCompatActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        //EditText nombreuser =(EditText)findViewById(R.id.editnombreusuario);
        /*try{
            new Tarea1().execute(nombreuser);

        }catch(Exception e){
            e.printStackTrace();
                    }*/
                 
            //boolean wifi = com.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
            if (internetvarmi()) {
                
                
                    //espera();
                   // mostraralerta("estado de conexion");
                    
                    
                } else {
                  espera();
                              }
            
                    
                    
                    
                    
                    }
        public void espera(){
            ProgressDialog pds = new ProgressDialog(Pruebaconexio.this);
                pds.setProgressStyle(ProgressDialog.STYLE_SPINNER); //Set style
                pds.setMessage("comprobando conexion a internet..."); //Message
                pds.setIndeterminate(true);
                pds.setCancelable(true);
                pds.show();}
                public void mostraralerta(String g){
                    
                        Dialog dia = new Dialog(Pruebaconexio.this);
                        //Başlık istemiyorsak bunu yazalım
                        dia.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        //arka plan transparan yapalım ki sadece layoutumuz gözüksün
                        dia.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        // göstreceğimiz layoutu ekleyelim
                        dia.setContentView(R.layout.dialogodealerta);
                        dia.setTitle(g);  // Bunu Aktif ederek Başlık Ekleyebilirsiniz
                        //şimdi gösterelim
                        dia.show();
                        
                }
                
        public boolean internetvarmi() {

                ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                if (conMgr.getActiveNetworkInfo() != null

                    && conMgr.getActiveNetworkInfo().isAvailable()

                    && conMgr.getActiveNetworkInfo().isConnected()) {

                        return true;

                    } else {

                        return false;

                    }

            }


}
