
package SickOrNotBackend.database;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.sun.net.httpserver.Filter;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import SickOrNotBackend.datatypes.Case;
import SickOrNotBackend.datatypes.HealthType;

/**
 * NewDatabase
 */
public class NewDatabase implements IDatabase {

    MongoClient client;
    MongoDatabase database;
    MongoCollection<Document> collection;

    public NewDatabase() {
        // client =
        // MongoClients.create("mongodb://development:SWtxXHaxr7WW6eXb@db01.dev.schaefkn.com/?authSource=admin");
        client = MongoClients.create();
        database = client.getDatabase("test");
        collection = database.getCollection("testCollection");
    }

    @Override
    public boolean insertCase(Case c) {
        Document d;
		d = new Document(new ObjectMapper().convertValue(c, Map.class));
        var num = collection.countDocuments(Filters.eq("number", c.number));
        if (num > 0)
            return false;
        collection.insertOne(d);
        return true;
    }

    @Override
    public HealthType getState(String id) {
        var result = collection.find(Filters.eq("number", id));
        var doc = result.first();

        var healthStr = doc.getString("health");
        
		return HealthType.valueOf(healthStr);
    }

    @Override
    public boolean caseExists(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void updateHealthStatus(HealthType status, String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Case getCase(String id) {
        // TODO Auto-generated method stub
        return null;
    }

}