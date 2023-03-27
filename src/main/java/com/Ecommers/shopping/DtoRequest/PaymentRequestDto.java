package com.Ecommers.shopping.DtoRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequestDto {

    private String customerId;

    private BigInteger amount;


}
