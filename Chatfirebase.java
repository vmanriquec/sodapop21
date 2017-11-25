package com.food.sistemas.sodapopapp;

import android.*;
import android.app.Activity;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
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
import static com.food.sistemas.sodapopapp.LocationActivity.REQUEST_LOCATION;

public class Chatfirebase extends Activity implements LocationListener {
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    EditText txtLat;
    EditText txtLong;
    String lat;
    String provider;

    protected boolean gps_enabled, network_enabled;
    private RecyclerView.LayoutManager lManager;
    private View parentView;
    private DatabaseReference chat_data_ref;
    private DatabaseReference user_name_ref;
    private ListView listView;
    private FirebaseAuth mAuth;
    private String dato = "", name = "", nombre, idf;
    private Location mLastLocation;
    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private FirebaseAuth firebaseAuth;
    private RecyclerView mBlogList;
    private boolean mRequestingLocationUpdates = false;
    private DatabaseReference userIdRef;
    HashMap<String, String> map;
    private LocationRequest mLocationRequest;
    private EditText editText;
    double latitude;
    double longitude;

    String FileName = "myfile";
    List itemso = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_location);
        txtLat = (EditText) findViewById(R.id.latitude);
        final EditText txtlong = (EditText) findViewById(R.id.longitude);
        final RecyclerView recycler = (RecyclerView) findViewById(R.id.recicladorchat);
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        SharedPreferences prefs = this.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        nombre = prefs.getString("sessionnombre", "");
        idf = prefs.getString("sessionid", "");
        editText = (EditText) findViewById(R.id.editto);
        Button btnemviar = (Button) findViewById(R.id.btnemviar);

        mBlogList = (RecyclerView) findViewById(R.id.recicladorchat);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chat_data");
        mAuth = FirebaseAuth.getInstance();


        chat_data_ref = FirebaseDatabase.getInstance().getReference().child("chat_data");

        user_name_ref = FirebaseDatabase.getInstance().getReference().child("chat_users");

        map = new HashMap<>();
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        user_name_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        btnemviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = editText.getText().toString();
                if (editText.getText().toString().length() == 0) {
                    // Toast.makeText(this, "no has ingresado tu mensaje", Toast.LENGTH_LONG).show();

                } else {
                    String imgUrl = "https://graph.facebook.com/"+idf+"/picture?type=large";
                    chat_data_ref.push().setValue(new Message(editText.getText().toString(),nombre,idf,imgUrl,txtLat.getText().toString(),txtlong.getText().toString()));
                    user_name_ref.push().setValue(new Message(editText.getText().toString(),nombre,idf,String.valueOf(latitude),String.valueOf(longitude),imgUrl));
                    editText.setText("");//clear the msg in edittext
                }




            }
        });


        FirebaseRecyclerAdapter<Message, CalendarFragment.BlogViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Message, CalendarFragment.BlogViewHolder>(Message.class,R.layout.tarjetachat,CalendarFragment.BlogViewHolder.class,myRef)  {


                    @Override
                    protected void populateViewHolder(CalendarFragment.BlogViewHolder viewHolder, Message model, int position) {
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

    }

    @Override
    public void onLocationChanged(Location location) {
        if(location!=null)
        {    txtLat = (EditText) findViewById(R.id.latitude);
            txtLat.setText( String.valueOf(location.getLatitude()));
           txtLong = (EditText) findViewById(R.id.longitude);
            txtLong.setText(String.valueOf(location.getLongitude()));



        }
       }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","status");
    }




    @Override
    public void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
//        startLocationUpdates();
    }

    @Override
    public void onResume() {
        super.onResume();

        getServicesAvailable();

        // Resuming the periodic location updates

    }
    protected void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Check Permissions Now
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        } else {

        }
    }

    //Stopping location updates
    @Override
    public void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public boolean getServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {

           // Dialog dialog = api.getErrorDialog(this), isAvailable, 0);
            //dialog.show();
        } else {
            //Toast.makeText(getActivity(), "Cannot Connect To Play Services", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void mostrarnotificacionb(String title, String body) {

        Intent targetIntent = new Intent(this, Menuprincipal.class);
        targetIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingintent = PendingIntent.getActivity(this, 0,
                targetIntent, PendingIntent.FLAG_ONE_SHOT);


        Uri sound= RingtoneManager.getDefaultUri((RingtoneManager.TYPE_NOTIFICATION));
        NotificationCompat.Builder noti= (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_menu_send)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(sound)
                .setContentIntent(pendingintent);


        NotificationManager notifi    = (NotificationManager)getApplication().getSystemService(getApplication().NOTIFICATION_SERVICE);


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
