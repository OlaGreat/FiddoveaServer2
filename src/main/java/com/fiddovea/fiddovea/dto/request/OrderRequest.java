package com.fiddovea.fiddovea.dto.request;

import com.fiddovea.fiddovea.data.models.Address;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {
   private String orderTotalAmount;
   private String houseNumber;
   private String street;
   private String state;
   private String lga;

}
