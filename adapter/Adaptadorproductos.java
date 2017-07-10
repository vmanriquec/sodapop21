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
import com.food.sistemas.sodapopapp.modelo.Productos;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import static com.facebook.FacebookSdk.getApplicationContext;
public class Adaptadorproductos extends RecyclerView.Adapter<Adaptadorproductos.AdaptadorViewHolder> {
    private Context mainContext;
    SharedPreferences prefs;String FileName ="myfile";
    private List<Productos> items;
    ArrayList<Detallepedido> detallepedido=new ArrayList<>();
    Detallepedido objdetallepedido;
    public Adaptadorproductos(List<Productos> items, Context contexto){
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

        public AdaptadorViewHolder(View v){
            super(v);
            this.productonombre=(TextView) v.findViewById(R.id.productonombre);
            this.productoprecio=(TextView) v.findViewById(R.id.productoprecio);
            this.idproducto=(TextView) v.findViewById(R.id.idproducto);
            this.cantidadpedida=(TextView) v.findViewById(R.id.contidadpedida);
            this.productoingredientes=(TextView) v.findViewById(R.id.productoingredientes);
            this.productoimagen=(ImageView) v.findViewById(R.id.productoimagen);
            this.michek=(CheckBox) v.findViewById(R.id.myCheckBox);
            this.masmenos=(LinearLayout)v.findViewById(R.id.masmenos);
            this.mas=(Button)v.findViewById(R.id.plus_button);
            this.menos=(Button)v.findViewById(R.id.menos_button);
            this.botonok=(Button)v.findViewById(R.id.botonok);
        }
    }
    @Override
    public Adaptadorproductos.AdaptadorViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tarjetaproducto,viewGroup,false);
        return new Adaptadorproductos.AdaptadorViewHolder(v);
    }
    @Override
    public void onBindViewHolder(final Adaptadorproductos.AdaptadorViewHolder viewHolder, final int position) {
        final Productos item = items.get(position);
        viewHolder.itemView.setTag(item);
        viewHolder.productonombre.setText(item.getNombreproducto());
        viewHolder.productoingredientes.setText(item.getIngredientes());
        viewHolder.productoprecio.setText(String.valueOf(item.getPrecventa()));
        viewHolder.idproducto.setText(String.valueOf(item.getIdproducto()));
        //viewHolder.michek.setVisibility(View.GONE);
        //viewHolder.cantidadpedida.setVisibility(View.GONE);
        viewHolder.masmenos.setVisibility(View.GONE);
        viewHolder.botonok.setVisibility(View.GONE);
/*asignar imagen desde url*/
String foto=item.getDescripcion().toString();

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

                   // Toast.makeText(getApplicationContext(),item.getNombreproducto()+viewHolder.cantidadpedida.getText(),Toast.LENGTH_LONG).show();


                }            }
        });


        viewHolder.menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cantidad=viewHolder.cantidadpedida.getText().toString();
                int c=Integer.parseInt(cantidad);
                if(c>0){
                    c=c-1;

                    viewHolder.cantidadpedida.setText( String.valueOf(c));
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



            }
                if(!buttonView.isChecked())
                {

                    viewHolder.cantidadpedida.setEnabled(false);
                    viewHolder.masmenos.setVisibility(View.GONE);
                    viewHolder.botonok.setVisibility(View.GONE);
                    viewHolder.cantidadpedida.setText( "0");
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
                 objdetallepedido= new Detallepedido(0,idp,cantped,pr,subto,0,t,i);
               realmgrbarenbasedatos(t,cantped,pr,idp);

                realmrecuperarundato();
                detallepedido.add(objdetallepedido);
                viewHolder.cantidadpedida.setEnabled(false);
                viewHolder.masmenos.setVisibility(View.GONE);
                viewHolder.botonok.setVisibility(View.GONE);
                viewHolder.cantidadpedida.setText( "0");
                viewHolder.michek.setChecked(false);
                for (int x = 0; x < detallepedido.size(); x++)
                {

                   // Toast.makeText(getApplicationContext(),detallepedido.get(x).getNombreproducto()+detallepedido.get(x).getCantidad(),Toast.LENGTH_SHORT).show();

                }


            }
        });

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getApplicationContext())
                .name("cars.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

public void realmgrbarenbasedatos(String nombre, int cantidad, Double precio,int idproducto){

    Realm realm = Realm.getDefaultInstance();

//Init the element
    CarDb car = new CarDb();

    car.setidproducto(idproducto);

    car.setnombreproducto(nombre);

    car.setcantidadapedir(cantidad);
    car.setprecio(precio);

    realm.beginTransaction();

    realm.copyToRealm(car);

    realm.commitTransaction();


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
