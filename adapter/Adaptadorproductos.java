package com.food.sistemas.sodapopapp.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        public AdaptadorViewHolder(View v){
            super(v);
            this.productonombre=(TextView) v.findViewById(R.id.productonombre);
            this.productoprecio=(TextView) v.findViewById(R.id.productoprecio);
            this.productoingredientes=(TextView) v.findViewById(R.id.productoingredientes);
            this.productoimagen=(ImageView) v.findViewById(R.id.productoimagen);
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
/*asignar imagen desde url*/
        String facebookUserId = "10205968625733202";
                 String imgUrl;
                    imgUrl = "https://graph.facebook.com/"+facebookUserId+"/picture?type=large";
                    Picasso.with(getApplicationContext()) .load(imgUrl).transform(new CropCircleTransformation()).resize(70, 70)
                            .into( viewHolder.productoimagen);
        //holder.

    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}
