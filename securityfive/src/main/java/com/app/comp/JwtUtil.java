package com.app.comp;

// Import Date class to set token creation and expiration time
import java.util.Date;

// SecretKey interface represents the cryptographic key used for signing JWT
import javax.crypto.SecretKey;

// Marks this class as a Spring-managed bean
import org.springframework.stereotype.Component;

// JJWT library classes used for creating and parsing JWT tokens
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    /*
     * SECRET KEY
     * ------------------------
     * This key is used to:
     * 1. Sign the JWT while generating it.
     * 2. Verify the JWT while parsing it.
     *
     * Both operations must use the same key.
     *
     * NOTE:
     * For HS256 algorithm, the key should be at least 32 bytes long.
     */
    private final String SECRET = "barrownzlearningacademybarrownzpearl12345";


    /*
     * getKey()
     * --------------------------------
     * Converts the secret string into a SecretKey object.
     *
     * SECRET String
     *       ↓
     * getBytes()
     *       ↓
     * byte[]
     *       ↓
     * hmacShaKeyFor()
     *       ↓
     * SecretKey
     *
     * This key is used for both signing and verification.
     */
    private SecretKey getKey() {

        // Convert secret String into SecretKey
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }


    /*
     * generateToken()
     * --------------------------------
     * Creates a JWT token for the supplied username.
     *
     * Example:
     * Username = "john"
     *
     * Token contains:
     * {
     *    sub : "john"
     *    iat : Token Creation Time
     *    exp : Token Expiration Time
     * }
     *
     * Returns:
     * Encoded JWT String
     */
    public String generateToken(String username) {

        return Jwts.builder()

                /*
                 * SUBJECT (sub)
                 *
                 * Stores the username inside the token.
                 *
                 * Example:
                 * username = "john"
                 *
                 * Payload:
                 * {
                 *      "sub":"john"
                 * }
                 */
                .subject(username)

                /*
                 * issuedAt()
                 *
                 * Sets the current time as token creation time.
                 *
                 * Example:
                 * 22-Jun-2026 11:30 AM
                 */
                .issuedAt(new Date())

                /*
                 * expiration()
                 *
                 * Sets token expiration time.
                 *
                 * System.currentTimeMillis()
                 * returns current time in milliseconds.
                 *
                 * 3600000 milliseconds = 1 hour
                 *
                 * Therefore token expires after one hour.
                 */
                .expiration(
                        new Date(System.currentTimeMillis() + 3600000)
                )

                /*
                 * signWith()
                 *
                 * Digitally signs the token using our secret key.
                 *
                 * If anyone modifies the token,
                 * verification will fail.
                 */
                .signWith(getKey())

                /*
                 * compact()
                 *
                 * Converts the JWT object into String form.
                 *
                 * Example:
                 *
                 * eyJhbGciOiJIUzI1NiJ9.
                 * eyJzdWIiOiJqb2huIiwiaWF0Ijox...
                 * ....
                 */
                .compact();
    }


    /*
     * extractUserName()
     * --------------------------------
     * Reads the token and extracts the username from it.
     *
     * Flow:
     *
     * Token
     *   ↓
     * Parser
     *   ↓
     * Verify Signature
     *   ↓
     * Parse Claims
     *   ↓
     * Get Payload
     *   ↓
     * Get Subject
     *   ↓
     * Return Username
     */
    public String extractUserName(String token) {

        return Jwts.parser()

                /*
                 * Uses the same secret key to verify the signature.
                 *
                 * If token is modified or invalid,
                 * an exception will be thrown.
                 */
                .verifyWith(getKey())

                /*
                 * Builds the parser object.
                 */
                .build()

                /*
                 * Parses the JWT and verifies its signature.
                 */
                .parseSignedClaims(token)

                /*
                 * Returns the payload section of JWT.
                 *
                 * Example payload:
                 *
                 * {
                 *      "sub":"john",
                 *      "iat":1750589000,
                 *      "exp":1750592600
                 * }
                 */
                .getPayload()

                /*
                 * Extracts the subject field ("sub")
                 * which contains the username.
                 *
                 * Example:
                 * "john"
                 */
                .getSubject();
    }
}