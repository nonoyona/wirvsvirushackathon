package SickOrNotBackend.authentication;

import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

public class Test {
    public static void main(String[] args) {
        try {
            Crypto authentication = new Crypto();
            authentication.addPassword("noah", "kevinistdoof");
            System.out.println("Ist Kevin doof?: " + authentication.checkPassword("noah", "kevinistdoof"));
            System.out.println("Ist Kevin toll?: " + authentication.checkPassword("noah", "kevinisttoll"));
        } catch (NoSuchAlgorithmException | UnknownHostException e) {
            e.printStackTrace();
        }
        
    }
}