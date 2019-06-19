package com.hazel.myfirstkiss;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView tvFind;
    TextView tvRecord;
    TextView tvPlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        tvFind = findViewById(R.id.tvFind);
        tvRecord = findViewById(R.id.tvRecord);
        tvPlay = findViewById(R.id.tvPlay);
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        // check if enabled and if not send user to the GSP settings
        // Better solution would be to display a dialog and suggesting to
        // go to the settings
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
    }

    public void onFindClicked(View v){
        startActivity(new Intent(MainActivity.this, FindingStoryActivity.class));
    }

    public void onRecordClicked(View v){
        startActivity(new Intent(MainActivity.this, RecordingStoryActivity.class));
    }

    public void onPlayClicked(View v){
        startActivity(new Intent(MainActivity.this, PlayRecording.class));
    }

    public void onLocationClicked(View v){
        startActivity(new Intent(MainActivity.this, GetLocation.class));
    }

}
