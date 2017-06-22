package com.food.sistemas.sodapopapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
public class SettingsFragment extends Fragment {
    String FileName ="myfile";
    private View view;
    private com.food.sistemas.sodapopapp.special.ResideMenu.ResideMenu resideMenu;
    private String[] strArrData = {"No Suggestions"};
    private EditText editText;
    private DatabaseReference chat_data_ref;
    private DatabaseReference user_name_ref;
    private ListView listView;
    private FirebaseAuth mAuth;
    private String dato="",name="";
    DatabaseReference databaseReference;
    String nombre;
    private DatabaseReference userIdRef;
    HashMap<String,String> map;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.chatpantalla, container, false);
        Button bu=(Button) view.findViewById(R.id.envioi);
        Spinner spin;
        Resources res = getResources();
        SharedPreferences prefs = getActivity().getSharedPreferences(FileName, Context.MODE_PRIVATE);
        nombre = prefs.getString("sessionnombre", "");


        databaseReference= FirebaseDatabase.getInstance().getReference().child("chat_users");
        mAuth=FirebaseAuth.getInstance();
        userIdRef=databaseReference.child(mAuth.getCurrentUser().getUid());
        userIdRef.child("name").setValue(nombre);





        mAuth= FirebaseAuth.getInstance();
        editText=(EditText)view.findViewById(R.id.edittext);
        chat_data_ref= FirebaseDatabase.getInstance().getReference().child("chat_data");

        user_name_ref=FirebaseDatabase.getInstance().getReference().child("chat_users").child(mAuth.getCurrentUser().getUid()).child("name");
        listView=(ListView)view.findViewById(R.id.listview);
        map=new HashMap<>();

        user_name_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //name=dataSnapshot.getValue().toString();
               // dato=userIdRef.child("name").setValue(nombre);


            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        FirebaseListAdapter<Message> adapter=new FirebaseListAdapter<Message>(
                getActivity(),Message.class,R.layout.individual_row,chat_data_ref
        ) {
            @Override
            protected void populateView(View v, Message model, int position) {
                TextView msg=(TextView)v.findViewById(R.id.textView11);
                msg.setText(model.getUser_name()+" : "+model.getMessage());
            }
        };
        listView.setAdapter(adapter);
bu.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        chat_data_ref.push().setValue(new Message(editText.getText().toString(),nombre));//storing actual msg with name of the user
        editText.setText("");//clear the msg in edittext
               }
});

        return view;


    }
}
