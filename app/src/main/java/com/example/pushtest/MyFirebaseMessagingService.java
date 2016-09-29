package com.example.pushtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static final String TAG = "MyMessaging";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> map = remoteMessage.getData();

        for (Map.Entry<String, String> ent: map.entrySet()) {
            Log.v(TAG, "entry " + ent.getKey() + " : " + ent.getValue());
        }

        // Minimal Notification Sample.
        NotificationCompat.Builder b = new NotificationCompat.Builder(this.getApplicationContext());
        b.setSmallIcon(R.mipmap.ic_launcher);

        //b.setContentTitle("Kii Cloud Push");
        b.setContentTitle(remoteMessage.getNotification().getTitle());

        b.setContentText(remoteMessage.getNotification().getBody());

        Notification n =  b.build();

        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(0, n);
    }

    @Override
    public void onMessageSent(String msgID) {
        super.onMessageSent(msgID);

        Log.d("TAG", "Message sent!");
    }
}
