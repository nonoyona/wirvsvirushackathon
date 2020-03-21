
package SickOrNotBackend.authentication;

import SickOrNotBackend.datatypes.AuthData;
import SickOrNotBackend.datatypes.AuthRoll;

/**
 * NewAuthentication
 */
public class NewAuthentication implements IAuthentication {

    private MongoClient mongoClient;
    private DBCollection collection;
    private DB database;
    private String databaseName = "authentication";

    public AuthenticationDataBase() throws UnknownHostException {
        mongoClient = new MongoClient("localhost", 27017);
        database = mongoClient.getDB(databaseName);
        collection = database.createCollection("passwords", null);
    }

    @Override
    public String getJsonWebToken(String username, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AuthData getAuthData(String jsonWebToken) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setAuthRoll(String username, AuthRoll roll) {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerUser(AuthData data) {
        // TODO Auto-generated method stub

    }

    @Override
    public void changePassword(String username, String newPassword) {
        // TODO Auto-generated method stub

    }

    
}