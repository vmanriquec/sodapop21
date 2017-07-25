package com.food.sistemas.sodapopapp.adapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.food.sistemas.sodapopapp.CarDb;
import com.food.sistemas.sodapopapp.HomeFragment;
import com.food.sistemas.sodapopapp.R;
import com.food.sistemas.sodapopapp.Realm.Detallepedidorealm;
import com.food.sistemas.sodapopapp.modelo.Dashboardpedido;
import com.food.sistemas.sodapopapp.modelo.Detallepedido;
import com.food.sistemas.sodapopapp.modelo.Productos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.CropSquareTransformation;

import static com.facebook.FacebookSdk.getApplicationContext;
public class Adaptadordashboard extends RecyclerView.Adapter<Adaptadordashboard.AdaptadorViewHolder> {
    private Context mainContext;
    String foto;
    SharedPreferences prefs;String FileName ="myfile";
    private List<Dashboardpedido> items;


    public Adaptadordashboard(List<Dashboardpedido> items, Context contexto){
        this.mainContext=contexto;
        this.items=items;
          }
    static class AdaptadorViewHolder extends RecyclerView.ViewHolder{
        protected TextView totalpedido;
        protected TextView totalentradas;
        protected TextView totalsalidas;
        protected TextView nombrealmacen;
        protected TextView totalneto;


        public AdaptadorViewHolder(View v){
            super(v);
            this.totalentradas=(TextView) v.findViewById(R.id.totalentradas);
            this.totalsalidas=(TextView) v.findViewById(R.id.totalsalidas);
            this.totalpedido=(TextView) v.findViewById(R.id.totalpedidos);
            this.totalneto=(TextView) v.findViewById(R.id.totalneto);
            this.nombrealmacen=(TextView) v.findViewById(R.id.txtcardalmacen);

        }
    }
    @Override
    public Adaptadordashboard.AdaptadorViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tarjetaalmacenes,viewGroup,false);
        return new Adaptadordashboard.AdaptadorViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final Adaptadordashboard.AdaptadorViewHolder viewHolder, final int position) {
        final Dashboardpedido item = items.get(position);

        viewHolder.totalentradas.setText(String.valueOf(item.getTotalentradas()));
        viewHolder.totalpedido.setText(String.valueOf(item.gettotalpedidos()));
        viewHolder.totalsalidas.setText(String.valueOf(item.getTotalsalidas()));
        viewHolder.totalneto.setText(String.valueOf((item.getTotalentradas()+item.getTotalentradas()-item.getTotalsalidas())));
        viewHolder.nombrealmacen.setText(item.getNombrealm());


    }




    @Override
    public int getItemCount() {
        return items.size();
    }
}
