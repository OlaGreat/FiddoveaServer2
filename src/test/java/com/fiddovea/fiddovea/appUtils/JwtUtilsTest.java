package com.fiddovea.fiddovea.appUtils;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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
        String userId = "123hfior94092";
        String token = JwtUtils.generateAccessToken(userId);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        String retrieve = JwtUtils.retrieveAndVerifyToken(request);
        assertThat(retrieve).isNotNull();
    }
    @Test
    public void testThatGeneratedTokenCanBeExtracted(){
        String userId = "123hfior94092";
        String token = JwtUtils.generateAccessToken(userId);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);

        String retrieve = JwtUtils.retrieveAndVerifyToken(request);
        assertThat(retrieve).isNotNull();

        String id = JwtUtils.extractUserIdFromToken(retrieve);
        assertThat(id).isNotNull();
        assertThat(id).isEqualTo(userId);
    }




}