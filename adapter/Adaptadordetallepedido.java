package com.food.sistemas.sodapopapp.adapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.food.sistemas.sodapopapp.CarDb;
import com.food.sistemas.sodapopapp.R;
import com.food.sistemas.sodapopapp.modelo.Detallepedido;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.facebook.FacebookSdk.getApplicationContext;
public class Adaptadordetallepedido extends RecyclerView.Adapter<Adaptadordetallepedido.AdaptadorViewHolder> {
    private Context mainContext;
    SharedPreferences prefs;String FileName ="myfile";
    private List<Detallepedido> items;
    ArrayList<Detallepedido> Detallepedido=new ArrayList<>();
    Detallepedido objDetallepedido;
    Realm realm = Realm.getDefaultInstance();
    public Adaptadordetallepedido(List<Detallepedido> items, Context contexto){
        this.mainContext=contexto;
        this.items=items;
        prefs = getApplicationContext().getSharedPreferences(FileName, Context.MODE_PRIVATE);
        String idalmacenactiv = prefs.getString("idalmacenactivo", "");
    }
    static class AdaptadorViewHolder extends RecyclerView.ViewHolder{
        protected TextView productonombre;
        protected TextView idproducto;
        protected TextView productoprecio;
        protected TextView productoingredientes;
        protected ImageView productoimagen;
        protected TextView cantidadpedida;
        protected CheckBox michek;
        protected LinearLayout masmenos;
        protected Button mas,botonok;
        protected Button  menos;
        protected Button eliminar;

