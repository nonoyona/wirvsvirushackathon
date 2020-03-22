
package SickOrNotBackend.request.handlers.caselisting;

import org.eclipse.jetty.http.HttpStatus;

import SickOrNotBackend.App;
import SickOrNotBackend.authentication.JWTHandler;
import SickOrNotBackend.datatypes.AuthRoll;
import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * CaseListingHandler
 */
public class CaseListingHandler implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        JWTHandler.JWTData data = JWTHandler.getJWTDataByContext(ctx);
        if (data.roll == AuthRoll.ADMIN || data.roll == AuthRoll.INSTITUTION) {
            CaseListingBody caseBody = ctx.bodyValidator(CaseListingBody.class).getOrNull();
            if (caseBody == null) {
                ctx.status(HttpStatus.BAD_REQUEST_400).json(new BadRequestResponse("Some Arguments are missing or wrong!"));
            } else {
                var result = App.database.getCases(data.username, caseBody.startIndex, caseBody.caseCount);
                ctx.status(HttpStatus.OK_200).json(new CaseListingResult());
            }
        }else{
            ctx.status(HttpStatus.UNAUTHORIZED_401).json(new BadRequestResponse("You are not authorized!"));
        }
    }

    
}