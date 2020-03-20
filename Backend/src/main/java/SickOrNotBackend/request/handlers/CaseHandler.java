package SickOrNotBackend.request.handlers;

import java.util.UUID;

import org.eclipse.jetty.http.HttpStatus;

import SickOrNotBackend.dataTypes.Case;
import SickOrNotBackend.dataTypes.HealthType;
import SickOrNotBackend.request.types.BadRequestResponse;
import SickOrNotBackend.request.types.CreateCaseBody;
import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Handler
 */
public class CaseHandler implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        CreateCaseBody caseBody = ctx.bodyValidator(CreateCaseBody.class).getOrNull();
        if(caseBody==null){
            ctx.status(HttpStatus.BAD_REQUEST_400).json(new BadRequestResponse("Arguments were not formatted correctly"));
        }else{
            Case fuckCase = new Case(caseBody.location, caseBody.date, HealthType.MAYBE,UUID.randomUUID().toString());
            
            ctx.status(HttpStatus.OK_200).json(fuckCase.number);
        }
        
    }
}
    
    

    
