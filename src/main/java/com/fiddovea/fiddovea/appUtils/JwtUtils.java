package com.fiddovea.fiddovea.appUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fiddovea.fiddovea.exceptions.TokenExpiredException;
import com.fiddovea.fiddovea.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.time.Instant;
import java.util.Map;

import static com.fiddovea.fiddovea.appUtils.AppUtils.*;
import static com.fiddovea.fiddovea.exceptions.ExceptionMessages.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JwtUtils {

   public static String generateAccessToken(String id){
        String token = JWT.create()
                .withClaim(USER_ID, id)
                .withIssuer(APP_NAME)
                .withExpiresAt(Instant.now().plusSeconds(3600))
                .sign(Algorithm.HMAC512(SECRET_KEY));
        return token;
    }

    public static String retrieveAndVerifyToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER)) {
            throw new UnauthorizedException(INVALID_AUTHORIZATION_HEADER_EXCEPTION.getMessage());
        }

        String authorizationToken = authorizationHeader.substring(SEVEN);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET_KEY))
                .withIssuer(APP_NAME)
                .withClaimPresence(USER_ID)
                .build();

        DecodedJWT verifiedToken = verifier.verify(authorizationToken);

        Instant expirationInstant = verifiedToken.getExpiresAt().toInstant();
        Instant now = Instant.now();
        if (now.isAfter(expirationInstant)) {
            throw new TokenExpiredException(TOKEN_EXPIRED_PLEASE_GENERATE_ANOTHER_TOKEN_FOR_VERIFICATION.getMessage());
        }
            if (verifiedToken != null) {
                return authorizationToken;
            }
            throw new UnauthorizedException(VERIFICATION_FAILED_EXCEPTION.getMessage());
   }

    public static String extractUserIdFromToken(String token){
       DecodedJWT decodedJWT =JWT.decode(token);
        Map<String, Claim> claimMap = decodedJWT.getClaims();
        if (claimMap.containsKey(USER_ID)){
            return claimMap.get(USER_ID).asString();
        }
            throw new UnauthorizedException(VERIFICATION_FAILED_EXCEPTION.getMessage());
   }
}
