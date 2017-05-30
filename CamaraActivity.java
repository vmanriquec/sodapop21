package com.food.sistemas.sodapopapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v7.app.ActionBarActivity;

import android.support.v7.widget.RecyclerView;
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

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

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
    Spinner spinnerResponsable;
    DBHelper mydb;
    ImageView imagensqlites;
    private RecyclerView recycler;
    private Adaptadorsqlite adapter;
    private RecyclerView.LayoutManager lManager;
    private List<Usuarios> listaItemsUsuarios;
    private static int LOAD_IMAGE_RESULTS = 1;
    List itemso = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogonuevousuario);
        final TextView nombreusuario = (TextView) findViewById(R.id.nombreusuario);
        final TextView claveusuario = (TextView) findViewById(R.id.claveusuario);
        final TextView listasql = (TextView) findViewById(R.id.textsqlite);

        final String allmacenes[] = {"huaral ","chancay","huacho","barranca"};
        final Spinner salmacenes=(Spinner) findViewById(R.id.spinneralmacenes);

        imagensqlites=(ImageView) findViewById(R.id.imagensqlite2);




        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,allmacenes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view

        salmacenes.setAdapter(aa);
        mydb=new DBHelper(this);


        btnclick =(Button) findViewById(R.id.sqliteinsertar);
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (imagePath.isEmpty()){

                    Toast.makeText(CamaraActivity.this,"seleccione una foto por favor",Toast.LENGTH_LONG).show();
                }else {

                    Toast.makeText(CamaraActivity.this,"esoesloquehay"+imagePath,Toast.LENGTH_LONG).show();

                    String usuario = nombreusuario.getText().toString();
                    String clave = claveusuario.getText().toString();
                    String almacen = salmacenes.getItemAtPosition(salmacenes.getSelectedItemPosition()).toString();
                    String esdt = imagePath;
                    Boolean result = mydb.insertarUsuario(usuario, clave, almacen, esdt,imagePath );
                    Toast.makeText(CamaraActivity.this, imagePath, Toast.LENGTH_LONG).show();

                    if (result == true) {
                        Toast.makeText(CamaraActivity.this, "usuario insertado", Toast.LENGTH_LONG).show();

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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
