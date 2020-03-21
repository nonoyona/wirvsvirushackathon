package SickOrNotBackend.authentication;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class AuthenticationDataBase {
    private MongoClient mongoClient;
    private DBCollection collection;
    private DB database;
    private String databaseName = "authentication";

    public AuthenticationDataBase() throws UnknownHostException {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDB(databaseName);
        collection = database.createCollection("passwords", null);
    }

    public void insert(String name, String hashedPassword, String salt){
        BasicDBObject document = new BasicDBObject();
        document.put("name", name);
        document.put("password", hashedPassword);
        document.put("salt", salt);
        collection.insert(document);
    }

    public String getPassword(String name){
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", name);
        DBCursor cursor = collection.find(searchQuery);
        return cursor.next().get("password").toString();
    }

    public String getSalt(String name){
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("name", name);
        DBCursor cursor = collection.find(searchQuery);
        return cursor.next().get("salt").toString();
    }
}