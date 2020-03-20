package SickOrNotBackend.request.handlers;

import SickOrNotBackend.request.types.BadRequestResponse;
import SickOrNotBackend.request.types.TestBody;
import SickOrNotBackend.request.types.TestResponse;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

public class TestHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        TestBody body = ctx.bodyValidator(TestBody.class).getOrNull();
        if(body == null) {
            ctx.status(HttpStatus.BAD_REQUEST_400).json(new BadRequestResponse("Arguments were not formatted correctly"));
        }else{
            
            ctx.status(HttpStatus.OK_200).json(new TestResponse());
        }
    }
}
