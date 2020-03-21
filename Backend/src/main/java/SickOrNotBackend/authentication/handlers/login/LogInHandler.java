package SickOrNotBackend.authentication.handlers.login;

import SickOrNotBackend.App;
import SickOrNotBackend.authentication.JWTHandler;
import SickOrNotBackend.authentication.handlers.login.LogInBody;
import SickOrNotBackend.authentication.handlers.registeruser.RegisterUserBody;
import SickOrNotBackend.datatypes.AuthData;
import SickOrNotBackend.datatypes.AuthRoll;
import SickOrNotBackend.request.types.BadRequestResponse;
import SickOrNotBackend.request.types.BodyFormatErrorResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

import java.security.AccessControlException;

public class LogInHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        LogInBody body = ctx.bodyValidator(LogInBody.class).getOrNull();
        if (body == null) {
            ctx.status(HttpStatus.BAD_REQUEST_400).json(new BodyFormatErrorResponse());
        } else {
            try {
                String token = App.authentication.getJsonWebToken(body.username, body.password);
                ctx.status(HttpStatus.OK_200).json(token);
            }catch (AccessControlException e){
                ctx.status(HttpStatus.UNAUTHORIZED_401).json(new BadRequestResponse("Wrong username or password!"));
            }
        }
    }
}
