package app.safety.hsbc.safeguard;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener {

//    private TextView mTextMessage;
    protected BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.getMenu().findItem(R.id.navigation_info).setChecked(true);

        /***
         * The below code push a notification
         */
//        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        // The id of the channel.
//        String id = "my_channel_01";
//        // The user-visible name of the channel.
//        CharSequence name = getString(R.string.channel_name);
//        // The user-visible description of the channel.
//        String description = getString(R.string.channel_description);
//        int importance = NotificationManager.IMPORTANCE_HIGH;
//        NotificationChannel mChannel = new NotificationChannel(id, name,importance);
//        // Configure the notification channel.
//        mChannel.setDescription(description);
//        mChannel.enableLights(true);
//        // Sets the notification light color for notifications posted to this
//        // channel, if the device supports this feature.
//        mChannel.setLightColor(R.color.colorPrimary);
//        mChannel.enableVibration(true);
//        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//        mNotificationManager.createNotificationChannel(mChannel);
//        mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//        // Sets an ID for the notification, so it can be updated.
//        int notifyID = 1;
//        // The id of the channel.
//        String CHANNEL_ID = "my_channel_01";
//        // Create a notification and set the notification channel.
//        Notification notification = new Notification.Builder(this)
//                .setContentTitle("New Message")
//                .setContentText("You've received new messages.")
//                .setSmallIcon(R.drawable.ic_add_location_black_24dp)
//                .setChannelId(CHANNEL_ID)
//                .build();
//        // Issue the notification.
//        mNotificationManager.notify(notifyID, notification);
//        Log.e("w", "shot!!!!!!!!!!!");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // switch between activities
        navigation.postDelayed(() -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(this, MainActivity.class));

            } else if (itemId == R.id.navigation_info) {
                startActivity(new Intent(this, InfoActivity.class));
            }
            finish();
        }, 300);
        return true;
    }
}
