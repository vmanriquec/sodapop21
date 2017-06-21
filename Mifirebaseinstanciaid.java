package com.food.sistemas.sodapopapp;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by usuario on 20/06/2017.
 */

public class Mifirebaseinstanciaid extends FirebaseInstanceIdService {
public  static  final String TAG="noticias";
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
String token= FirebaseInstanceId.getInstance().getToken();

        Log.d(TAG,"token"+token);
    }
}
