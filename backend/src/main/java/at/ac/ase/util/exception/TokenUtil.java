package at.ac.ase.util.exception;

import at.ac.ase.entities.User;
import at.ac.ase.service.users.AuctionHouseService;
import at.ac.ase.service.users.UserService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.swing.text.html.Option;
import javax.xml.bind.DatatypeConverter;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    @Autowired
    private UserService userService;

    @Autowired
    private AuctionHouseService auctionHouseService;

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

    public static String getEmailFromToken(String token){
        JsonObject jsonObject = parseTokenToJSON(token);
        return jsonObject.get("sub").getAsString();
    }

    public static JsonObject parseTokenToJSON(String token){
        return JsonParser.parseString(new String(Base64.getDecoder().decode(token.split("\\.")[1].getBytes()))).getAsJsonObject();
    }

    public Authentication getAuthentication(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring("Bearer ".length());
        }
        final List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        String userEmail = getEmailFromToken(token);
        User user = userService.getUserByEmail(userEmail);
        if (Objects.isNull(user)) {
            user = auctionHouseService.getAuctionHouseByEmail(userEmail);
        }
        return new UsernamePasswordAuthenticationToken(user, token, roles);
    }


}
