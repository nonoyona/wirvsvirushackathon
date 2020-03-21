package SickOrNotBackend.utils;

import java.util.UUID;

/**
 * RandomID
 */
public class RandomID {

    public static String getRandom(){
        return UUID.randomUUID().toString().toUpperCase().substring(0, 7);
    }
    
}