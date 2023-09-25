package com.fiddovea.fiddovea.controller;

import com.fiddovea.fiddovea.dto.request.LoginRequest;
import com.fiddovea.fiddovea.dto.request.RegisterRequest;
import com.fiddovea.fiddovea.dto.response.LoginResponse;
import com.fiddovea.fiddovea.dto.response.RegisterResponse;
import com.fiddovea.fiddovea.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin")
@CrossOrigin("*")
public class AdminServiceController {
    AdminService adminService;


    @PostMapping
    public ResponseEntity<RegisterResponse> registerCustomer(@RequestBody RegisterRequest registerRequest){
        RegisterResponse registerResponse = adminService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginCustomer(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = adminService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loginResponse);
    }
}
