package app.safety.hsbc.safeguard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import app.safety.hsbc.safeguard.utils.HttpRequester;

public class UpdateActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener {

    //    private TextView mTextMessage;
    protected BottomNavigationView navigation;
    EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        phoneNumber = (EditText) findViewById(R.id.input_phone);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.getMenu().findItem(R.id.navigation_info).setChecked(true);
        TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String getSimSerialNumber = telemamanger.getSimSerialNumber();
        String getSimNumber = telemamanger.getLine1Number();
        phoneNumber.setText(getSimNumber);


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


    public void cancelOnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void updateOnClick(View view) {

        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("phone_number",phoneNumber.getText().toString());
        startActivity(intent);
        finish();
    }
}