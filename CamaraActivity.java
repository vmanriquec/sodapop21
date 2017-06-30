package com.food.sistemas.sodapopapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v7.app.ActionBarActivity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.food.sistemas.sodapopapp.adapter.Adaptadorsqlite;
import com.food.sistemas.sodapopapp.database.DBHelper;
import com.food.sistemas.sodapopapp.modelo.Almacen;
import com.food.sistemas.sodapopapp.modelo.Usuarios;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.food.sistemas.sodapopapp.LoginActivity.CONNECTION_TIMEOUT;
import static com.food.sistemas.sodapopapp.LoginActivity.READ_TIMEOUT;

/**
 * Created by developer on 07/07/2016.
 */
public class CamaraActivity extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener {
    String imagePath="";
    TextView result;
    private Button showDialog,alert_edittext;
    private CharSequence[] items = null;
    private Resources res = null;
    private android.app.AlertDialog alert;


    Button cargarimagen,insert, show,  btnclick,btnclickver,btnrefrescar,imqagenclkick;
    Almacen mes;
    Spinner spinnerResponsable,spin;
    DBHelper mydb;
    ImageView imagensqlites;
    private RecyclerView recycler;
    private Adaptadorsqlite adapter;
    private RecyclerView.LayoutManager lManager;
    private List<Usuarios> listaItemsUsuarios;
    private static int LOAD_IMAGE_RESULTS = 1;
    List itemso = new ArrayList();

    private String[] strArrData = {"No Suggestions"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogonuevousuario);
        final TextView nombreusuario = (TextView) findViewById(R.id.nombreusuario);
        final TextView claveusuario = (TextView) findViewById(R.id.claveusuario);



        final Spinner spina=(Spinner) findViewById(R.id.spinnertask);
        imagensqlites=(ImageView) findViewById(R.id.imagensqlite2);

        new cargaralmacen().execute();


        mydb=new DBHelper(this);


        btnclick =(Button) findViewById(R.id.sqliteinsertar);
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (imagePath.isEmpty()){

                    Toast.makeText(CamaraActivity.this,"seleccione una foto por favor",Toast.LENGTH_LONG).show();
                }else {


                    String usuario = nombreusuario.getText().toString();
                    String clave = claveusuario.getText().toString();
                    String almacen = spin.getItemAtPosition(spin.getSelectedItemPosition()).toString();
                    String esdt = imagePath;
                    Boolean result = mydb.insertarUsuario(usuario, clave, almacen, esdt,imagePath );
                    Toast.makeText(CamaraActivity.this, imagePath, Toast.LENGTH_LONG).show();

                    if (result == true) {
                        Toast.makeText(CamaraActivity.this, "usuario insertado", Toast.LENGTH_LONG).show();
iramenu();
                    } else {
                        Toast.makeText(CamaraActivity.this, "nooo se completo la operacion", Toast.LENGTH_LONG).show();

                    }
                }      }
        });


        cargarimagen=(Button) findViewById(R.id.sqlitecargarimagen);




        cargarimagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);           }
        });


    }
    //recupera imagen desde galeria
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
// TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            Uri targetUri = data.getData();
            Bitmap bitmap;
            try {
                Uri pickedImage = data.getData();
                //ruta de imagen
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor cursor =this.getContentResolver().query(pickedImage, filePath, null, null, null);
                cursor.moveToFirst();
                imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

                bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(targetUri));
                imagensqlites.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }





    }
    private class cargaralmacen extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(CamaraActivity.this);
        HttpURLConnection conn;
        URL url = null;
        ArrayList<Almacen> listaalmacen = new ArrayList<Almacen>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {
            spin=(Spinner) findViewById(R.id.spinnertask);
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
                Toast.makeText(CamaraActivity.this,"no existen datos a mostrar",Toast.LENGTH_LONG).show();

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
                    ArrayAdapter<Almacen> adaptadorl= new ArrayAdapter<Almacen>(CamaraActivity.this, android.R.layout.simple_spinner_item,listaalmacen );
                    spin.setAdapter(adaptadorl);
                    Toast.makeText(CamaraActivity.this,"Almacenes cargados con Api Rest",Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                }

            }

        }

    }

    public void iramenu(){
        Intent i = new Intent(this, Menuprincipal.class);
        startActivity(i);

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
