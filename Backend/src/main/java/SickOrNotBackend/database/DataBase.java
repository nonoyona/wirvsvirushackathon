package SickOrNotBackend.database;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;


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

