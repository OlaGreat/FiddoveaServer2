package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.appUtils.JwtUtils;
import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.LoginRequest;
import com.fiddovea.fiddovea.dto.request.ProductRequest;
import com.fiddovea.fiddovea.dto.request.UpdateVendorRequest;
import com.fiddovea.fiddovea.dto.request.VendorRegistrationRequest;
import com.fiddovea.fiddovea.dto.response.*;
import com.fiddovea.fiddovea.exceptions.BadCredentialsException;
import com.fiddovea.fiddovea.exceptions.FiddoveaException;
import com.fiddovea.fiddovea.services.NotificationService;
import com.fiddovea.fiddovea.services.VendorService;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static com.fiddovea.fiddovea.appUtils.AppUtils.BLANK_SPACE;
import static com.fiddovea.fiddovea.dto.response.ResponseMessage.YOUR_PRODUCT_HAS_BEEN_ADDED_SUCCESSFULLY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class FiddoveaVendorServiceTest {
    @Autowired
    VendorService vendorService;

    @Autowired
    NotificationService notificationService;

    @Test
    @Order(1)
    public void testThatVendorCanRegister(){
        VendorRegistrationRequest request = buildVendorMapper();
        VendorRegistrationResponse response = vendorService.register(request);
        assertThat(response).isNotNull();

    }
    @Order(2)
    @Test
     public void testThatVendorRegisterThrowsException(){
       VendorRegistrationRequest request = new VendorRegistrationRequest();
        request.setEmail("Oladipupoolamilekan@gmail.com");
        request.setPassword("Oladipupo");
        assertThrows(BadCredentialsException.class, ()-> vendorService.register(request));
    }

    @Test
    @Order(3)
    public void testVendorLogin(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("Oladipupoolamilekan@gmail.com");
        loginRequest.setPassword("Oladipupo");
        VendorLoginResponse response = vendorService.login(loginRequest);
        System.out.println(response);
        assertThat(response).isNotNull();
    }

    @Test
    @Order(4)
    public void testVendorLoginThrowsException(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("Oladipupoolamilekan2@gmail.com");
        loginRequest.setPassword("Oladipupo");
        assertThrows(BadCredentialsException.class, ()->vendorService.login(loginRequest));
    }
    @Test
    @Order(5)
    public void testThatAnotherVendorCanRegister(){
        VendorRegistrationRequest request = buildVendorMapper2();
        VendorRegistrationResponse response = vendorService.register(request);
        assertThat(response).isNotNull();

    }


    @Test
    public void testVendorUpdateProfile() throws JsonPatchException{
        HttpServletRequest userId = buildHttpServletRequestForToken();
        UpdateVendorRequest updateVendorRequest = buildUpdateRequest();
        UpdateVendorResponse updateResponse = vendorService.updateProfile(updateVendorRequest, userId);
        assertThat(updateResponse).isNotNull();
        GetResponse vendorResponse = vendorService.getVendorById("6515b42785b0a160dc0d699d");
        String fullName = vendorResponse.getFullName();
        String email = vendorResponse.getEmail();
        String password = vendorResponse.getPhoneNumber();

        String expectedFullName = updateVendorRequest.getFirstName()+
                BLANK_SPACE+
                updateVendorRequest.getLastName();

        String expectedMail = updateVendorRequest.getEmail();
        String expectedPassword = updateVendorRequest.getPassword();

        assertThat(fullName).isEqualTo(expectedFullName);
        assertThat(email).isEqualTo(expectedMail);
        assertThat(expectedPassword).isEqualTo(expectedPassword);

    }

    private UpdateVendorRequest buildUpdateRequest(){
        UpdateVendorRequest updateRequest = new UpdateVendorRequest();
        updateRequest.setFirstName("ola");
        updateRequest.setLastName("dudu");
        updateRequest.setEmail("Oladipupoolamilekan419@gmail.com");
        updateRequest.setPassword("password");
        updateRequest.setBusinessType("RESTAURANT");
        updateRequest.setCompanyPhoneNumber("08020204050");
        updateRequest.setCompanyRcNumber("222222288888");
        updateRequest.setProductType("GRILL");
        updateRequest.setHouseNumber("888A");
        updateRequest.setStreet("Ikeja");
        updateRequest.setState("Lagos");
        updateRequest.setLga("Oshodi");

        return updateRequest;
    }

    private static HttpServletRequest buildHttpServletRequestForToken() {
        String userId = "6515b42785b0a160dc0d699d";
        String token = JwtUtils.generateAccessToken(userId);

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        return request;
    }


    private VendorRegistrationRequest buildVendorMapper(){
        VendorRegistrationRequest request = new VendorRegistrationRequest();
        request.setEmail("Oladipupoolamilekan1008@gmail.com");
        request.setPassword("Oladipupo");
        request.setBusinessType("GROCERY");
        request.setCompanyPhoneNumber("08126188203");
        request.setCompanyRcNumber("123457698476");
        request.setHouseNumber("216A");
        request.setStreet("Sabo");
        request.setState("Lagos");
        request.setLga("Yaba");
        return request;

    }
    private VendorRegistrationRequest buildVendorMapper2(){
        VendorRegistrationRequest request = new VendorRegistrationRequest();
        request.setEmail("Oladipupoolamilekan555478@gmail.com");
        request.setPassword("Oladipupo");
        request.setBusinessType("GROCERY");
        request.setCompanyPhoneNumber("08126188203");
        request.setCompanyRcNumber("123457698476");
        request.setHouseNumber("216A");
        request.setStreet("Sabo");
        request.setState("Lagos");
        request.setLga("Yaba");
        return request;

    }
    @Test
    public void testThatVendorCanAddProduct() throws IOException {
        HttpServletRequest servletRequest = buildHttpServletRequestForToken();

        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("Product name");
        productRequest.setProductDescription("Product description");
        productRequest.setProductPrice(BigDecimal.valueOf(500));
        productRequest.setProductQuantity(10);
        productRequest.setDiscount(0);
        productRequest.setProductType("cake");
        productRequest.setProductImage(getTestImage());


        ProductResponse response = vendorService.addProduct(productRequest, servletRequest);
        assertThat(response).isNotNull();



////        // Assert that the captured message matches the expected message
//        assertThat(capturedMessage).isEqualTo(YOUR_PRODUCT_HAS_BEEN_ADDED_SUCCESSFULLY.name());
//
//        // Assert that the captured user ID matches the vendor ID
//        assertThat(capturedUserId).isEqualTo("651258570a29fe6b94f9d353");
    }


//      private String capturedMessage;
//     private String capturedUserId;
//
//
//    private void sendNotificationToVendor(String vendorId, String message) {
//        capturedMessage = message;
//        capturedUserId = vendorId;
//        notificationService.addNotification(vendorId, message);
//    }
    private MultipartFile getTestImage(){
        Path path = Paths.get("C:\\Users\\DELL\\Documents\\ProjectWorks\\Fiddovea\\FiddoveaServer\\Fiddovea\\src\\test\\resources\\image\\STK160_X_Twitter_009.webp");
        try (var inputStream = Files.newInputStream(path)){
            MultipartFile image = new MockMultipartFile("new_image",inputStream);
            return image;
        } catch (Exception exception) {
            throw new FiddoveaException(exception.getMessage());
        }
    }

    @Test
    @Order(7)
    public void testThatVendorCanAddProduct2(){
        HttpServletRequest servletRequest = buildHttpServletRequestForToken();
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("Product name");
        productRequest.setProductDescription("Product description");
        productRequest.setProductPrice(BigDecimal.valueOf(500));
        productRequest.setProductQuantity(10);
        productRequest.setProductType("cake");
        productRequest.setDiscount(0);
        productRequest.setProductImage(getTestImage());

        ProductResponse response = vendorService.addProduct(productRequest, servletRequest);
        assertThat(response).isNotNull();

//        productRequest.setProductImage();
    }
    @Test
    @Order(8)
    public void testThatVendorCanDeleteProduct(){
        HttpServletRequest servletRequest = buildHttpServletRequestForToken();
        DeleteProductResponse response = vendorService.deleteProduct("651dc4c42c086b1715e19916", servletRequest);
        assertThat(response).isNotNull();
    }

    @Test
    @Order(9)
    public void testThatVendorCanViewTheirProduct(){
        HttpServletRequest servletRequest = buildHttpServletRequestForToken();
        List<Product> productList = vendorService.viewMyProduct(servletRequest);
        assertEquals(18, productList.size());
    }

    @Test
    @Order(10)
    public void testThatVendorCanViewOrder(){
        HttpServletRequest servletRequest = buildHttpServletRequestForToken();
        List<Product> orders = vendorService.viewOrder(servletRequest);
        assertThat(orders.size()).isEqualTo(0);
    }
//
//
//
//
}
