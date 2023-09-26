package com.fiddovea.fiddovea.service;

import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.dto.request.LoginRequest;
import com.fiddovea.fiddovea.dto.request.ProductRequest;
import com.fiddovea.fiddovea.dto.request.VendorRegistrationRequest;
import com.fiddovea.fiddovea.dto.response.DeleteProductResponse;
import com.fiddovea.fiddovea.dto.response.LoginResponse;
import com.fiddovea.fiddovea.dto.response.ProductResponse;
import com.fiddovea.fiddovea.dto.response.VendorRegistrationResponse;
import com.fiddovea.fiddovea.exceptions.BadCredentialsException;
import com.fiddovea.fiddovea.exceptions.FiddoveaException;
import com.fiddovea.fiddovea.services.VendorService;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class FiddoveaVendorServiceTest {
    @Autowired
    VendorService vendorService;

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
        LoginResponse response = vendorService.login(loginRequest);
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

    private VendorRegistrationRequest buildVendorMapper(){
        VendorRegistrationRequest request = new VendorRegistrationRequest();
        request.setEmail("Oladipupoolamilekan@gmail.com");
        request.setPassword("Oladipupo");
        request.setBusinessType("GROCERY");
        request.setCompanyPhoneNumber("08126188203");
        request.setCompanyRcNumber("123457698476");
        request.setProductType("GRILL");
        request.setHouseNumber("216A");
        request.setStreet("Sabo");
        request.setState("Lagos");
        request.setLga("Yaba");
        return request;

    }
    private VendorRegistrationRequest buildVendorMapper2(){
        VendorRegistrationRequest request = new VendorRegistrationRequest();
        request.setEmail("Oladipupoolamilekan55@gmail.com");
        request.setPassword("Oladipupo");
        request.setBusinessType("GROCERY");
        request.setCompanyPhoneNumber("08126188203");
        request.setCompanyRcNumber("123457698476");
        request.setProductType("GRILL");
        request.setHouseNumber("216A");
        request.setStreet("Sabo");
        request.setState("Lagos");
        request.setLga("Yaba");
        return request;

    }
    @Test
    public void testThatVendorCanAddProduct() throws IOException {

        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("Product name");
        productRequest.setProductDescription("Product description");
        productRequest.setProductPrice(BigDecimal.valueOf(500));
        productRequest.setProductQuantity(10);
        productRequest.setDiscount(0);
        productRequest.setProductImage(getTestImage());


        ProductResponse response = vendorService.addProduct(productRequest, "6511c40f29b0ca3c8d0cce62");
        assertThat(response).isNotNull();

    }

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
        ProductRequest productRequest = new ProductRequest();
        productRequest.setProductName("Product name");
        productRequest.setProductDescription("Product description");
        productRequest.setProductPrice(BigDecimal.valueOf(500));
        productRequest.setProductQuantity(10);
        productRequest.setDiscount(0);

        ProductResponse response = vendorService.addProduct(productRequest, "6511c40f29b0ca3c8d0cce62");
        assertThat(response).isNotNull();

//        productRequest.setProductImage();
    }
    @Test
    @Order(8)
    public void testThatVendorCanDeleteProduct(){
        DeleteProductResponse response = vendorService.deleteProduct("6511c40f29b0ca3c8d0cce62", "6511c47166641160cff718cc");
        assertThat(response).isNotNull();
    }

    @Test
    @Order(9)
    public void testThatVendorCanViewTheirProduct(){
        List<Product> productList = vendorService.viewMyProduct("6511c40f29b0ca3c8d0cce62");
        assertEquals(1, productList.size());
    }

    @Test
    @Order(10)
    public void testThatVendorCanViewOrder(){
        List<Product> orders = vendorService.viewOrder("6511c40f29b0ca3c8d0cce62");
        assertThat(orders.size()).isEqualTo(0);
    }




}
