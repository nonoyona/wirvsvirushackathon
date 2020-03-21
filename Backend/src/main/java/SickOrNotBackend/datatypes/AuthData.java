
package SickOrNotBackend.datatypes;

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
        
    }

    public String passwordHash;
    public String passwordSalt;
    public String username;
    public AuthRoll roll;
}