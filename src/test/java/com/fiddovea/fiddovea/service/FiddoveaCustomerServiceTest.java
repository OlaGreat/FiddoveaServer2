package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.appUtils.JwtUtils;
import com.fiddovea.fiddovea.data.models.Customer;
import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.*;
import com.fiddovea.fiddovea.dto.response.*;
import com.fiddovea.fiddovea.exceptions.BadCredentialsException;
import com.fiddovea.fiddovea.exceptions.ProductAlreadyAdded;
import com.fiddovea.fiddovea.services.CustomerService;
import com.fiddovea.fiddovea.services.ProductService;
import com.fiddovea.fiddovea.services.TokenService;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.servlet.http.HttpServletRequest;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Slf4j

public class FiddoveaCustomerServiceTest {
    @Autowired
    CustomerService customerService;
    @Autowired
    TokenService tokenService;

    @Autowired
    ProductService productService;

    private static String labTestProductId;
    private static String labTestCustomerId;

    @Test
    @Order(1)
    public void testThatCustomerCanRegister(){
        RegisterRequest request = new RegisterRequest();
        request.setEmail("Oladipupoolamilekan2@gmail.com");
        request.setPassword("Oladipupo");
        RegisterResponse response = customerService.register(request);
        assertThat(response).isNotNull();

    }
    @Test
    @Order(2)
    public void findATestCustomer(){
       List<Customer> customerList = customerService.getAllCustomer();
       Customer testCustomer = customerList.get(0);
       labTestCustomerId = testCustomer.getId();
        System.out.println(labTestCustomerId);
    }
    @Test
    @Order(3)
    public void findATestProduct(){
        List<Product> productList = productService.getAllProduct();
        Product testProduct = productList.get(1);
        labTestProductId = testProduct.getProductId();

    }


    @Test
    @Order(4)
    public void testThatCustomerRegisterThrowsException(){
        RegisterRequest request = new RegisterRequest();
        request.setEmail("Oladipupoolamilekan2@gmail.com");
        request.setPassword("Oladipupo");
      assertThrows(BadCredentialsException.class, ()-> customerService.register(request));
    }

    @Test
    @Order(5)
    public void testCustomerLogin(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("Oladipupoolamilekan2@gmail.com");
        loginRequest.setPassword("newPassword");
        LoginResponse response = customerService.login(loginRequest);
        System.out.println(response);
        assertThat(response).isNotNull();

    }

    @Test
    @Order(6)
    public void testCustomerLoginThrowsException(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("Oladipupoolamilekan@gmail.com");
        loginRequest.setPassword("Oladipupo");
        assertThrows(BadCredentialsException.class, ()->customerService.login(loginRequest));
    }



    @Test
    @Order(7)
    public void tesThatUserCanUpdateAccount() throws JsonPatchException {
        UpdateCustomerRequest updateCustomerRequest = buildUpdateRequest();
        UpdateCustomerResponse response = customerService.updateProfile(updateCustomerRequest, labTestCustomerId);
        assertThat(response).isNotNull();
        GetResponse customerResponse = customerService.getCustomerById(labTestCustomerId);
        String fullName = customerResponse.getFullName();
        String expectedFullName = updateCustomerRequest.getFirstName() +
                BLANK_SPACE +
                updateCustomerRequest.getLastName();

        assertThat(fullName).isEqualTo(expectedFullName);

    }

    @Test
    @Order(8)
    public void testViewWishList(){
        HttpServletRequest request = buildHttpServletRequestForToken();
        List<Product> customerList = customerService.viewWishList(request);
        assertEquals(0, customerList.size());
    }


    @Test
    @Order(9)
    public void testAddToCart(){

        HttpServletRequest request = buildHttpServletRequestForToken();
        AddToCartResponse response = customerService.addToCart(labTestProductId,request);
        System.out.println(response);
        assertThat(response).isNotNull();
    }

    @Test
    @Order(10)
    public void testThatAddToCartThrowsExceptionForDuplicateProduct(){
        HttpServletRequest request = buildHttpServletRequestForToken();
        assertThrows(ProductAlreadyAdded.class, ()->customerService.addToCart(labTestProductId,request));
    }

