package com.hazel.myfirstkiss;

import android.app.AppComponentFactory;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.hazel.myfirstkiss.Models.Position;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import java.net.UnknownHostException;

public class DbConnection extends AppCompatActivity {
    MongoClient mongoClient;
    DbConnection connection;
    DB database;
    DBCollection collection;
    double longtitue = 0;
    double latitute = 0;
    String identifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mongoClient = new MongoClient(new MongoClientURI("mongodb://database.hackathon.ict-flame.eu:27017"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public void uploadToDb(Position p, String id){
        connection = new DbConnection();
        database = connection.mongoClient.getDB("echodb");
        collection = database.getCollection("positions");

        latitute = p.getLocationLat();
        longtitue = p.getLocationLong();
        identifier = id;

        for (int i=-3; i<=3; i++){
            DBObject location = new BasicDBObject()
//                    .append("logtitude", longtitue)
                    .append("lattitude", latitute+i)
                    .append("identifier", identifier);
            collection.insert(location);

        }
    }

    public void queryFromDb(double locationQuery){
        DBObject found = collection.findOne(locationQuery);
        String key = (String) found.get("identifier");
    }
}

