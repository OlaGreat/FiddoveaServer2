package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Admin;
import com.fiddovea.fiddovea.data.models.Vendor;
import com.fiddovea.fiddovea.dto.request.LoginRequest;
import com.fiddovea.fiddovea.dto.request.RegisterRequest;
import com.fiddovea.fiddovea.dto.response.LoginResponse;
import com.fiddovea.fiddovea.dto.response.RegisterResponse;

public interface AdminService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest loginRequest);
    Admin findByEmail(String email);
    void pendingVerify(Vendor vendor);
}
