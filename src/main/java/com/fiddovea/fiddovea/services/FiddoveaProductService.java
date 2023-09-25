package com.fiddovea.fiddovea.services;

import com.fiddovea.fiddovea.data.models.Product;
import com.fiddovea.fiddovea.data.repository.ProductRepository;
import com.fiddovea.fiddovea.dto.request.ProductRequest;
import com.fiddovea.fiddovea.exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fiddovea.fiddovea.exceptions.ExceptionMessages.PRODUCT_NOT_FOUND;

@Service
@AllArgsConstructor
public class FiddoveaProductService implements ProductService{
    ProductRepository productRepository;

    @Override
    public Product addNewProduct(ProductRequest productRequest, String vendorId) {

        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductDescription(productRequest.getProductDescription());
        product.setProductPrice(productRequest.getProductPrice());
        product.setDiscount(productRequest.getDiscount());
        product.setVendorId(vendorId);
        product.setProductQuantity(productRequest.getProductQuantity());
        product.setProductImage(productRequest.getProductImage());
        Product savedProduct = productRepository.save(product);

        return savedProduct;
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
}

