package com.Ecommers.shopping.DtoRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDto {

    private int productId;

    private int customerId;

    private int requiredQuantity;
}
