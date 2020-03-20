package SickOrNotBackend.database;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import SickOrNotBackend.datatypes.*;

public class TestDataBase {
    private MongoClient mongoClient;
    private MongoCollection<Case> collection;
    private DB database;
    private String databaseName = "test";

    public TestDataBase() throws UnknownHostException {
        mongoClient = new MongoClient("db02.dev.schaefkn.com", 27017);
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