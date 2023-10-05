package com.fiddovea.fiddovea.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Setter
@Getter
public class ProductReviewRequest {
    private String reviewContent;
    private double productRatings;

}
