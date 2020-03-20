
package SickOrNotBackend.request.handlers;

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

        

    }

    
}