
package SickOrNotBackend.authentication;

/**
 * IAuthentication
 */
public interface IAuthentication {

    public String getJsonWebToken(String username, String password);

}