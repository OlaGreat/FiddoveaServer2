package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Token;

public interface TokenService {

    String createToken(String email);

    Token findByOwnerEmail(String email);

    void deleteToken(String id);
}
