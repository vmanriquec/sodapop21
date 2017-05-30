package com.food.sistemas.sodapopapp.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.food.sistemas.sodapopapp.R;
import com.food.sistemas.sodapopapp.modelo.Usuarios;
import com.food.sistemas.sodapopapp.ui.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by usuario on 21/05/2017.
 */public class Adaptadorsqlite extends RecyclerView.Adapter<Adaptadorsqlite.AdaptadorViewHolder> {


    private  Context mainContext;
    private List<Usuarios> items;
    public Adaptadorsqlite(List<Usuarios> items,Context contexto){
        this.mainContext=contexto;
        this.items=items;

    }
    static class AdaptadorViewHolder extends RecyclerView.ViewHolder{

        protected TextView id;
        protected TextView nombre;
        protected TextView clave;
        protected TextView  almacen;
        protected ImageView imagennes;
        protected TextView imagente;
        protected Button editar;
        protected Button eliminar;
        ImageButton btnInfo;

        public AdaptadorViewHolder(View v){
            super(v);
            this.id=(TextView) v.findViewById(R.id.cvid);
            this.nombre=(TextView) v.findViewById(R.id.cvnombre);
            this.clave=(TextView) v.findViewById(R.id.cvclave);
            this.almacen=(TextView) v.findViewById(R.id.cvalmacen);
            this.imagennes=(ImageView) v.findViewById(R.id.imagensqlite);
this.editar=(Button) v.findViewById(R.id.sqliteeditar);
            this.eliminar=(Button) v.findViewById(R.id.sqliteeliminar);


        }
    }



    @Override
    public Adaptadorsqlite.AdaptadorViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tarjetacardview,viewGroup,false);
        return new AdaptadorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AdaptadorViewHolder viewHolder, final int position) {
        final Usuarios item = items.get(position);
        viewHolder.itemView.setTag(item);
        viewHolder.id.setText("Idº: " + item.getIdusuario());
        viewHolder.nombre.setText("Nombre: " + item.getNombreusuario());
        viewHolder.clave.setText("clave: " + item.getClaveusuario());
               {
        };
        viewHolder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), item.getNombreusuario() + " is selected!", Toast.LENGTH_SHORT).show();

            }
        });
        String p = item.getImagen();
        viewHolder.almacen.setText("Almacen: " + item.getAlmacenusuario());
        FileInputStream in;
        BufferedInputStream buf;
        try {
            in = new FileInputStream(p);
            buf = new BufferedInputStream(in);
            Bitmap bMap = BitmapFactory.decodeStream(buf);

            // viewHolder.imagennes.setImageBitmap(BitmapFactory.decodeFile(item.getImagen()));

            viewHolder.imagennes.setImageBitmap(bMap);

            viewHolder.btnInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 }
            });
            if (in != null) {
                in.close();
            }
            if (buf != null) {
                buf.close();
            }
        } catch (Exception e) {
            Log.e("Error reading file", e.toString());
        }


/*
        File f = new File(p);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),options);
               viewHolder.imagennes.setImageBitmap(bitmap);

*/

       // viewHolder.imagennes.setImageBitmap(BitmapFactory.decodeFile(item.getImagen()));
      //  viewHolder.imagennes.setImageURI(Uri.fromFile(new File(item.getImagen())));
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
