package com.food.sistemas.sodapopapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import com.android.volley.RequestQueue;
import com.food.sistemas.sodapopapp.adapter.Adaptadorsqlite;
import com.food.sistemas.sodapopapp.database.DBHelper;
import com.food.sistemas.sodapopapp.database.UsuarioHelper;
import com.food.sistemas.sodapopapp.database.Usuariosql;
import com.food.sistemas.sodapopapp.modelo.Almacen;
import com.food.sistemas.sodapopapp.modelo.Usuarios;
import com.food.sistemas.sodapopapp.response.RedemnorteApiAdapter;
import com.food.sistemas.sodapopapp.response.ResponsableResponse;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ProfileFragment extends Fragment {
    String showUrl = "http://www.sodapop1978.pe.hu/apiandroidrecuperaalmacenes.php";
    String imagePath="";
    TextView result;
    RequestQueue requestQueue;
    Button cargarimagen,insert, show,  btnclick,btnclickver,btnrefrescar;
    Almacen mes;
    Spinner spinnerResponsable;
    DBHelper mydb;
    ImageView imagensqlites;
  private RecyclerView recycler;
    private Adaptadorsqlite adapter;
    private RecyclerView.LayoutManager lManager;
    private  List<Usuarios> listaItemsUsuarios;
    private static int LOAD_IMAGE_RESULTS = 1;
    List itemso = new ArrayList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usuariossqlite, container, false);
        //final Button btnrefrescar =(Button) view.findViewById(R.id.sqliterefrescar);

        final TextView nombreusuario = (TextView) view.findViewById(R.id.nombreusuario);
        final TextView claveusuario = (TextView) view.findViewById(R.id.claveusuario);
        final TextView listasql = (TextView) view.findViewById(R.id.textsqlite);

        final String allmacenes[] = {"huaral ","chancay","huacho","barranca"};
        final Spinner salmacenes=(Spinner) view.findViewById(R.id.spinneralmacenes);

        imagensqlites=(ImageView) view.findViewById(R.id.imagensqlite);


        final RecyclerView recycler = (RecyclerView) view.findViewById(R.id.reciclador);

        recycler.setHasFixedSize(true);



        ArrayAdapter <String> aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,allmacenes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view

        salmacenes.setAdapter(aa);




        mydb=new DBHelper(getContext());
        btnclick =(Button) view.findViewById(R.id.sqliteinsertar);
        btnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (imagePath.isEmpty()){

                    Toast.makeText(getContext(),"seleccione una foto por favor",Toast.LENGTH_LONG).show();
                }else {

                    //Toast.makeText(getContext(),"esoesloquehay"+imagePath,Toast.LENGTH_LONG).show();

                    String usuario = nombreusuario.getText().toString();
                    String clave = claveusuario.getText().toString();
                    String almacen = salmacenes.getItemAtPosition(salmacenes.getSelectedItemPosition()).toString();
                    String esdt = imagePath;
                    Boolean result = mydb.insertarUsuario(usuario, clave, almacen, esdt,imagePath );
                    Toast.makeText(getContext(), imagePath, Toast.LENGTH_LONG).show();

                    if (result == true) {
                        Toast.makeText(getContext(), "usuario insertado", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getContext(), "nooo se completo la operacion", Toast.LENGTH_LONG).show();

                    }
                }      }
        });
cargarimagen=(Button) view.findViewById(R.id.sqlitecargarimagen);








        btnclickver =(Button) view.findViewById(R.id.sqlitever);
        btnclickver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//verificar que seleciono la imagen osea que imagenpoath no sea vacio

                    Toast.makeText(getContext(),imagePath,Toast.LENGTH_LONG).show();

                    Cursor res =mydb.traerUsarios();
                    StringBuffer stringBuffer=new StringBuffer();
                    if (res!=null && res.getCount()>0){
                        while (res.moveToNext()){
                            stringBuffer.append("idusuario     :"+res.getString(0)+"\n");
                            stringBuffer.append("nombreusuario :"+res.getString(1)+"\n");
                            stringBuffer.append("claveusuario  :"+res.getString(2)+"\n");
                            stringBuffer.append("almacen       :"+res.getString(3)+"\n");
                            stringBuffer.append("imagen       :"+imagePath);
                            lManager = new LinearLayoutManager(getContext());
                            recycler.setLayoutManager(lManager);

                            itemso.add(new Usuarios( res.getString(0), res.getString(1),res.getString(2),res.getString(3),res.getString(4)) );
                        }
//listasql.setText(stringBuffer.toString());
                        Adaptadorsqlite adapterrecicler= new Adaptadorsqlite( itemso,getContext());
                        recycler.setAdapter(adapterrecicler);

                        imagePath="";
                    }else {
                        Toast.makeText(getContext(),"no hay datos",Toast.LENGTH_LONG).show();
                    }


            }
        });
        cargarimagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);           }
        });
        return view;
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
                Cursor cursor = getActivity().getContentResolver().query(pickedImage, filePath, null, null, null);
                cursor.moveToFirst();
               imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

                bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(targetUri));
                imagensqlites.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


}
