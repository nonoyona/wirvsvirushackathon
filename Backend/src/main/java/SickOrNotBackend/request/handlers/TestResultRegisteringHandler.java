package SickOrNotBackend.request.handlers;

import SickOrNotBackend.App;
import SickOrNotBackend.request.types.BadRequestResponse;
import SickOrNotBackend.request.types.TestResultRegisteringBody;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.eclipse.jetty.http.HttpStatus;
import org.jetbrains.annotations.NotNull;

public class TestResultRegisteringHandler implements Handler {
    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        String id = ctx.pathParam("id");
        TestResultRegisteringBody body = ctx.bodyValidator(TestResultRegisteringBody.class).getOrNull();
        if(body != null && App.database.caseExists(id)) {
            App.database.registerTestResult(body.testResult, id);
            ctx.status(HttpStatus.OK_200).json(id);
        }else{
            ctx.status(HttpStatus.BAD_REQUEST_400).json(new BadRequestResponse("Arguments were not formatted correctly"));
        }
    }
}
