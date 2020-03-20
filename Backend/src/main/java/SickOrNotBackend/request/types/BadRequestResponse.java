package SickOrNotBackend.request.types;

public class BadRequestResponse {
    public String message;

    public BadRequestResponse(String message) {
        this.message = message;
    }
}
