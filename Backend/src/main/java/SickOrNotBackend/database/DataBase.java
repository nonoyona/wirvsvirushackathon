package SickOrNotBackend.database;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
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

    public void insert(String id, boolean testResult){
        BasicDBObject document = new BasicDBObject();
        document.put("id", id);
        document.put("results", testResult);
        collection.insert(document);
    }

    public String search(String id){
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("id", id);
        DBCursor cursor = collection.find(searchQuery);
        return cursor.next().get("results").toString();
    }
}