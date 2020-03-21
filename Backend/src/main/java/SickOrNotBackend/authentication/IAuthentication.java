
package SickOrNotBackend.authentication;

import java.security.AccessControlException;

import SickOrNotBackend.datatypes.AuthData;
import SickOrNotBackend.datatypes.AuthRoll;

/**
 * IAuthentication
 */
public interface IAuthentication {

    /**
     * Gets a signed JsonWebToken from credentials
     * 
     * Tries to create a jwt from the given credentials
     * If the credentials dont match the credentials in the database
     * 
     * @param username username
     * @param password plain-text password of the user
     * @return The signed jsonwebtoken with which th user can authenticate in future requests
     * @throws AccessControlException if username and password dont match the database
     */
    public String getJsonWebToken(String username, String password);

    /**
     * Gets the authentication Data of a user by his jsonwebtoken
     * 
     * REQUIRES user with given webtoken exists in database
     * 
     * @param jsonWebToken The JsonWebToken as String
     * @return The Authetication data of a user by his jwt
     * @throws NullPointerException if no user with that token exists
     */
    public AuthData getAuthData(String jsonWebToken);

    /**
     * Sets the Authetication Roll of a user
     * 
     * The caller of this function must be an ADMIN
     * 
     * REQUIRES user with given webtoken exists in database
     * 
     * @param username username
     * @param roll the roll that should be assigned to the user
     * @throws NullPointerException if no user with that token exists
     */
    public void setAuthRoll(String username, AuthRoll roll);

    /**
     * Adds a user to the database
     * 
     * This function should only be called by an ADMIN
     * @param data user-data
     * @throws IllegalArgumentException if username already exists
     */
    public void registerUser(AuthData data);

    /**
     * Determines whether the username already exists.
     * 
     * @param username The username to check
     * @return true if the username exists in the database, false otherwise
     */
    public boolean usernameExists(String username);

    /**
     * Changes the password of a user
     * 
     * This function should only be called by a verified user or
     * by an ADMIN
     * 
     * @param username
     * @param newPassword
     */
    public void changePassword(String username, String newPassword);

}