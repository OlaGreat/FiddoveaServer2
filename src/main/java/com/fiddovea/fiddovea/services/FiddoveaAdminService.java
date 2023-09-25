package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.appUtils.AppUtils;
import com.fiddovea.fiddovea.data.models.Admin;
import com.fiddovea.fiddovea.data.models.Customer;
import com.fiddovea.fiddovea.data.models.Role;
import com.fiddovea.fiddovea.data.models.Vendor;
import com.fiddovea.fiddovea.data.repository.AdminRepository;
import com.fiddovea.fiddovea.dto.request.LoginRequest;
import com.fiddovea.fiddovea.dto.request.RegisterRequest;
import com.fiddovea.fiddovea.dto.response.LoginResponse;
import com.fiddovea.fiddovea.dto.response.RegisterResponse;
import com.fiddovea.fiddovea.exceptions.BadCredentialsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.fiddovea.fiddovea.data.models.Role.ADMIN;
import static com.fiddovea.fiddovea.dto.response.ResponseMessage.REGISTRATION_SUCCESSFUL;
import static com.fiddovea.fiddovea.dto.response.ResponseMessage.WELCOME_BACK;
import static com.fiddovea.fiddovea.exceptions.ExceptionMessages.*;

@Service
@AllArgsConstructor
public class FiddoveaAdminService implements AdminService{

    private final AdminRepository adminRepository;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        String email = request.getEmail().toLowerCase();
        String password = request.getPassword();
        if(checkRegisterEmail(email)) throw new BadCredentialsException(EMAIL_ALREADY_EXIST.getMessage());

        Admin admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(password);
        admin.setRole(ADMIN);

        adminRepository.save(admin);

        RegisterResponse response = new RegisterResponse();
        response.setMessage(REGISTRATION_SUCCESSFUL.name());
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail().toLowerCase();
        String password = loginRequest.getPassword();

        Admin admin = adminRepository.readByEmail(email)
                                     .orElseThrow(()->new BadCredentialsException(INVALID_LOGIN_DETAILS.getMessage()));
        if(admin.getPassword().equals(password)){
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setMessage(WELCOME_BACK.name());
            return loginResponse;
        }else throw new BadCredentialsException(INVALID_LOGIN_DETAILS.getMessage());
    }

    @Override
    public Admin findByEmail(String email) {
        Admin admin = adminRepository.readByEmail(email).orElseThrow(()->new BadCredentialsException(USER_NOT_FOUND.getMessage()));
        return admin;
    }

    @Override
    public void pendingVerify(Vendor vendor) {
            Admin admin = findByEmail(AppUtils.APP_MAIL_SENDER);
            admin.getToVerify().add(vendor);
            adminRepository.save(admin);
    }


    private boolean checkRegisterEmail(String  email){
        if(!AppUtils.verifyEmail(email)) throw new BadCredentialsException(INVALID_EMAIL.getMessage());
        Optional<Admin> foundAdmin = adminRepository.readByEmail(email);
        return foundAdmin.isPresent();
    }

}


