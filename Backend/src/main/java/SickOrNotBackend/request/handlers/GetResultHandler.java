
package SickOrNotBackend.request.handlers;

import SickOrNotBackend.App;
import SickOrNotBackend.datatypes.HealthType;
import org.eclipse.jetty.http.HttpStatus;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * GetResultHandler
 */
public class GetResultHandler implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        String id = ctx.pathParam("id");
        HealthType health = App.database.getState(id);
        ctx.status(HttpStatus.OK_200).json(health);
    }

    
}