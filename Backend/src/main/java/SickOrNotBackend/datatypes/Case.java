package SickOrNotBackend.datatypes;

import java.util.Date;

/**
 * Case
 */
public class Case {
    public String id;
    public String username;
    public Date date;
    public String location;
    public TestResult health;

    public Case(String id, String username, Date date, String location, TestResult testResult) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.location = location;
        this.health = testResult;
    }
}