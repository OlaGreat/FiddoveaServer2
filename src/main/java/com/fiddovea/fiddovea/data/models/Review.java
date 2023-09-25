package com.fiddovea.fiddovea.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@Document("review")
public class Review {
    @Id
    private String id;
    private LocalDate reviewDate;
    private String reviewContent;
    private double productRatings;
    private String reviewAuthor;

}
