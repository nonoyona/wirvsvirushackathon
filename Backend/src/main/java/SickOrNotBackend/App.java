/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package SickOrNotBackend;

import com.mongodb.client.MongoClients;

import SickOrNotBackend.authentication.IAuthentication;
import SickOrNotBackend.authentication.NewAuthentication;
import SickOrNotBackend.authentication.handlers.login.LogInHandler;
import SickOrNotBackend.authentication.handlers.registeruser.RegisterUserHandler;
import SickOrNotBackend.database.IDatabase;
import SickOrNotBackend.database.NewDatabase;
import SickOrNotBackend.request.handlers.TestResultReceivingHandler;
import SickOrNotBackend.request.handlers.TestResultRegisteringHandler;
import SickOrNotBackend.request.handlers.casecount.CaseCountHandler;
import SickOrNotBackend.request.handlers.caselisting.CaseListingHandler;
import SickOrNotBackend.request.handlers.CaseCreationHandler;
import SickOrNotBackend.request.handlers.TestHandler;
import io.javalin.Javalin;

public class App {

    public static IDatabase database;
    public static IAuthentication authentication;

    public static void main(String[] args) {
        var client = MongoClients.create(
                "mongodb://development:SWtxXHaxr7WW6eXb@db01.dev.schaefkn.com:27017/?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false");
        // var client = MongoClients.create();
        database = new NewDatabase(client);
        authentication = new NewAuthentication(client);
        Javalin app = Javalin.create().start(8080);
        app.before(ctx -> {
            String origin =  ctx.header("Origin");
            System.out.println(origin + "has connected");
            if (origin == null) {
                origin = "http://localhost:3000/";
            }
            
            ctx.header("Access-Control-Allow-Origin", origin);
        });
        app.get("/", new TestHandler());
        app.get("/result/:id", new TestResultReceivingHandler());
        app.post("/cases", new CaseListingHandler());
        app.post("/count", new CaseCountHandler());
        app.post("/create", new CaseCreationHandler());
        app.post("/result/:id", new TestResultRegisteringHandler());
        app.post("/login", new LogInHandler());
        app.post("/register", new RegisterUserHandler());

    }
}
