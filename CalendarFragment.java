package  com.food.sistemas.sodapopapp;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.food.sistemas.sodapopapp.Menuprincipal;
import com.food.sistemas.sodapopapp.Message;
import com.food.sistemas.sodapopapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.food.sistemas.sodapopapp.adapter.Adaptadorchat;
import com.food.sistemas.sodapopapp.adapter.Adaptadorsqlite;
import com.food.sistemas.sodapopapp.modelo.Usuarios;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.food.sistemas.sodapopapp.R.layout.profile;
import static com.food.sistemas.sodapopapp.R.layout.tarjetacardview;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午3:26
 * Mail: specialcyci@gmail.com
 */
public class CalendarFragment extends Fragment {
    private RecyclerView.LayoutManager lManager;
    private View parentView;
    private DatabaseReference chat_data_ref;
    private DatabaseReference user_name_ref;
    private ListView listView;
    private FirebaseAuth mAuth;
    private String dato="",name="",nombre,idf;

    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth firebaseAuth;
    private RecyclerView mBlogList;
    private DatabaseReference userIdRef;
    HashMap<String,String> map;
    private EditText editText;
    String FileName ="myfile";
    List itemso = new ArrayList();
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.reciclerchat, container, false);
        final RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recicladorchat);
        lManager = new LinearLayoutManager(getContext());


        recycler.setLayoutManager(lManager);
        SharedPreferences prefs = getActivity().getSharedPreferences(FileName, Context.MODE_PRIVATE);
        nombre = prefs.getString("sessionnombre", "");
        idf=prefs.getString("sessionid","");
        editText=(EditText) view.findViewById(R.id.editto);
        Button btnemviar=(Button) view.findViewById(R.id.btnemviar);
        mBlogList = (RecyclerView)view.findViewById(R.id.recicladorchat);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(getContext()));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chat_data");
        mAuth= FirebaseAuth.getInstance();

        chat_data_ref= FirebaseDatabase.getInstance().getReference().child("chat_data");

        user_name_ref=FirebaseDatabase.getInstance().getReference().child("chat_users").child(mAuth.getCurrentUser().getUid()).child("name").child("idfacebook");

        map=new HashMap<>();
        lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);

        user_name_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });






        btnemviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f=editText.getText().toString();
                if (editText.getText().toString().length() == 0 ){
                    Toast.makeText(getContext(),"no has ingresado tu mensaje",Toast.LENGTH_LONG).show();

                }
                else
                {  chat_data_ref.push().setValue(new Message(editText.getText().toString(),nombre,idf));//storing actual msg with name of the user
                    editText.setText("");//clear the msg in edittext
                }




            }
        });

        FirebaseRecyclerAdapter<Message, BlogViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Message, BlogViewHolder>(Message.class,R.layout.tarjetachat,BlogViewHolder.class,myRef)  {


                    @Override
                    protected void populateViewHolder(BlogViewHolder viewHolder, Message model, int position) {
                        Log.d("valor",model.getFacebook().toString());
                        if(model.getFacebook().toString().equals("10205968625733202")){

                            viewHolder.mView.findViewById(R.id.ln_message_bg).setBackgroundResource(R.color.chatmio);

                            viewHolder.setTitle(model.getMessage());
                            viewHolder.setImage(getApplicationContext(), model.getFacebook());
  /*                          RelativeLayout.LayoutParams rl=(RelativeLayout.LayoutParams) viewHolder.mView.findViewById(R.id.rela).getLayoutParams();
                            rl.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);*/
                            RelativeLayout d =(RelativeLayout)viewHolder.mView.findViewById(R.id.rela);
                            d.setGravity(Gravity.RIGHT);


                        }else{
                            viewHolder.mView.findViewById(R.id.ln_message_bg).setBackgroundResource(R.color.chatotro);


                            viewHolder.setTitle(model.getMessage());
                            viewHolder.setImage(getApplicationContext(), model.getFacebook());
                           /* RelativeLayout.LayoutParams rl=(RelativeLayout.LayoutParams) viewHolder.mView.findViewById(R.id.rela).getLayoutParams();
                            rl.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                            viewHolder.mView.findViewById(R.id.rela).setLayoutParams(rl);
*/
                        }



                    }


                };
        mBlogList.setAdapter(firebaseRecyclerAdapter);

        return view;


    }
    @Override
    public void onStart() {
        super.onStart();


    }
    private void mostrarnotificacionb(String title, String body) {

        Intent targetIntent = new Intent(getContext(), Menuprincipal.class);
        targetIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingintent = PendingIntent.getActivity(getContext(), 0,
                targetIntent, PendingIntent.FLAG_ONE_SHOT);


        Uri sound= RingtoneManager.getDefaultUri((RingtoneManager.TYPE_NOTIFICATION));
        NotificationCompat.Builder noti= (NotificationCompat.Builder) new NotificationCompat.Builder(getContext())
                .setSmallIcon(R.drawable.ic_menu_send)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(sound)
                .setContentIntent(pendingintent);


        NotificationManager notifi    = (NotificationManager)getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);


        notifi.notify(0,noti.build());

    }


    public static class BlogViewHolder extends RecyclerView.ViewHolder  {
        View mView;
        public BlogViewHolder(View itemView) {



            super(itemView);
            CardView cardView=(CardView)itemView.findViewById(R.id.cv_message);
            TextView tvmenssage=(TextView) itemView.findViewById(R.id.textochat);
            ImageView imagemessage=(ImageView) itemView.findViewById(R.id.imagenchat);
            LinearLayout mensagebg=(LinearLayout)itemView.findViewById(R.id.ln_message_bg);

            mView= itemView;

        }
        public void setTitle(String title){
            TextView post_title = (TextView)mView.findViewById(R.id.textochat);
            post_title.setText(title);
        }
        public void setImage(Context ctx , String image){
            ImageView post_image = (ImageView)mView.findViewById(R.id.imagenchat);
            // We Need TO pass Context
            String imgUrl = "https://graph.facebook.com/"+image+"/picture?type=large";

            Picasso.with(ctx) .load(imgUrl).transform(new CropCircleTransformation()).resize(50, 50)
                    .into(post_image);;
            //Picasso.with(ctx).load(image).into(post_image);
        }    }
    public void bg(){


    }


}
