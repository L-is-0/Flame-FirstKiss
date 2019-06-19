package com.hazel.myfirstkiss;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.hazel.myfirstkiss.Models.Position;

import java.util.ArrayList;

public class FindingStoryActivity  extends AppCompatActivity {
    GetLocation mLocation;
    Position mPostion;
    ArrayList<Position> dbLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findstory);

        mLocation = new GetLocation();
        mPostion = mLocation.retriveCurrentLocation();

        dbLocations = new ArrayList<>();

        calculateTheDistance();
    }

    private void calculateTheDistance(){
        //TODO GET THE LOCATION FROM DB

    }

    private void getDbLocation(){

    }
}
