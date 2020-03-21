package SickOrNotBackend.authentication.handlers.registeruser;

import SickOrNotBackend.App;
import SickOrNotBackend.authentication.JWTHandler;
import SickOrNotBackend.datatypes.AuthData;
import SickOrNotBackend.datatypes.AuthRoll;
import SickOrNotBackend.request.types.BadRequestResponse;
import SickOrNotBackend.request.types.BodyFormatErrorResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

public class RegisterUserHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        JWTHandler.JWTData data = JWTHandler.getJWTDataByContext(ctx);
        if (data.roll == AuthRoll.ADMIN) {
            RegisterUserBody body = ctx.bodyValidator(RegisterUserBody.class).getOrNull();
            if (body == null) {
                ctx.status(HttpStatus.BAD_REQUEST_400).json(new BodyFormatErrorResponse());
            } else {
                try {
                    var authData = new AuthData(body.username, body.password, body.authRoll);
                    App.authentication.registerUser(authData);
                    ctx.status(HttpStatus.OK_200).json(authData);
                } catch (IllegalArgumentException e) {
                    ctx.status(HttpStatus.CONFLICT_409).json(new BadRequestResponse("Username already exists!"));
                }
            }
        } else {
            ctx.status(HttpStatus.UNAUTHORIZED_401).json(new BadRequestResponse("You are not authorized!"));
        }
    }
}
