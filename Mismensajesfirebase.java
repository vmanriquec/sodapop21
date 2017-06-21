package com.food.sistemas.sodapopapp;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by usuario on 20/06/2017.
 */

public class Mismensajesfirebase extends FirebaseMessagingService{
    public static final String TAG="noticias";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
       String from= remoteMessage.getFrom();
        Log.d(TAG,"mensaje recibido de "+from);
        if (remoteMessage.getNotification()!=null){
            Log.d(TAG,"NOTIFICACION"+ remoteMessage.getNotification().getBody());
            mostrarnotificacionb(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());

        }
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
        NotificationManager notifi =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                notifi.notify(0,noti.build());

    }
}
