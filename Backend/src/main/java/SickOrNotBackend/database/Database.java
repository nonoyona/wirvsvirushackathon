package SickOrNotBackend.database;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import SickOrNotBackend.datatypes.Case;
import SickOrNotBackend.datatypes.HealthType;

public class Database implements IDatabase {
    private MongoClient mongoClient;
    private DBCollection collection;
    private DB database;
    private String databaseName = "test";

    public Database() throws UnknownHostException {
        MongoClientURI uri = new MongoClientURI(
                "mongodb://development:SWtxXHaxr7WW6eXb@db01.dev.schaefkn.com/?authSource=admin");
        mongoClient = new MongoClient(uri);
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

    @Override
    public boolean insertCase(Case c) {
        return false;
    }

    @Override
    public HealthType getState(String id) {
        return null;
    }

    @Override
    public boolean caseExists(String id) {
        return false;
    }
}
