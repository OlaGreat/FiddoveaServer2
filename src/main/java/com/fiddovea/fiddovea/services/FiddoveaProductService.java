package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.data.models.ProductType;
import com.fiddovea.fiddovea.data.repository.ProductRepository;
import com.fiddovea.fiddovea.dto.request.ProductRequest;
import com.fiddovea.fiddovea.exceptions.EmptyFieldsException;
import com.fiddovea.fiddovea.exceptions.ProductNotFoundException;
import com.fiddovea.fiddovea.services.cloud.CloudService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static com.fiddovea.fiddovea.exceptions.ExceptionMessages.PLEASE_FILL_ALL_FIELDS;
import static com.fiddovea.fiddovea.exceptions.ExceptionMessages.PRODUCT_NOT_FOUND;

@Service
@AllArgsConstructor
public class FiddoveaProductService implements ProductService{
    private final ProductRepository productRepository;
    private final CloudService cloudService;

    @Override
    public Product addNewProduct(ProductRequest productRequest, String vendorId) {
        if(!checkFields(productRequest)) throw new EmptyFieldsException(PLEASE_FILL_ALL_FIELDS.getMessage());
        MultipartFile productImage = productRequest.getProductImage();

        String productImageUrl = cloudService.upload(productImage);



        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductPrice(productRequest.getProductPrice());
        product.setDiscount(productRequest.getDiscount());
        product.setVendorId(vendorId);
        product.setProductQuantity(productRequest.getProductQuantity());
        product.setProductImageUrl(productImageUrl);
        product.setProductType(ProductType.valueOf(productRequest.getProductType().toUpperCase()));
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }


    private boolean checkFields(ProductRequest productRequest){
        return Stream.of(
                productRequest.getProductImage(),
                productRequest.getProductName(),
                productRequest.getProductDescription()
        ).allMatch(Objects::nonNull) && productRequest.getProductPrice().compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public List<Product> findProductByName(String productName) {
        return productRepository.searchProductByProductName(productName);
    }



    @Override
    public Product findById(String id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(PRODUCT_NOT_FOUND.getMessage()));
        return product;
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}

