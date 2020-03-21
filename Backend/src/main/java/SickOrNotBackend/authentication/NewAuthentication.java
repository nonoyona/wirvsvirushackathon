
package SickOrNotBackend.authentication;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;

import SickOrNotBackend.datatypes.AuthData;
import SickOrNotBackend.datatypes.AuthRoll;

/**
 * NewAuthentication
 */
public class NewAuthentication implements IAuthentication {

    private MongoCollection<Document> collection;
    private MongoDatabase database;
    private String databaseName = "authentication";

    public NewAuthentication(MongoClient client) {
        database = client.getDatabase(databaseName);
        collection = database.getCollection("users");
    }

    @Override
    public String getJsonWebToken(String username, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AuthData getAuthData(String jsonWebToken) {
        var data = JWTHandler.getJWTData(jsonWebToken);
        var result = collection.find(Filters.eq("username", data.username));

        var doc = result.first();
        if (doc == null) {
            throw new NullPointerException("No User with username found");
        }

        return new AuthData(doc.getString("passwordHash"), doc.getString("passwordSalt"), doc.getString("username"),
                AuthRoll.valueOf(doc.getString("roll")));
    }

    @Override
    public void setAuthRoll(String username, AuthRoll roll) {
        this.collection.updateOne(Filters.eq("username", username), Updates.set(fieldName, value))
    }

    @Override
    public void registerUser(AuthData data) {
        Document d = new Document(new ObjectMapper().convertValue(data, Map.class));
        var num = collection.countDocuments(Filters.eq("username", data.username));
        if (num > 0)
            throw new IllegalArgumentException("This username already exists");
        collection.insertOne(d);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        
    }

    @Override
    public boolean usernameExists(String username) {
        var num = collection.countDocuments(Filters.eq("username", username));
        return num > 0;
    }

}