        public AdaptadorViewHolder(View v){
            super(v);
            this.productonombre=(TextView) v.findViewById(R.id.productonombre1);
            this.productoprecio=(TextView) v.findViewById(R.id.productoprecio1);
            this.idproducto=(TextView) v.findViewById(R.id.idproducto1);
            this.cantidadpedida=(TextView) v.findViewById(R.id.contidadpedida1);
            this.productoingredientes=(TextView) v.findViewById(R.id.productoingredientes1);
            this.productoimagen=(ImageView) v.findViewById(R.id.productoimagen1);
            this.michek=(CheckBox) v.findViewById(R.id.myCheckBox1);
            this.masmenos=(LinearLayout)v.findViewById(R.id.masmenos1);
            this.mas=(Button)v.findViewById(R.id.plus_button1);
            this.menos=(Button)v.findViewById(R.id.menos_button1);
            this.botonok=(Button)v.findViewById(R.id.botonok1);
            this.eliminar=(Button)v.findViewById(R.id.eliminar1);
        }
    }
    @Override
    public Adaptadordetallepedido.AdaptadorViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tarjetarealmdetallepedido,viewGroup,false);
        return new Adaptadordetallepedido.AdaptadorViewHolder(v);
    }
    private int idproductorealm;
    private int cantidadrealm;
    private Double precventarealm;

    private String nombreproductorealm;
    private int idalmacenrealm;
    @Override
    public void onBindViewHolder(final Adaptadordetallepedido.AdaptadorViewHolder viewHolder, final int position) {
        final Detallepedido item = items.get(position);
         // Toast.makeText(getApplicationContext(),item.getNombreproducto()+viewHolder.cantidadpedida.getText(),Toast.LENGTH_LONG).show();

        viewHolder.itemView.setTag(item);
        viewHolder.productonombre.setText(item.getNombreproducto());
          viewHolder.productoprecio.setText(String.valueOf(item.getPrecventa()));
        viewHolder.idproducto.setText(String.valueOf(item.getIdproducto()));
        //viewHolder.michek.setVisibility(View.GONE);
        viewHolder.cantidadpedida.setText(String.valueOf(item.getCantidad()));
        viewHolder.masmenos.setVisibility(View.GONE);
        viewHolder.botonok.setVisibility(View.GONE);
        viewHolder.eliminar.setVisibility(View.GONE);
        double pr =item.getPrecventa();
        int cantped=Integer.parseInt( viewHolder.cantidadpedida.getText().toString());

        double j=cantped;
        Double subto=pr*j;

        viewHolder.productoingredientes.setText(String.valueOf(subto));


/*asignar imagen desde url*/

String foto=item.getImagen().toString();

        Picasso.with(getApplicationContext()) .load(foto).transform(new CropCircleTransformation()).resize(100, 100)
                .into( viewHolder.productoimagen);
  /*boton mas o menos cantidad*/
        viewHolder.mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cantidad=viewHolder.cantidadpedida.getText().toString();
                int c=Integer.parseInt(cantidad);
                if(c>=0){
                    c=c+1;viewHolder.cantidadpedida.setText( String.valueOf(c));
                    double pr =item.getPrecventa();
                    int cantped=Integer.parseInt( viewHolder.cantidadpedida.getText().toString());

                    double j=cantped;
                    Double subto=pr*j;

                    viewHolder.productoingredientes.setText(String.valueOf(subto));
                    // Toast.makeText(getApplicationContext(),item.getNombreproducto()+viewHolder.cantidadpedida.getText(),Toast.LENGTH_LONG).show();


                }            }
        });

        viewHolder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                realm.where(CarDb.class).equalTo("iddetallepedidorealm", item.getIdproducto()).findFirst().deleteFromRealm();
                realm.beginTransaction();
                realm.commitTransaction();



                items.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());


                }
        });
        viewHolder.menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cantidad=viewHolder.cantidadpedida.getText().toString();
                int c=Integer.parseInt(cantidad);
                if(c>0){
                    c=c-1;

                    viewHolder.cantidadpedida.setText( String.valueOf(c));
                    double pr =item.getPrecventa();
                    int cantped=Integer.parseInt( viewHolder.cantidadpedida.getText().toString());

                    double j=cantped;
                    Double subto=pr*j;

                    viewHolder.productoingredientes.setText(String.valueOf(subto));
                    //   Toast.makeText(getApplicationContext(),item.getNombreproducto()+viewHolder.cantidadpedida.getText(),Toast.LENGTH_LONG).show();

                }            }
        });


        /*si esta check activo para aumentar cantidad*/


        viewHolder.michek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked())
                {
                    viewHolder.cantidadpedida.setVisibility(View.VISIBLE);
                    viewHolder.cantidadpedida.setEnabled(true);
                    viewHolder.masmenos.setVisibility(View.VISIBLE);
                    viewHolder.botonok.setVisibility(View.VISIBLE);
                    viewHolder.eliminar.setVisibility(View.VISIBLE);


                }
                if(!buttonView.isChecked())
                {

                    viewHolder.cantidadpedida.setEnabled(false);
                    viewHolder.masmenos.setVisibility(View.GONE);
                    viewHolder.botonok.setVisibility(View.GONE);
                    viewHolder.cantidadpedida.setText( "0");
                    viewHolder.eliminar.setVisibility(View.GONE);
                }

            }
        });



        viewHolder.botonok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idp=Integer.parseInt(viewHolder.idproducto.getText().toString());


                double pr =item.getPrecventa();
                int cantped=Integer.parseInt( viewHolder.cantidadpedida.getText().toString());

                double j=cantped;
                Double subto=pr*j;

                String t=item.getNombreproducto().toString();
                prefs = getApplicationContext().getSharedPreferences(FileName, Context.MODE_PRIVATE);
                String idalmacenactiv = prefs.getString("idalmacenactivo", "");
                int i=Integer.parseInt(idalmacenactiv);

               /* if (verificarsiexiste(idp)){
                    Detallepedido results = realm.where(Detallepedido.class)
                            .equalTo("idDetallepedido", idp)
                            .findFirst();
                    results.setCantidadrealm(cantped);
                    realm.beginTransaction();
                    realm.commitTransaction();

                }
                else {
                    realmgrbarenbasedatos(t,cantped,pr,idp);
                }
*/
                Detallepedido.add(objDetallepedido);
                // viewHolder.cantidadpedida.setEnabled(false);
                viewHolder.masmenos.setVisibility(View.GONE);
                viewHolder.botonok.setVisibility(View.GONE);

                viewHolder.michek.setChecked(false);
                viewHolder.cantidadpedida.setText( String.valueOf(cantped));
                for (int x = 0; x < Detallepedido.size(); x++)
                {

                    // Toast.makeText(getApplicationContext(),Detallepedido.get(x).getNombreproducto()+Detallepedido.get(x).getCantidad(),Toast.LENGTH_SHORT).show();

                }


            }
        });


    }


   /* public Boolean  verificarsiexiste(int idproducto){

        RealmResults<Detallepedidorealm> results = realm.where(Detallepedidorealm.class)
                .equalTo(String.valueOf(Detallepedidorealm.idDetallepedidorealm), idproducto)
                .findAll();
        if(results.size()>0){

            return  true;
        }else {
            return false;
        }


    }
    public void realmrecuperarundato(){

        Realm realm = Realm.getDefaultInstance();

        RealmResults<CarDb> results = realm.where(CarDb.class)
                //.equalTo(CarDb.K_CAR_PLATE_NUMBER, idproducto)
                //.or()
                //.equalTo(CarDb.K_CAR_PLATE_NUMBER, "3333-XXX")
                .findAll();

        Toast.makeText(getApplicationContext(),"cantidad de datos"+String.valueOf(results.size()),Toast.LENGTH_SHORT).show();
    }
*/

    /* Realm realm = Realm.getDefaultInstance();Begin transaction
    realm.beginTransaction();
    
        //Init the element
        CarDb car = realm.createObject(CarDb.class);
    
    car.setPlateNumber("1111-SSS");
    
    car.setBrand("Citroen");
    
    car.setModel("ZX");
    
    //Commit transaction
    realm.commitTransaction();*/


    @Override
    public int getItemCount() {
        return items.size();
    }
}
