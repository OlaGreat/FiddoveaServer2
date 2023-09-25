package com.fiddovea.fiddovea.appUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;

import static com.fiddovea.fiddovea.appUtils.AppUtils.APP_NAME;

public class JwtUtils {

   public static String generateVerificationToken(String email){
        String token = JWT.create()
                .withClaim("user", email)
                .withIssuer(APP_NAME)
                .withExpiresAt(Instant.now().plusSeconds(2000))
                .sign(Algorithm.HMAC512("secret"));

        return token;


    }
}
