package at.ac.ase.util.exception;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.Date;

public class TokenUtil {

    static String key = "B&E)H@McQfTjWnZr4u7x!A%D*F-JaNdR";
    static String base64Key = DatatypeConverter.printBase64Binary(key.getBytes());
    static byte[] secretBytes = DatatypeConverter.parseBase64Binary(base64Key);

    public static String generateToken(String email, String password) throws JOSEException {
        JWSSigner signer = new MACSigner(secretBytes);

        // Prepare JWT with claims set
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("CatchaBid")
                .claim("password", password)
                .expirationTime(new Date(new Date().getTime() + 60 * 1000))
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

    public static boolean verifyToken(String token) throws ParseException, JOSEException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(secretBytes);
        return signedJWT.verify(verifier);
    }

}
