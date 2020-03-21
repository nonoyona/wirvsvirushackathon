package SickOrNotBackend.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;

import SickOrNotBackend.datatypes.Case;
import SickOrNotBackend.datatypes.TestResult;

/**
 * NewDatabase
 */
public class NewDatabase implements IDatabase {

    MongoClient client;
    MongoDatabase database;
    MongoCollection<Document> collection;

    public NewDatabase() {
        client = MongoClients.create("mongodb://development:SWtxXHaxr7WW6eXb@db01.dev.schaefkn.com:27017/?authSource=admin&readPreference=primary&appname=MongoDB%20Compass&ssl=false");
        // client = MongoClients.create();
        database = client.getDatabase("test");
        collection = database.getCollection("testCollection");
    }

    @Override
    public boolean insertCase(Case c) {
        Document d = new Document(new ObjectMapper().convertValue(c, JsonMap.class));
        var num = collection.countDocuments(Filters.eq("number", c.id));
        if (num > 0)
            return false;
        collection.insertOne(d);
        return true;
    }

    @Override
    public TestResult getState(String id) {
        var result = collection.find(Filters.eq("number", id));

        var doc = result.first();
        if (doc == null) {
            throw new NullPointerException("No Case with id found");
        }
        var healthStr = doc.getString("health");

        return TestResult.valueOf(healthStr);
    }

    @Override
    public boolean caseExists(String id) {
        var num = collection.countDocuments(Filters.eq("number", id));
        return num > 0;
    }

    @Override
    public void registerTestResult(TestResult testResult, String id) {
        var result = collection.updateOne(Filters.eq("number", id), Updates.set("health", testResult));
        if (result.getModifiedCount() < 1) {
            throw new NullPointerException("No Case with id found");
        }
    }

    @Override
    public Case getCase(String id) {
        var result = collection.find(Filters.eq("number", id));
        var doc = result.first();
        if (doc == null) {
            throw new NullPointerException("No Case with id found");
        }
        try {
            return new Case(doc.getString("number"), doc.getString("username"), doc.getDate("date"), doc.getString("location"),
                    TestResult.valueOf(doc.getString("health")));
        } catch (ClassCastException e) {
            //Should not happen because this database contains only Cases
            assert false;
            return null;
        }
    }

}