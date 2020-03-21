
package SickOrNotBackend.datatypes;

import java.security.NoSuchAlgorithmException;

import SickOrNotBackend.authentication.Crypto;

/**
 * AuthData
 */
public class AuthData {

    public AuthData(String passwordHash, String passwordSalt, String username, AuthRoll roll) {
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.username = username;
        this.roll = roll;
    }

    public AuthData(String username, String plainPassword, AuthRoll roll){
        try {
            var crypto = Crypto.hashPassword(plainPassword);
            this.passwordHash = crypto.password;
            this.passwordSalt = crypto.salt;
            this.username = username;
            this.roll = roll;
        } catch (NoSuchAlgorithmException e) {
            //////////////////////////////////////////////////////////////////////////////////////////////////////// TODO LEEL SAAS SOOS DAT IS KACKE
            e.printStackTrace();
        }
    
        
    }

    public String passwordHash;
    public String passwordSalt;
    public String username;
    public AuthRoll roll;
}