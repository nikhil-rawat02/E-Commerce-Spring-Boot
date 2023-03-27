package com.Ecommers.shopping.DtoResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentResponseDto {

    String secretKey;

    String razorPayOrderId;

    String applicationFee;

    String secretId;

    String pgName;

}
