
package SickOrNotBackend.request.handlers;

import SickOrNotBackend.App;
import SickOrNotBackend.datatypes.TestResult;
import SickOrNotBackend.request.types.BadRequestResponse;
import SickOrNotBackend.request.types.GetResultResponse;

import org.eclipse.jetty.http.HttpStatus;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * GetResultHandler
 */
public class TestResultReceivingHandler implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        String id = ctx.pathParam("id");
        if (App.database.caseExists(id)) {
            TestResult health = App.database.getState(id);
            ctx.status(HttpStatus.OK_200).json(new GetResultResponse(health));
        }else{
            ctx.status(HttpStatus.BAD_REQUEST_400).json(new BadRequestResponse("There is no case for the given id!"));
        }
    }


}