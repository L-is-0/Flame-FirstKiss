package com.hazel.myfirstkiss;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.media.MicrophoneDirection;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.IOException;

public class RecordingStoryActivity extends AppCompatActivity {
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String fileName = null;
    private String TAG = "Test";
    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};
    boolean mStartRecording = true;

    private Button btnRecord;
    private MediaRecorder myrecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Record to the external cache directory for visibility
        fileName = getExternalCacheDir().getAbsolutePath();
        Log.d("FilePath",fileName);
        fileName += "audiorecordtest.3gp";


        setContentView(R.layout.activity_recordstory);

        btnRecord = findViewById(R.id.btnRecord);

        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRecord(mStartRecording);
                if (mStartRecording) {
                    btnRecord.setText("Stop recording");
                } else {
                    btnRecord.setText("Start recording");
                }
                mStartRecording = !mStartRecording;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted ) finish();

    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording(){
        myrecorder = new MediaRecorder();
        myrecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myrecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); //for api >= 26
        myrecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        myrecorder.setOutputFile(fileName);

        try {
            myrecorder.prepare();
        } catch (IOException e) {
            Log.d(TAG, "prepare() failed");
        }

        myrecorder.start();
    }

    private void stopRecording() {
        myrecorder.stop();
        myrecorder.release();
        myrecorder = null;
    }

}
