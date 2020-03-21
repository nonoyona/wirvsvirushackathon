package SickOrNotBackend.database;

import java.util.Date;
import java.util.Map;

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

    MongoDatabase database;
    MongoCollection<Document> collection;

    public NewDatabase(MongoClient client) {
        database = client.getDatabase("test");
        collection = database.getCollection("testCollection");
    }

    @Override
    public boolean insertCase(Case c) {
        Document d = new Document(new ObjectMapper().convertValue(c, Map.class));
        var num = collection.countDocuments(Filters.eq("id", c.id));
        if (num > 0)
            return false;
        collection.insertOne(d);
        return true;
    }

    @Override
    public TestResult getState(String id) {
        var result = collection.find(Filters.eq("id", id));

        var doc = result.first();
        if (doc == null) {
            throw new NullPointerException("No Case with id found");
        }
        var healthStr = doc.getString("health");

        return TestResult.valueOf(healthStr);
    }

    @Override
    public boolean caseExists(String id) {
        var num = collection.countDocuments(Filters.eq("id", id));
        return num > 0;
    }

    @Override
    public void registerTestResult(TestResult testResult, String id) {
        var result = collection.updateOne(Filters.eq("id", id), Updates.set("health", testResult.toString()));
        if (result.getModifiedCount() < 1) {
            throw new NullPointerException("No Case with id found");
        }
    }

    @Override
    public Case getCase(String id) {
        var result = collection.find(Filters.eq("id", id));
        var doc = result.first();
        if (doc == null) {
            throw new NullPointerException("No Case with id found");
        }
        try {
            return new Case(doc.getString("id"), 
            doc.getString("username"), 
            new Date(doc.getLong("date")),
                    doc.getString("location"), 
                    TestResult.valueOf(doc.getString("health")));
        } catch (ClassCastException e) {
            e.printStackTrace();
            // Should not happen because this database contains only Cases
            assert false;
            return null;
        }
    }

}