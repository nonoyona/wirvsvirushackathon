package SickOrNotBackend.database;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.conversions.Bson;

import SickOrNotBackend.datatypes.*;

public class TestDataBase {
    private MongoClient mongoClient;
    private MongoCollection<Case> collection;
    private MongoDatabase database;
    private String databaseName = "test";

    public TestDataBase() throws UnknownHostException {
        mongoClient = MongoClients.create(
                "mongodb://development:SWtxXHaxr7WW6eXb@db01.dev.schaefkn.com:27017/?authSource=admin&ssl=true");
        database = mongoClient.getDatabase(databaseName);
        collection = database.getCollection("testresults", Case.class);
    }

    public void insert(String id, boolean testResult) {
        collection.insertOne(new Case(id, testResult));
    }

    public String search(String id) {
        return collection.find(eq("id", id)).first().id;
    }

    private Bson eq(String string, String id) {
        return ;
    }
}