    @Test
    @Order(11)
    void testForgetPassword(){
        ForgetPasswordRequest request = new ForgetPasswordRequest();
        request.setEmail("Oladipupoolamilekan2@gmail.com");
        String response = customerService.forgetPassword(request);
        assertThat(response).isNotNull();
    }

    @Test
    @Order(12)
    void testAddToWishList(){
        HttpServletRequest request = buildHttpServletRequestForToken();
        WishListResponse wishListResponse = customerService.addToWishList(labTestProductId, request);
        System.out.println(wishListResponse);
        assertThat(wishListResponse).isNotNull();
    }

    @Test
    @Order(13)
    void testThatAddToWishListThrowsExceptionForDuplicateProduct(){
        HttpServletRequest request = buildHttpServletRequestForToken();
        assertThrows(ProductAlreadyAdded.class, ()-> customerService.addToWishList(labTestProductId, request));
    }

    @Test
    @Order(14)
    void testRemoveProductFromCart(){
        HttpServletRequest request = buildHttpServletRequestForToken();
        RemoveProductResponse response = customerService.removeFromCart(labTestProductId, request);
        assertThat(response).isNotNull();
    }

    @Test
    @Order(15)
    void testRemoveFormWishList(){
        HttpServletRequest request = buildHttpServletRequestForToken();
        RemoveProductResponse response = customerService.removeFromWishList( labTestProductId, request);
        assertThat(response).isNotNull();

    }


    @Test
    @Order(16)
    void testThatCustomerCanReviewProduct(){
        HttpServletRequest requestToken = buildHttpServletRequestForToken();
        ProductReviewRequest request = new ProductReviewRequest();
        request.setProductRatings(4.7);
        request.setReviewContent("The best i have tasted");
        ProductReviewResponse response = customerService.reviewProduct(request, labTestProductId,requestToken);
        assertThat(response).isNotNull();
    }

    @Test
    @Order(17)
    void testThatCustomerReceivesOtp(){
        RegisterRequest request = new RegisterRequest();
        request.setEmail("greatcolourspaint@gmail.com");
        request.setPassword("Oladipupo");
        RegisterResponse response = customerService.register(request);
        assertThat(response).isNotNull();
    }




    @Test
    @Order(18)
    void testThatCustomerCanMakeOrder(){
        HttpServletRequest requestToken = buildHttpServletRequestForToken();
        OrderRequest request = new OrderRequest();

        request.setOrderTotalAmount("50000");
        request.setHouseNumber("6A");
        request.setLga("Yaba");
        request.setStreet("Sabo");
        request.setState("Lagos");

        ConfirmOrderResponse response = customerService.order(request, requestToken);
        assertThat(response).isNotNull();

    }

    @Test
    @Order(19)
    void testThatCustomerCanViewCart(){
        HttpServletRequest requestToken = buildHttpServletRequestForToken();
       List<Product> cart = customerService.viewCart(requestToken);
       assertThat(cart.size()).isEqualTo(0);
    }

    @Test
    @Order(20)
    void testThatCustomerCanAddAddress(){
        HttpServletRequest requestToken = buildHttpServletRequestForToken();
        AddAddressRequest addAddressRequest = new AddAddressRequest();
        addAddressRequest.setLga("Sabo");
        addAddressRequest.setHouseNumber("7");
        addAddressRequest.setState("Lagos");
        addAddressRequest.setStreet("Yaba");
        customerService.addAddress(addAddressRequest,requestToken);
    }


    private UpdateCustomerRequest buildUpdateRequest() {
    UpdateCustomerRequest updateCustomerRequest = new UpdateCustomerRequest();
    updateCustomerRequest.setFirstName("Oladipupo");
    updateCustomerRequest.setLastName("Olamilekan");
    updateCustomerRequest.setPhoneNumber("08026188203");
    updateCustomerRequest.setPassword("newPassword");

    updateCustomerRequest.setEmail("Oladipupoolamilekan2@gmail.com");

    return updateCustomerRequest;
}
    private static HttpServletRequest buildHttpServletRequestForToken() {
        String userId = labTestCustomerId;
        String token = JwtUtils.generateAccessToken(userId);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        return request;
    }
}
