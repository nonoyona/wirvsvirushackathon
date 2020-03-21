package SickOrNotBackend.datatypes;

import java.util.Date;

/**
 * Case
 */
public class Case {
    public String location;
    public Date date;
    public TestResult health;
    public String number;

    public Case(String location, Date date, TestResult health, String number) {
        this.location = location;
        this.date = date;
        this.health = health;
        this.number = number;
    }
}