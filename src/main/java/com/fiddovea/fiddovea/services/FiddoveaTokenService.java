package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Token;
import com.fiddovea.fiddovea.data.repository.TokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
@AllArgsConstructor
public class FiddoveaTokenService implements TokenService{

    private final TokenRepository tokenRepository;



    @Override
    public String createToken(String userId) {
        String token = generateToken();
        Token userToken = new Token();
        userToken.setToken(token);
        userToken.setOwnerId(userId);
        Token savedToken = tokenRepository.save(userToken);

        String tokenToSend = savedToken.getToken();

        return tokenToSend;
    }

    private String generateToken() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            int digit = random.nextInt(1,9);
            otp.append(digit);
        }
        return String.valueOf(otp);
    }
}
