package SickOrNotBackend.datatypes;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Case
 */
public class Case {
    public String location;
    public String date;
    public HealthType health;
    public String number;
    
	public Case(String location, String date, HealthType health, String number){
        this.location=location;
        this.date=date;
        this.health=health;
        this.number=number;
    }
    
}