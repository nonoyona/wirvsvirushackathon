
package SickOrNotBackend.request.types;

/**
 * BodyFormatErrorResponse
 */
public class BodyFormatErrorResponse extends BadRequestResponse{

    public BodyFormatErrorResponse() {
        super("Body was not formatted correctly");
    }

    
}