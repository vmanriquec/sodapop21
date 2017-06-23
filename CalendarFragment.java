package com.food.sistemas.sodapopapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.firebase.ui.database.FirebaseListAdapter;
import com.food.sistemas.sodapopapp.adapter.Adaptadorchat;
import com.food.sistemas.sodapopapp.adapter.Adaptadorsqlite;
import com.food.sistemas.sodapopapp.modelo.Usuarios;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private String dato="",name="",nombre;

    DatabaseReference databaseReference;

    private DatabaseReference userIdRef;
    HashMap<String,String> map;
    private EditText editText;
    String FileName ="myfile";
    List itemso = new ArrayList();
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reciclerchat, container, false);
        final RecyclerView recycler = (RecyclerView) view.findViewById(R.id.recicladorchat);
        lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);
        SharedPreferences prefs = getActivity().getSharedPreferences(FileName, Context.MODE_PRIVATE);
        nombre = prefs.getString("sessionnombre", "");
         editText=(EditText) view.findViewById(R.id.editto);
        Button bu=(Button) view.findViewById(R.id.btnemviar);





        mAuth= FirebaseAuth.getInstance();

        chat_data_ref= FirebaseDatabase.getInstance().getReference().child("chat_data");

        user_name_ref=FirebaseDatabase.getInstance().getReference().child("chat_users").child(mAuth.getCurrentUser().getUid()).child("name");

        map=new HashMap<>();

        user_name_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                name=dataSnapshot.getValue().toString();
                // dato=userIdRef.child("name").setValue(nombre);


            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        FirebaseListAdapter<Message> adapter=new FirebaseListAdapter<Message>(
                getActivity(),Message.class,R.layout.reciclerchat,chat_data_ref
        ) {
            @Override
            protected void populateView(View v, Message model, int position) {

                itemso.add(new Usuarios( model.getMessage()+"ww" ,"","","",""));
                Adaptadorchat adapterrecicler = new Adaptadorchat(itemso, getContext());

                recycler.setAdapter(adapterrecicler);


            }
        };

        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f=editText.getText().toString();
                if (editText.getText().toString().length() == 0 ){
                    Toast.makeText(getContext(),"no has ingresado tu mensaje",Toast.LENGTH_LONG).show();

                }
                else
                {  chat_data_ref.push().setValue(new Message(editText.getText().toString(),nombre));//storing actual msg with name of the user
                    editText.setText("");//clear the msg in edittext
                }




            }
        });


        return view;

    }
}
