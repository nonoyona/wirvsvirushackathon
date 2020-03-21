package SickOrNotBackend.request.handlers;

import java.util.UUID;

import org.eclipse.jetty.http.HttpStatus;

import SickOrNotBackend.App;
import SickOrNotBackend.datatypes.Case;
import SickOrNotBackend.datatypes.HealthType;
import SickOrNotBackend.request.types.BadRequestResponse;
import SickOrNotBackend.request.types.CreateCaseBody;
import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * fills the case body and saves it 
 */
public class CaseHandler implements Handler {

    @Override
    public void handle(Context ctx) throws Exception {
        CreateCaseBody caseBody = ctx.bodyValidator(CreateCaseBody.class).getOrNull();
        if(caseBody==null){
            ctx.status(HttpStatus.BAD_REQUEST_400).json(new BadRequestResponse("Some Arguments are missing or wrong!"));
        }else{
            Case ccase = new Case(caseBody.location, caseBody.date, HealthType.MAYBE,UUID.randomUUID().toString());
            if(!App.database.insertCase(ccase)){
                ctx.status(HttpStatus.CONFLICT_409).json(new BadRequestResponse("Data is not saved!"));
            }
            ctx.status(HttpStatus.OK_200).json(ccase.number);
        } 
    }
}
    
    

    
