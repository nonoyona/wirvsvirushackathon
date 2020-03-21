package SickOrNotBackend.database;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DataBase {
    private MongoClient mongoClient;
    private DBCollection collection;
    private DB database;
    private String databaseName = "test";

    public DataBase() throws UnknownHostException {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDB(databaseName);
        collection = database.createCollection("testresults", null);
    }

