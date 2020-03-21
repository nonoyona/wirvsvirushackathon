package SickOrNotBackend.authentication;

import java.util.Random;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Crypto {
    private static char[] salt_symbols = new char[36];

    /**
     * HashData
     */
    public static class HashData {

        public final String password;
        public final String salt;

        public HashData(String password, String salt) {
            this.password = password;
            this.salt = salt;
        }

    }

    public static HashData hashPassword(String password) throws NoSuchAlgorithmException {
        String salt = generateSalt();
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update((password + salt).getBytes());
        String hashedPassword = new String(messageDigest.digest());

        return new HashData(hashedPassword, salt);
    }

    public static boolean checkPassword(String password, HashData hashedData) throws NoSuchAlgorithmException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update((password + hashedData.salt).getBytes());
        String hashedPassword = new String(messageDigest.digest());

        return hashedData.password.equals(hashedPassword);
    }

    private static void generateSaltSymbols() {
        int index = 0;
        for (char c = '0'; c <= '9'; c++)
            salt_symbols[index++] = c;

        for (char c = 'a'; c <= 'z'; c++)
            salt_symbols[index++] = c;
    }

    private static String generateSalt() {
        Random random = new Random(System.nanoTime());
        StringBuilder salt_string = new StringBuilder();
        generateSaltSymbols();

        for (int i = 0; i < 10; i++)
            salt_string.append(salt_symbols[random.nextInt(36)]);

        return salt_string.toString();
    }
}