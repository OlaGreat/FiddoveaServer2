package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.*;
import com.fiddovea.fiddovea.dto.response.*;
import com.fiddovea.fiddovea.exceptions.BadCredentialsException;
import com.fiddovea.fiddovea.exceptions.ProductAlreadyAdded;
import com.fiddovea.fiddovea.services.CustomerService;
import com.fiddovea.fiddovea.dto.request.RemoveProductRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class FiddoveaCustomerServiceTest {
    @Autowired
    CustomerService customerService;

    @Test
    @Order(1)
    public void testThatCustomerCanRegister(){
        RegisterRequest request = new RegisterRequest();
        request.setEmail("Oladipupoolamilekan@gmail.com");
        request.setPassword("Oladipupo");
        RegisterResponse response = customerService.register(request);
        assertThat(response).isNotNull();

    }



    @Test
    @Order(2)
    public void testThatCustomerRegisterThrowsException(){
        RegisterRequest request = new RegisterRequest();
        request.setEmail("Oladipupoolamilekan@gmail.com");
        request.setPassword("Oladipupo");
      assertThrows(BadCredentialsException.class, ()-> customerService.register(request));
    }

    @Test
    @Order(3)
    public void testCustomerLogin(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("Oladipupoolamilekan@gmail.com");
        loginRequest.setPassword("Oladipupo");
        LoginResponse response = customerService.login(loginRequest);
        assertThat(response).isNotNull();
    }

    @Test
    @Order(4)
    public void testCustomerLoginThrowsException(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("Oladipupoolamilekan2@gmail.com");
        loginRequest.setPassword("Oladipupo");
        assertThrows(BadCredentialsException.class, ()->customerService.login(loginRequest));
    }




    @Test
    @Order(5)
    public void testViewWishList(){
        List<Product> customerList = customerService.viewWishList("650f84e1e7e3ce5c035725e4");
        assertEquals(0, customerList.size());
    }

    @Test
    @Order(6)
    public void testAddToCart(){
        AddToCartRequest addToCartRequest = new AddToCartRequest();
        addToCartRequest.setCustomerId("650f84e1e7e3ce5c035725e4");
        addToCartRequest.setProductId("650f31d2b2f7514ff894c31a");
        AddToCartResponse response = customerService.addToCart(addToCartRequest);
        assertThat(response).isNotNull();
    }

    @Test
    @Order(7)
    public void testThatAddToCartThrowsExceptionForDuplicateProduct(){
        AddToCartRequest addToCartRequest = new AddToCartRequest();
        addToCartRequest.setCustomerId("650f84e1e7e3ce5c035725e4");
        addToCartRequest.setProductId("650f31d2b2f7514ff894c31a");
        assertThrows(ProductAlreadyAdded.class, ()->customerService.addToCart(addToCartRequest));
    }

    @Test
    @Order(8)
    void testForgetPassword(){
        ForgetPasswordRequest request = new ForgetPasswordRequest();
        request.setEmail("Oladipupoolamilekan@gmail.com");
        String response = customerService.forgetPassword(request);
        assertThat(response).isNotNull();
    }

    @Test
    @Order(9)
    void testAddToWishList(){
        WishListRequest wishListRequest = new WishListRequest();
        wishListRequest.setCustomerId("650f84e1e7e3ce5c035725e4");
        wishListRequest.setProductId("650f31d2b2f7514ff894c31a");
        WishListResponse wishListResponse = customerService.addToWishList(wishListRequest);
        assertThat(wishListResponse).isNotNull();
    }
    @Test
    @Order(10)
    void testThatAddToWishListThrowsExceptionForDuplicateProduct(){
        WishListRequest wishListRequest = new WishListRequest();
        wishListRequest.setCustomerId("650f84e1e7e3ce5c035725e4");
        wishListRequest.setProductId("650f31d2b2f7514ff894c31a");
        assertThrows(ProductAlreadyAdded.class, ()-> customerService.addToWishList(wishListRequest));
    }

    @Test
    @Order(11)
    void testRemoveProductFromCart(){
        RemoveProductRequest request = new RemoveProductRequest();
        request.setUserId("650f84e1e7e3ce5c035725e4");
        request.setProductId("650f31d2b2f7514ff894c31a");
        RemoveProductResponse response = customerService.removeFromCart(request);
        assertThat(response).isNotNull();
    }
    @Test
    @Order(12)
    void testRemoveFormWishList(){
        RemoveProductRequest request = new RemoveProductRequest();
        request.setUserId("650f84e1e7e3ce5c035725e4");
        request.setProductId("650f31d2b2f7514ff894c31a");
        RemoveProductResponse response = customerService.removeFromWishList(request);
        assertThat(response).isNotNull();

    }

//    @Test
//    void test that

//    @Test
//    public void testFindById(){
//        Optional<Customer> customer = customerService.findById("6508d75e27cea05f89ed13e3");
//        Customer foundCustomer = customer.get();
//        assertThat(foundCustomer).isNotNull();
//
//    }
}
