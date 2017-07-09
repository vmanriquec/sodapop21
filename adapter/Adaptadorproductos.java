package com.food.sistemas.sodapopapp.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.food.sistemas.sodapopapp.R;
import com.food.sistemas.sodapopapp.modelo.Productos;
import com.squareup.picasso.Picasso;
import java.util.List;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import static com.facebook.FacebookSdk.getApplicationContext;
public class Adaptadorproductos extends RecyclerView.Adapter<Adaptadorproductos.AdaptadorViewHolder> {
    private Context mainContext;
    private List<Productos> items;
    public Adaptadorproductos(List<Productos> items, Context contexto){
        this.mainContext=contexto;
        this.items=items;
    }
    static class AdaptadorViewHolder extends RecyclerView.ViewHolder{
        protected TextView productonombre;
        protected TextView productoprecio;
        protected TextView productoingredientes;
        protected ImageView productoimagen;
        protected TextView cantidadpedida;
        protected CheckBox michek;
        protected LinearLayout masmenos;
        public AdaptadorViewHolder(View v){
            super(v);
            this.productonombre=(TextView) v.findViewById(R.id.productonombre);
            this.productoprecio=(TextView) v.findViewById(R.id.productoprecio);
            this.cantidadpedida=(TextView) v.findViewById(R.id.contidadpedida);
            this.productoingredientes=(TextView) v.findViewById(R.id.productoingredientes);
            this.productoimagen=(ImageView) v.findViewById(R.id.productoimagen);
            this.michek=(CheckBox) v.findViewById(R.id.myCheckBox);
            this.masmenos=(LinearLayout)v.findViewById(R.id.masmenos);
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
        //viewHolder.michek.setVisibility(View.GONE);
        //viewHolder.cantidadpedida.setVisibility(View.GONE);
        viewHolder.masmenos.setVisibility(View.GONE);
/*asignar imagen desde url*/
        String facebookUserId = "https://unareceta.com/wp-content/uploads/2016/08/receta-arroz-con-pollo-peruano.jpg";
                 String imgUrl;
                    imgUrl = "https://unareceta.com/wp-content/uploads/2016/08/receta-arroz-con-pollo-peruano.jpg";
                    Picasso.with(getApplicationContext()) .load(imgUrl).transform(new CropCircleTransformation()).resize(100, 100)
                            .into( viewHolder.productoimagen);
        viewHolder.michek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked())
                {
                    viewHolder.cantidadpedida.setVisibility(View.VISIBLE);
                    viewHolder.cantidadpedida.setEnabled(true);
                    viewHolder.masmenos.setVisibility(View.VISIBLE);
                }
                if(!buttonView.isChecked())
                {

                    viewHolder.cantidadpedida.setEnabled(false);
                    viewHolder.masmenos.setVisibility(View.GONE);
                }

            }
        });



    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}
