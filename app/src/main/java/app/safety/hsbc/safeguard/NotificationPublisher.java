package app.safety.hsbc.safeguard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import app.safety.hsbc.safeguard.NotificationAlarmService;

/**
 * Created by steevehuang on 10/11/2017.
 */

public class NotificationPublisher extends BroadcastReceiver {

    final String TAG = "NotificationPublisher";

    public void onReceive(Context context, Intent intent) {

        Intent service = new Intent(context, NotificationAlarmService.class);
//        service.putExtra("notifId", intent.getIntExtra("notifId", 0));
        Log.e("w","publisher");
        context.startService(service);
    }
}