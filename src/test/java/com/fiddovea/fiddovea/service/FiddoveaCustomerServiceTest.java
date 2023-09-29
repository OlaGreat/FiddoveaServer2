package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.data.models.Gender;
import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.*;
import com.fiddovea.fiddovea.dto.response.*;
import com.fiddovea.fiddovea.exceptions.BadCredentialsException;
import com.fiddovea.fiddovea.exceptions.ProductAlreadyAdded;
import com.fiddovea.fiddovea.services.CustomerService;
import com.fiddovea.fiddovea.dto.request.RemoveProductRequest;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.fiddovea.fiddovea.appUtils.AppUtils.BLANK_SPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j
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
    public void tesThatUserCanUpdateAccount() throws JsonPatchException {
        UpdateCustomerRequest updateCustomerRequest = buildUpdateRequest();
        UpdateCustomerResponse response = customerService.updateProfile(updateCustomerRequest, "65125697985f4b19b406d7b2");
        log.info("updated profile --> {}", updateCustomerRequest);
        assertThat(response).isNotNull();
        GetResponse customerResponse = customerService.getCustomerById("65125697985f4b19b406d7b2");
        String fullName = customerResponse.getFullName();
        String expectedFullName = updateCustomerRequest.getFirstName() +
                BLANK_SPACE +
                updateCustomerRequest.getLastName();

        assertThat(fullName).isEqualTo(expectedFullName);

    }




    @Test
    @Order(5)
    public void testViewWishList(){
        List<Product> customerList = customerService.viewWishList("65125697985f4b19b406d7b2");
        assertEquals(0, customerList.size());
    }

    @Test
    @Order(6)
    public void testAddToCart(){
        AddToCartRequest addToCartRequest = new AddToCartRequest();
        addToCartRequest.setCustomerId("65125697985f4b19b406d7b2");
        addToCartRequest.setProductId("650f31d2b2f7514ff894c31a");
        AddToCartResponse response = customerService.addToCart(addToCartRequest);
        assertThat(response).isNotNull();
    }

    @Test
    @Order(7)
    public void testThatAddToCartThrowsExceptionForDuplicateProduct(){
        AddToCartRequest addToCartRequest = new AddToCartRequest();
        addToCartRequest.setCustomerId("65125697985f4b19b406d7b2");
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
        wishListRequest.setCustomerId("65125697985f4b19b406d7b2");
        wishListRequest.setProductId("650f31d2b2f7514ff894c31a");
        WishListResponse wishListResponse = customerService.addToWishList(wishListRequest);
        assertThat(wishListResponse).isNotNull();
    }
    @Test
    @Order(10)
    void testThatAddToWishListThrowsExceptionForDuplicateProduct(){
        WishListRequest wishListRequest = new WishListRequest();
        wishListRequest.setCustomerId("65125697985f4b19b406d7b2");
        wishListRequest.setProductId("6510c16533c9254cef1d9b58");
        assertThrows(ProductAlreadyAdded.class, ()-> customerService.addToWishList(wishListRequest));
    }

    @Test
    @Order(11)
    void testRemoveProductFromCart(){
        RemoveProductRequest request = new RemoveProductRequest();
        request.setUserId("65125697985f4b19b406d7b2");
        request.setProductId("650f31d2b2f7514ff894c31a");
        RemoveProductResponse response = customerService.removeFromCart(request);
        assertThat(response).isNotNull();
    }
    @Test
    @Order(12)
    void testRemoveFormWishList(){
        RemoveProductRequest request = new RemoveProductRequest();
        request.setUserId("65125697985f4b19b406d7b2");
        request.setProductId("650f31d2b2f7514ff894c31a");
        RemoveProductResponse response = customerService.removeFromWishList(request);
        assertThat(response).isNotNull();

    }



    private UpdateCustomerRequest buildUpdateRequest() {
        UpdateCustomerRequest updateCustomerRequest = new UpdateCustomerRequest();
        updateCustomerRequest.setFirstName("Coutinho");
        updateCustomerRequest.setLastName("Dacruz");
        updateCustomerRequest.setPhoneNumber("1234567890");
        updateCustomerRequest.setPassword("newPassword");
        updateCustomerRequest.setGender(Gender.MALE);
        updateCustomerRequest.setEmail("Coutinho@gmail.com");
        updateCustomerRequest.setState("lagos");
        updateCustomerRequest.setLga("oshodi");
        updateCustomerRequest.setHouseNumber("13");


        return updateCustomerRequest;
    }

    @Test
    @Order(13)
    void testThatCustomerCanReviewProduct(){
        ProductReviewRequest request = new ProductReviewRequest();
        request.setProductRatings(4.7);
        request.setReviewContent("The best i have tasted");
        request.setProductId("6513c9891d61ad2834bd365f");
        request.setReviewAuthor(" ");
       ProductReviewResponse response = customerService.reviewProduct(request);
        assertThat(response).isNotNull();
    }

    @Test
    @Order(14)
    void testThatCustomerReceivesOtp(){
        RegisterRequest request = new RegisterRequest();
        request.setEmail("greatcolourspaint@gmail.com");
        request.setPassword("Oladipupo");
        RegisterResponse response = customerService.register(request);
        assertThat(response).isNotNull();
    }

    @Test
    @Order(15)
    void testVerifyOtp(){
       TokenVerificationResponse response = customerService.verifyToken("greatcolourspaint@gmail.com", "11613");
       assertThat(response).isNotNull();
    }

    @Test
    @Order(16)
    void testThatCustomerCanMakeOrder(){

    }

    @Test
    void testThatCustomerCanViewCart(){
       List<Product> cart = customerService.viewCart("65144107c024b32b4b18104f");
       assertThat(cart.size()).isEqualTo(0);
    }


//    @Test
//    public void testFindById(){
//        Optional<Customer> customer = customerService.findById("6508d75e27cea05f89ed13e3");
//        Customer foundCustomer = customer.get();
//        assertThat(foundCustomer).isNotNull();
//
//    }
}
