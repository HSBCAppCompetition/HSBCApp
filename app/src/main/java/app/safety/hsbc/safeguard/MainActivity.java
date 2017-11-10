package app.safety.hsbc.safeguard;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener{

//    private TextView mTextMessage;
    protected BottomNavigationView navigation;
    final int REQUEST_RPS = 0;
    public static final String BROADCAST = "PACKAGE_NAME.android.action.broadcast";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
//        TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        String getSimSerialNumber = telemamanger.getSimSerialNumber();
//        String getSimNumber = telemamanger.getLine1Number();
//        Log.e("w", "Phone number is !! " +  getSimNumber);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_RPS);
        }
        scheduleNotification(1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_RPS:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    String getSimSerialNumber = telemamanger.getSimSerialNumber();
                    String getSimNumber = telemamanger.getLine1Number();
                    Log.e("w", "Phone number is !! " +  getSimNumber);
                }
                break;

            default:
                break;
        }
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
//                navigation.getMenu().findItem(R.id.navigation_info).setChecked(true);
                Log.e("w", "what up!");

            }
            finish();

        }, 300);

        return true;
    }

    private void scheduleNotification(int week){
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, 0);


//        Context context = this;
        /**
         * This claims to trigger the service every 5 sec, but seems that the app needs to run in front.
         */
//        Log.e("w","sechduled");
//        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 3000, 5 *1000, pendingIntent);
//        startActivity(new Intent(this, NotificationPublisher.class));






        /**
         * These are for sending broadcast. Still don't know how to unregister
         */
//        IntentFilter intentFilter = new IntentFilter(BROADCAST);
//        NotificationPublisher notificationPublisher = new NotificationPublisher();
//        registerReceiver( notificationPublisher , intentFilter);
//        Intent intent = new Intent(BROADCAST);
//        Bundle extras = new Bundle();
//        sendBroadcast(intent);


//        unregisterReceiver( notificationPublisher);
        /**
         * This notify the service directly
         */
        Intent serviceIntent = new Intent(this, NotificationAlarmService.class);
        startService(serviceIntent);
    }
}
