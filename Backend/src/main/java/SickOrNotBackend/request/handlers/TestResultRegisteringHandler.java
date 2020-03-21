package SickOrNotBackend.request.handlers;

import SickOrNotBackend.App;
import SickOrNotBackend.authentication.JWTHandler;
import SickOrNotBackend.datatypes.AuthRoll;
import SickOrNotBackend.request.types.BadRequestResponse;
import SickOrNotBackend.request.types.BodyFormatErrorResponse;
import SickOrNotBackend.request.types.TestResultRegisteringBody;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

public class TestResultRegisteringHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String id = ctx.pathParam("id");
        JWTHandler.JWTData data = JWTHandler.getJWTDataByContext(ctx);
        if (data.roll == AuthRoll.ADMIN || data.roll == AuthRoll.INSTITUTION) {
            TestResultRegisteringBody body = ctx.bodyValidator(TestResultRegisteringBody.class).getOrNull();
            if (body != null) {
                if (App.database.caseExists(id)) {
                    if (App.database.getCase(id).username.equals(data.username)) {
                        App.database.registerTestResult(body.testResult, id);
                        ctx.status(HttpStatus.OK_200).json(id);
                    } else {
                        ctx.status(HttpStatus.UNAUTHORIZED_401).json(new BadRequestResponse("You are not authorized!"));
                    }
                } else {
                    ctx.status(HttpStatus.NOT_FOUND_404).json(new BadRequestResponse("User not found!"));
                }
            } else {
                ctx.status(HttpStatus.BAD_REQUEST_400).json(new BodyFormatErrorResponse());
            }
        }else{
            ctx.status(HttpStatus.UNAUTHORIZED_401).json(new BadRequestResponse("You are not authorized!"));
        }
    }
}
