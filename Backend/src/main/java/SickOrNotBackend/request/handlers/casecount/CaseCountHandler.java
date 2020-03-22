
package SickOrNotBackend.request.handlers.casecount;

import org.eclipse.jetty.http.HttpStatus;

import SickOrNotBackend.App;
import SickOrNotBackend.authentication.JWTHandler;
import SickOrNotBackend.datatypes.AuthRoll;
import SickOrNotBackend.datatypes.TestResult;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * CaseCountHandler
 */
public class CaseCountHandler implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        JWTHandler.JWTData data = JWTHandler.getJWTDataByContext(ctx);
        if (data.roll == AuthRoll.ADMIN || data.roll == AuthRoll.INSTITUTION) {
            var positive = App.database.getCount(data.username, TestResult.POSITIVE);
            var negative = App.database.getCount(data.username, TestResult.NEGATIVE);
            var all = App.database.getCount(data.username);
            ctx.status(HttpStatus.OK_200)
                    .json(new CaseCountResponse(all, all - positive - negative, positive, negative));
        } else {
            ctx.status(HttpStatus.UNAUTHORIZED_401).json(new BadRequestResponse("You are not authorized!"));
        }
    }

}