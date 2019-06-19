package com.hazel.myfirstkiss;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import org.json.JSONException;

import java.io.IOException;

public class RecordingStoryActivity extends AppCompatActivity {
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private static String fileName = null;
    private String TAG = "Test";
    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String [] permissions = {Manifest.permission.RECORD_AUDIO};
    boolean mStartRecording = true;
    boolean mStartPlaying = true;

    private Button btnRecord, btnPlay;
    private MediaRecorder myrecorder;
    private MediaPlayer myplayer;
    private FileStorage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Record to the external cache directory for visibility
        fileName = getExternalCacheDir().getAbsolutePath();
        fileName += "/audiorecordtest.3gp";
        Log.d("FilePath",fileName);

        setContentView(R.layout.activity_recordstory);

        btnRecord = findViewById(R.id.btnRecord);
        btnPlay = findViewById(R.id.btnPlay);

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

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPlay(mStartPlaying);
                if (mStartPlaying) {
                    btnPlay.setText("Stop playing");
                } else {
                    btnPlay.setText("Start playing");
                }
                mStartPlaying = !mStartPlaying;
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

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        myplayer = new MediaPlayer();
        try {
            myplayer.setDataSource(fileName);
            myplayer.prepare();
            myplayer.start();
        } catch (IOException e) {
            Log.e(TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        myplayer.release();
        myplayer = null;
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

    public void onUploadClicked(View v) throws IOException, JSONException {
        if(!fileName.isEmpty())
        {
            mStorage = new FileStorage();
            mStorage.uploadFile(fileName);

            Toast.makeText(getApplicationContext(), "Your story has been uploaded!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(RecordingStoryActivity.this, MainActivity.class));
        }else{
            Log.d("File","File is not ready yet");
        }
    }
}
