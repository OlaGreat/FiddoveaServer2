package com.fiddovea.fiddovea.appUtils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
class JwtUtilsTest {

    @Test
    public void testJwtTokenGeneration(){
        String email = "oladipupoolamilekan2@gmail.com";
        String token = JwtUtils.generateVerificationToken(email);
        log.info("generated ---->{}", token);
        assertThat(token).isNotNull();
    }

}