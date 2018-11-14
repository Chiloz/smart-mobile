package com.noedel.firebasenotifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.renderscript.RenderScript;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int n = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonNotify = findViewById(R.id.buttonNotify);

        buttonNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationCall();
                n++;
            }
        });
    }

    public void notificationCall(){

            createNotificationChannel();
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "my_channel")
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setSmallIcon(R.drawable.ic_stat_notification)
                    .setContentTitle("New location discovered!")
                    .setContentText("You have discovered 'Maastrichter markt'")
                    .setPriority(NotificationCompat.PRIORITY_HIGH);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notificationBuilder.build());

    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            CharSequence name = "Notifications from watSTAD?!";
            String description = "Notifications when you discover a new location";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel notificationChannel = new NotificationChannel("my_channel", name, importance);

            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}



// https://developer.android.com/training/notify-user/build-notification#java
// https://developer.android.com/training/notify-user/navigation