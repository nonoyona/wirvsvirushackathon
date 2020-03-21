/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package SickOrNotBackend;

import SickOrNotBackend.database.IDatabase;
import SickOrNotBackend.request.handlers.GetResultHandler;
import SickOrNotBackend.request.handlers.TestHandler;
import io.javalin.Javalin;

public class App {

    public static IDatabase database;

    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);
        app.get("/", new TestHandler());
        app.get("/result/:id", new GetResultHandler());
        
    }
}
