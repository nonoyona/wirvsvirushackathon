
package SickOrNotBackend.authentication.handlers.login;

/**
 * LogInSuccessResponse
 */
public class LogInSuccessResponse {

    public String token;

    public LogInSuccessResponse(String token){
        this.token = token;
    }

}