package SickOrNotBackend.database;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class Database {
    private MongoClient mongoClient;
    private DBCollection collection;
    private DB database;
    private String databaseName = "test";

    public Database() throws UnknownHostException {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDB(databaseName);
        collection = database.createCollection("testresults", null);
    }

    public String search(String string) {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("str", string);
        DBCursor cursor = collection.find(searchQuery);
        return cursor.next().get("val").toString();
    }

    public void insert(String string, boolean value) {
        BasicDBObject document = new BasicDBObject();
        document.put("str", string);
        document.put("val", value);
        collection.insert(document);
    }
}
