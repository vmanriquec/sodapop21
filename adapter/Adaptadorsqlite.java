package com.food.sistemas.sodapopapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.food.sistemas.sodapopapp.R;
import com.food.sistemas.sodapopapp.modelo.Usuarios;

import java.util.List;

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
        public AdaptadorViewHolder(View v){
            super(v);
            this.id=(TextView) v.findViewById(R.id.cvid);
            this.nombre=(TextView) v.findViewById(R.id.cvnombre);
            this.clave=(TextView) v.findViewById(R.id.cvclave);
            this.almacen=(TextView) v.findViewById(R.id.cvalmacen);

        }
    }



    @Override
    public Adaptadorsqlite.AdaptadorViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v=LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tarjetacardview,viewGroup,false);
        return new AdaptadorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorViewHolder viewHolder, int position) {
Usuarios item=items.get(position);
        viewHolder.itemView.setTag(item);
        viewHolder.id.setText("Nº: ");
        viewHolder.nombre.setText("Nombre: " +  item.getNombreusuario());
        viewHolder.clave.setText("clave: " +  item.getClaveusuario());
        viewHolder.almacen.setText("almacen: " + item.getAlmacenusuario());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
