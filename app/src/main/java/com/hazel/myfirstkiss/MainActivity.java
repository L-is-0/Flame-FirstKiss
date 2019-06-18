package com.hazel.myfirstkiss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView tvFind;
    TextView tvRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFind = findViewById(R.id.tvFind);
        tvRecord = findViewById(R.id.tvRecord);
    }

    public void onFindClicked(View v){
        startActivity(new Intent(MainActivity.this, FindingStoryActivity.class));
    }

    public void onRecordClicked(View v){
        startActivity(new Intent(MainActivity.this, RecordingStoryActivity.class));
    }
}
