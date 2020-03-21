package SickOrNotBackend.authentication;

import java.net.UnknownHostException;
import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Authentication {
    private AuthenticationDataBase authenticationDataBase;
    private char[] salt_symbols;

    public Authentication() throws UnknownHostException {
        authenticationDataBase = new AuthenticationDataBase();
        salt_symbols = new char[36];
    }

    public void addPassword(String name, String password) throws NoSuchAlgorithmException {
        String salt = generateSalt();        
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update((password + salt).getBytes());
        String hashedPassword = new String(messageDigest.digest());
        
        authenticationDataBase.insert(name, hashedPassword, salt);
    }

    public boolean checkPassword(String name, String password) throws NoSuchAlgorithmException {
        String storedPassword = authenticationDataBase.getPassword(name);
        String salt = authenticationDataBase.getSalt(name);

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update((password + salt).getBytes());
        String hashedPassword = new String(messageDigest.digest());

        return storedPassword.equals(hashedPassword);
    }

    private void generateSaltSymbols(){
        int index = 0;
        for(char c = '0'; c <= '9'; c++)
            salt_symbols[index++] = c;

        for(char c = 'a'; c <= 'z'; c++)
            salt_symbols[index++] = c;
    }

    private String generateSalt(){
        Random random = new Random(System.nanoTime());
        StringBuilder salt_string = new StringBuilder();
        generateSaltSymbols();

        for(int i = 0; i < 10; i++) 
            salt_string.append(salt_symbols[random.nextInt(36)]);
        
        return salt_string.toString();
    }
}