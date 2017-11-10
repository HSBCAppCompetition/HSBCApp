package app.safety.hsbc.safeguard;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import app.safety.hsbc.safeguard.MainActivity;
import app.safety.hsbc.safeguard.R;

/**
 * Created by steevehuang on 10/11/2017.
 */

public class NotificationAlarmService extends Service {
    public static final int notificationId = 8888;
    NotificationManager notificationManager;
    PendingIntent pendingIntent;
    @Override
    public int onStartCommand(Intent intent, int flag, int startId) {
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(this, MainActivity.class);
        pendingIntent = PendingIntent.getActivity(this, intent.getIntExtra("notifId", 0), mIntent, PendingIntent.FLAG_UPDATE_CURRENT);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                /***
                 * The below code push a notification
                 */
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // The id of the channel.
                String id = "my_channel_01";
                // The user-visible name of the channel.
                CharSequence name = getString(R.string.channel_name);
                // The user-visible description of the channel.
                String description = getString(R.string.channel_description);
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(id, name,importance);
                // Configure the notification channel.
                mChannel.setDescription(description);
                mChannel.enableLights(true);
                // Sets the notification light color for notifications posted to this
                // channel, if the device supports this feature.
                mChannel.setLightColor(R.color.colorPrimary);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mNotificationManager.createNotificationChannel(mChannel);
                mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                // Sets an ID for the notification, so it can be updated.
                int notifyID = 1;
                // The id of the channel.
                String CHANNEL_ID = "my_channel_01";

                // cancel intent
                Intent cancelIntent = new Intent(NotificationAlarmService.this, CancelNotification.class);
                Bundle extras = new Bundle();
                extras.putInt("notification_id", NotificationAlarmService.notificationId);
//                cancelIntent.putExtras(extras);
                PendingIntent pendingCancelIntent = PendingIntent.getBroadcast(getApplicationContext(), NotificationAlarmService.notificationId, cancelIntent, PendingIntent.FLAG_CANCEL_CURRENT) ;

                // go to update activity
                Intent updateIntent = new Intent(getApplicationContext(), UpdateActivity.class);
                PendingIntent updatePendingIntent = PendingIntent.getActivity(NotificationAlarmService.this,
                        0, updateIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT);

                // Create a notification and set the notification channel.
                Notification notification = new Notification.Builder(NotificationAlarmService.this)
                        .setContentTitle(getString(R.string.notification_title))
                        .setContentText(getString(R.string.on_number_change))
                        .setSmallIcon(R.drawable.ic_local_phone_black_24dp)
                        .setChannelId(CHANNEL_ID)
                        .addAction(R.drawable.ic_dashboard_black_24dp,
                                getString(R.string.dismiss), pendingCancelIntent)
                        .addAction(R.drawable.ic_local_phone_black_24dp,
                                getString(R.string.agree), updatePendingIntent)
                        .build();

                // Issue the notification.
                mNotificationManager.notify(notifyID, notification);
                Log.e("w","Notifyinggg");
            }
        }, 1000* 5);
        return super.onStartCommand(intent, flag, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static class CancelNotification extends BroadcastReceiver {

        private int id=8888;

        @Override
        public void onReceive(Context context, Intent intent) {
//            id = intent.getIntExtra("notification_id", 1);
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(id);
        }
    }
}