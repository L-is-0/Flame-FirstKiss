package com.hazel.myfirstkiss;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.BasicDBObject;
import java.net.UnknownHostException;

public class DbConnection {

    MongoClient mongoClient;

    public DbConnection() throws UnknownHostException {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://database.hackathon.ict-flame.eu:27017"));
    }

    public static void main(String[] args) throws UnknownHostException{

        DbConnection connection = new DbConnection();
        DB database = connection.mongoClient.getDB("myDB");
        DBCollection collection = database.getCollection("User");
        DBObject person1 = new BasicDBObject()
                .append("userId", "1")
                .append("userName", "wingwing")
                .append("recordId", "1")
                //.append("recordContent", "101010101")
                .append("locationId", "1")
                .append("locationLat", "101010101")
                .append("locationLong", "101010101");
        collection.insert(person1);
    }

}

