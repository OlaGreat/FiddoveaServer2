package com.fiddovea.fiddovea.appUtils;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
class JwtUtilsTest {

    @Test
    public void testJwtTokenGeneration(){
        String userId = "123hfior94092";
        String token = JwtUtils.generateAccessToken(userId);
        log.info("generated ---->{}", token);
        assertThat(token).isNotNull();
    }
    @Test
    public void testThatTokenCanBeRetrieveAndVerified(){



//        String retrieve = JwtUtils.retrieveAndVerifyToken();
    }


}