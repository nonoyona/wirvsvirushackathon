package SickOrNotBackend.database;

import java.net.UnknownHostException;

public class Test {
    public static void main(String[] args) {
        try {
            TestDataBase database = new TestDataBase();
            database.insert("0", true);
            System.out.println(database.search("0"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        
    }
}