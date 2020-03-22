
package SickOrNotBackend.authentication;

import java.security.AccessControlException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;

import SickOrNotBackend.authentication.Crypto.HashData;
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
        try {
            var data = this.getAuthDataByUsername(username);
            try {
                if (Crypto.checkPassword(password, new HashData(data.passwordHash, data.passwordSalt))) {
                    var result = JWTHandler.createJWT(data.username, data.roll, 60 * 1000 * 60);
                    return result;
                } else {
                    throw new AccessControlException("Auth failed");
                }

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                throw new AccessControlException("Auth failed");
            }
        } catch (NullPointerException e) {
            throw new AccessControlException("Auth failed");
        }
    }

    @Override
    public AuthData getAuthData(String jsonWebToken) {
        var data = JWTHandler.getJWTData(jsonWebToken);
        return getAuthDataByUsername(data.username);
    }

    private AuthData getAuthDataByUsername(String username) {
        var result = collection.find(Filters.eq("username", username));

        var doc = result.first();
        if (doc == null) {
            throw new NullPointerException("No User with username found");
        }

        return new AuthData(doc.getString("passwordHash"), doc.getString("passwordSalt"), doc.getString("username"),
                AuthRoll.valueOf(doc.getString("roll")));
    }

    @Override
    public void setAuthRoll(String username, AuthRoll roll) {
        var result = this.collection.updateOne(Filters.eq("username", username), Updates.set("roll", roll.toString()));
        if (result.getModifiedCount() < 1) {
            throw new NullPointerException("This username does not exist in the database");
        }
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
        try {
            var crypto = Crypto.hashPassword(newPassword);
            var result = this.collection.updateOne(Filters.eq("username", username), Updates
                    .combine(Updates.set("passwordHash", crypto.password), Updates.set("passwordSalt", crypto.salt)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // TODO Give feedback that it didnt work
        }

    }

    @Override
    public boolean usernameExists(String username) {
        var num = collection.countDocuments(Filters.eq("username", username));
        return num > 0;
    }

}