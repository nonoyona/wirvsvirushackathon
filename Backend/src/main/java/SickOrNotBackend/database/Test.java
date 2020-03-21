package SickOrNotBackend.database;

import java.util.Date;
import java.util.UUID;

import SickOrNotBackend.datatypes.Case;
import SickOrNotBackend.datatypes.TestResult;

public class Test {
    public static void main(String[] args) {
        NewDatabase database = new NewDatabase();
        database.insertCase(new Case("Test Ort", new Date(), TestResult.POSITIVE, "HUMBAHUMBA"));
        System.out.println(database.getState("HUMBAHUMBA"));
        System.out.println(UUID.randomUUID().toString());

    }
}
