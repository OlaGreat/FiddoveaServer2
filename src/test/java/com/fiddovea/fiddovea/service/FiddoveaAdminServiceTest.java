package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.data.models.Address;
import com.fiddovea.fiddovea.data.models.BusinessType;
import com.fiddovea.fiddovea.data.models.ProductType;
import com.fiddovea.fiddovea.data.models.Vendor;
import com.fiddovea.fiddovea.dto.request.LoginRequest;
import com.fiddovea.fiddovea.dto.request.RegisterRequest;
import com.fiddovea.fiddovea.dto.request.VendorRegistrationRequest;
import com.fiddovea.fiddovea.dto.response.LoginResponse;
import com.fiddovea.fiddovea.dto.response.RegisterResponse;
import com.fiddovea.fiddovea.exceptions.BadCredentialsException;
import com.fiddovea.fiddovea.services.AdminService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FiddoveaAdminServiceTest {

    @Autowired
    AdminService adminService;

    @Test
    @Order(1)
    public void testThatAdminCanRegister(){
        RegisterRequest request = new RegisterRequest();
        request.setEmail("Oladipupoolamilekan2@gmail.com");
        request.setPassword("Oladipupo");
        RegisterResponse response = adminService.register(request);
        assertThat(response).isNotNull();

    }

    @Test
    @Order(2)
    public void testThatAdminRegisterThrowsException(){
        RegisterRequest request = new RegisterRequest();
        request.setEmail("Oladipupoolamilekan2@gmail.com");
        request.setPassword("Oladipupo");
        assertThrows(BadCredentialsException.class, ()-> adminService.register(request));
    }

    @Test
    @Order(3)
    public void testAdminLogin(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("Oladipupoolamilekan2@gmail.com");
        loginRequest.setPassword("Oladipupo");
        LoginResponse response = adminService.login(loginRequest);
        assertThat(response).isNotNull();
    }

    @Test
    @Order(4)
    public void testVendorLoginThrowsException(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("Oladipupoolamilekan@gmail.com");
        loginRequest.setPassword("Oladipupo");
        assertThrows(BadCredentialsException.class, ()->adminService.login(loginRequest));
    }


    @Test
    void testThatAdminVerifyNewVendors(){
        adminService.pendingVerify(buildVendorMapper());

    }

    private Vendor buildVendorMapper(){
        Vendor vendor = new Vendor();
        vendor.setEmail("Oladipupoolamilekan@gmail.com");
        vendor.setPassword("Oladipupo");
        vendor.setBusinessType(BusinessType.GROCERY);
        vendor.setCompanyPhoneNumber("08126188203");
        vendor.setCompanyRcNumber("123457698476");
        vendor.setProductType(ProductType.GRILL);
        Address address = new Address();
        address.setHouseNumber("216A");
        address.setStreet("Sabo");
        address.setState("Lagos");
        address.setLga("Yaba");
        vendor.setAddress(address);
        return vendor;

    }

}
