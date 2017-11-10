package app.safety.hsbc.safeguard;

/**
 * Created by steevehuang on 10/11/2017.
 * This class is for detecting state change in sim card.
 */

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SimChangedReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(final Context context, final Intent intent) {

//        Log.d("SimChangedReceiver", "--> SIM state changed <--");

        // Most likely, checking if the SIM changed could be limited to
        // events where the intent's extras contains a key "ss" with value "LOADED".
        // But it is more secure to just always check if there was a change.
        Intent service = new Intent(context, NotificationAlarmService.class);
//        service.putExtra("notifId", intent.getIntExtra("notifId", 0));
        Log.e("w","publisher");
        context.startService(service);
    }
}
