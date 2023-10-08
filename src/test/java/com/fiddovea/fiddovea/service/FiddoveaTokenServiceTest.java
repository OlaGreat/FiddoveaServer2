package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.appUtils.AppUtils;
import com.fiddovea.fiddovea.data.models.Token;
import com.fiddovea.fiddovea.services.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FiddoveaTokenServiceTest {
    @Autowired
    TokenService tokenService;

    @Test
    void testThatTokenCanBeGenerated(){
        String token = tokenService.createToken(AppUtils.APP_MAIL_SENDER);
        assertThat(token).isNotNull();
    }
    @Test
    void testFindByOwnerId(){
        Token token = tokenService.findByOwnerEmail("oladipupoolamilekan2@gmail.com");
        assertThat(token).isNotNull();
    }

}
