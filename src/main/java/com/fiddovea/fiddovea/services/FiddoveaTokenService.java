package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Token;
import com.fiddovea.fiddovea.data.repository.TokenRepository;
import com.fiddovea.fiddovea.exceptions.TokenExpiredException;
import com.fiddovea.fiddovea.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

import static com.fiddovea.fiddovea.exceptions.ExceptionMessages.THE_PROVIDED_EMAIL_IS_NOT_ATTACHED_TO_THE_TOKEN;
import static com.fiddovea.fiddovea.exceptions.ExceptionMessages.TOKEN_EXPIRED_PLEASE_GENERATE_ANOTHER_TOKEN_FOR_VERIFICATION;


@Service
@AllArgsConstructor
public class FiddoveaTokenService implements TokenService{

    private final TokenRepository tokenRepository;



    @Override
    public String createToken(String userEmail) {
        String token = generateToken();
        Token userToken = new Token();
        userToken.setToken(token);
        userToken.setOwnerEmail(userEmail.toLowerCase());
        Token savedToken = tokenRepository.save(userToken);
        System.out.println(savedToken);

        String tokenToSend = savedToken.getToken();

        return tokenToSend;
    }

    @Override
    public Token findByOwnerEmail(String email) {
        Token token = tokenRepository.findByOwnerEmail(email.toLowerCase())
                .orElseThrow(()-> new UserNotFoundException(THE_PROVIDED_EMAIL_IS_NOT_ATTACHED_TO_THE_TOKEN.getMessage()));
        return token;
    }

    @Override
    public void deleteToken(String id) {
        tokenRepository.deleteById(id);
    }

    private String generateToken() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            int digit = random.nextInt(1,9);
            otp.append(digit);
        }
        return String.valueOf(otp);
    }
}
