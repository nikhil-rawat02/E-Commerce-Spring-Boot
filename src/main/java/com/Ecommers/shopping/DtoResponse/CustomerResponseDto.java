package com.Ecommers.shopping.DtoResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerResponseDto {

    private int CustomerId;

    private String name;

    private int age;

    private String mobileNo;

    private String email;

    private int noOfItemsInCart;
}
