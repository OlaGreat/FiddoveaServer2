package com.fiddovea.fiddovea.dto.request;

import com.fiddovea.fiddovea.data.models.Review;
import com.fiddovea.fiddovea.data.models.Vendor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
@Setter
@Getter
public class ProductRequest {

    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private double discount;
    private int productQuantity;
    private MultipartFile productImage;
    private String productType;
}
