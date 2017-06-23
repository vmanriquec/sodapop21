package com.food.sistemas.sodapopapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.food.sistemas.sodapopapp.R;
import com.food.sistemas.sodapopapp.modelo.Usuarios;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by usuario on 22/06/2017.
 */

public class Adaptadorchat extends RecyclerView.Adapter<Adaptadorchat.AdaptadorViewHolder> {


private Context mainContext;
private List<Usuarios> items;
public Adaptadorchat(List<Usuarios> items, Context contexto){
        this.mainContext=contexto;
        this.items=items;

        }
static class AdaptadorViewHolder extends RecyclerView.ViewHolder{

    protected TextView textochat;

    protected ImageView imagennes;

    public AdaptadorViewHolder(View v){
        super(v);

        this.textochat=(TextView) v.findViewById(R.id.textochat);

        this.imagennes=(ImageView) v.findViewById(R.id.imagenchat);



    }
}



    @Override
    public Adaptadorchat.AdaptadorViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tarjetachat,viewGroup,false);
        return new Adaptadorchat.AdaptadorViewHolder(v);
    }


    @Override
    public void onBindViewHolder(final Adaptadorchat.AdaptadorViewHolder viewHolder, final int position) {
        final Usuarios item = items.get(position);
        viewHolder.itemView.setTag(item);
        viewHolder.textochat.setText("" + item.getIdusuario());

        {
        };

        String facebookUserId = "";
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        for(UserInfo profile : user.getProviderData()) {
            // check if the provider id matches "facebook.com"
            if(profile.getProviderId().equals("facebook.com")) {
                facebookUserId = profile.getUid();
                try {
                    String imgUrl;
                    imgUrl = "https://graph.facebook.com/"+facebookUserId+"/picture?type=large";

                    Picasso.with(getApplicationContext()) .load(imgUrl).transform(new CropCircleTransformation()).resize(70, 70)
                            .into( viewHolder.imagennes);;
                } catch (Exception e) {
                    System.out.println("Error esta aqui " + e.getMessage());

                }
            }
        }




    }


    @Override
    public int getItemCount() {
        return items.size();
    }
}
