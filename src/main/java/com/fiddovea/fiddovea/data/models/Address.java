package com.fiddovea.fiddovea.data.models;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString

public class Address {
    private String houseNumber;
    private String street;
    private String lga;
    private String state;

}
