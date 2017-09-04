package com.food.sistemas.sodapopapp;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.food.sistemas.sodapopapp.modelo.Dashboardpedido;
import com.food.sistemas.sodapopapp.modelo.Detallepedido;
import com.food.sistemas.sodapopapp.modelo.Productos;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class Menuprincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
   String idd;

    private RecyclerView recycler,recycler2,recycler3;
    private RecyclerView.Adapter adapter,adapter2,adapter3;
    private RecyclerView.LayoutManager lManager,lManager2,lManager3;
    ArrayList<Productos> people=new ArrayList<>();
    ArrayList<Detallepedido> people2=new ArrayList<>();
    ArrayList<Dashboardpedido> people3=new ArrayList<>();
    private String[] strArrData = {"No Suggestions"};

    String session,nombreususrio,almacenactivo;
    String FileName ="myfile";
    private static final String FRAGMENT_TAG = "CURRENT_FRAGMENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(getApplicationContext())
                .name("cars.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
        SharedPreferences sharedPreferences=getSharedPreferences(FileName, Context.MODE_PRIVATE);
        session=sharedPreferences.getString("sessionid","");
        nombreususrio=sharedPreferences.getString("sessionnombre","");

        if (Profile.getCurrentProfile() != null) {
            Profile profile = Profile.getCurrentProfile();
            String firstName = profile.getFirstName();
            String lastName = profile.getLastName();
            String id=profile.getId();
            String photoUrl = profile.getProfilePictureUri(200, 200).toString();


        } else {
            ProfileTracker profileTracker = new ProfileTracker() {
                @Override
                protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                    stopTracking();
                    Profile.setCurrentProfile(currentProfile);
                 }
            };
            profileTracker.startTracking();
        }
        final Profile profile = Profile.getCurrentProfile();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,"Session: "+nombreususrio, Snackbar.LENGTH_LONG)
                        .setAction("Salir",  new salirdefaceboko()).show();
            }
        });
        ///cerar faceboook
        /// LoginManager.getInstance().logOut();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView = navigationView.getHeaderView(0);
        TextView correo = (TextView) hView.findViewById(R.id.nombreuser);


        correo.setText(nombreususrio);
        ImageView toto = (ImageView) hView.findViewById(R.id.fotos);
        String imgUrl;
        try {
 Log.d("TAG", session);
            imgUrl = "https://graph.facebook.com/"+session+"/picture?type=large";

           Picasso.with(this) .load(imgUrl).transform(new CropCircleTransformation()).resize(120, 120)
                    .into(toto);
   } catch (Exception e) {
             System.out.println("Error esta aqui " + e.getMessage());

        }



    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    //menu delos 3 puntitos
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuprincipal, menu);
        return true;
    }
    //opciones del menu de los 3 puntitos
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.mapaaction:
                startActivity(new Intent(Menuprincipal.this, MapsActivity.class));
                return true;
           // case R.id.action_notification:
             //   inflateInboxLayout();
               // return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean FragmentTransaction=false;
        Fragment fragment=null;

        if (id == R.id.nav_camera) {
            // Handle the camera action
            fragment = new ProfileFragment();
            FragmentTransaction=true;

        } else if (id == R.id.nav_gallery) {
            fragment = new HomeFragment();
            FragmentTransaction=true;
        } else if (id == R.id.nav_slideshow) {
            fragment = new CalendarFragment();
            FragmentTransaction=true;
        } else if (id == R.id.nav_manage) {
            fragment = new Dashboard();
            FragmentTransaction=true;
        } else if (id == R.id.nav_share) {
            fragment = new SettingsFragment();
            FragmentTransaction=true;


        } else if (id == R.id.nav_send) {
        }
        if(FragmentTransaction){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_menuprincipal,fragment)
                    .commit();
            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public class salirdefaceboko implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            FirebaseAuth.getInstance().signOut();
            LoginManager.getInstance().logOut();

            limpiarshare();
            Iirainicio();
        }
    }

    public void Iirainicio(){
        Intent i = new Intent(this, Pruebaconexio.class);
        startActivity(i);

        finish();
    }

    private  void limpiarshare(){
        SharedPreferences sharedPreferences =getSharedPreferences(FileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("sessionid","");
        editor.putString("sessionnombre","");
        editor.putString("sessionapepat","");
        editor.putString("sessionapemat","");
        editor.putString("almacenactivo","");
        editor.putString("facebook","");
        editor.putString("editandopedido","no");

        editor.commit();
        Toast.makeText(this,"Session Cerrada",Toast.LENGTH_LONG).show();
    }



}
