package SickOrNotBackend.authentication;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import SickOrNotBackend.datatypes.AuthRoll;
import io.javalin.http.Context;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWTHandler
 */
public class JWTHandler {

    /**
     * JWTData
     */
    public static class JWTData {

        public JWTData(AuthRoll roll, String username) {
            this.roll = roll;
            this.username = username;
        }

        public AuthRoll roll;
        public String username;
    }

    /**
     * Creates a signed JWT
     * 
     * Creates a signed JsonWebToken containing the [userId] and the [roll] of a
     * User.
     * 
     * @param userId           unique user ID of a user
     * @param roll             Authentication-Roll of a user
     * @param expirationMillis Milliseconds until the Token expires
     * @return The signed JWT as String
     */
    public static String createJWT(String userId, AuthRoll roll, long expirationMillis) {

        // The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter
                .parseBase64Binary("THIS IS MY SECRET KEY OF LIFE YOU FUCKING BASTARDS");

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(userId).setAudience(roll.toString()).setIssuedAt(now)
                .signWith(signatureAlgorithm, signingKey);

        // if it has been specified, let's add the expiration
        if (expirationMillis > 0) {
            long expMillis = nowMillis + expirationMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * Gets the plain Claims of a JsonWebToken
     * 
     * @param jwt Encoded JsonWebToken
     * @return Claims of the token
     * @throws UnsupportedJwtException if this JsonWebToken does not represent a
     *                                 proper token or if its not verified
     */
    public static Claims decodeJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(
                        DatatypeConverter.parseBase64Binary("THIS IS MY SECRET KEY OF LIFE YOU FUCKING BASTARDS"))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * Gets the JWT data of a user by his webtoken
     * 
     * @param jwt JsonWebToken of the Http Request of the user
     * @return The JWT data of the user
     */
    public static JWTData getJWTData(String jwt) {
        try {
            var claims = decodeJWT(jwt);
            return new JWTData(AuthRoll.valueOf(claims.getAudience()), claims.getId());
        } catch (Exception e) {
            return new JWTData(AuthRoll.UNAUTHORIZED, "username");
        }
    }

    /**
     * Gets the JWT data of a user by his request context
     * 
     * @param ctx Context of the request
     * @return The JWT data of the user
     */
    public static JWTData getJWTDataByContext(Context ctx) {
        var header = ctx.header("Bearer");
        if (header == null) {
            return new JWTData(AuthRoll.UNAUTHORIZED, "username");
        } else {
            return getJWTData(header);
        }
    }

    

}