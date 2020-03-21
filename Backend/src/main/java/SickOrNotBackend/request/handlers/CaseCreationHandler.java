package SickOrNotBackend.request.handlers;

import java.util.UUID;

import SickOrNotBackend.authentication.JWTHandler;
import SickOrNotBackend.datatypes.AuthRoll;
import org.eclipse.jetty.http.HttpStatus;

import SickOrNotBackend.App;
import SickOrNotBackend.datatypes.Case;
import SickOrNotBackend.datatypes.TestResult;
import SickOrNotBackend.request.types.BadRequestResponse;
import SickOrNotBackend.request.types.CaseCreationBody;
import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * fills the case body and saves it
 */
public class CaseCreationHandler implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        AuthRoll authRoll = JWTHandler.getAuthRollByContext(ctx);
        if (authRoll == AuthRoll.ADMIN || authRoll == AuthRoll.INSTITUTION) {
            CaseCreationBody caseBody = ctx.bodyValidator(CaseCreationBody.class).getOrNull();
            if (caseBody == null) {
                ctx.status(HttpStatus.BAD_REQUEST_400).json(new BadRequestResponse("Some Arguments are missing or wrong!"));
            } else {
                Case c = new Case(UUID.randomUUID().toString(), data.username, caseBody.date, caseBody.location, TestResult.UNTESTED);
                if (!App.database.insertCase(c)) {
                    ctx.status(HttpStatus.CONFLICT_409).json(new BadRequestResponse("Data is not saved!"));
                }
                ctx.status(HttpStatus.OK_200).json(c.id);
            }
        }else{
            ctx.status(HttpStatus.UNAUTHORIZED_401).json(new BadRequestResponse("You are not authorized!"));
        }
    }
}
    
    

    
