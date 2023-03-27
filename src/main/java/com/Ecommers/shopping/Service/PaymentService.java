package com.Ecommers.shopping.Service;

import com.Ecommers.shopping.DtoRequest.PaymentRequestDto;
import com.Ecommers.shopping.DtoResponse.PaymentResponseDto;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

public interface PaymentService {

    static final String SECRET_ID = "rzp_test_nQT78EuwecLGqw";
    static final String SECRET_KEY = "4Xas9G0R2qif3gLQYvEn3yeq";

    PaymentResponseDto createOrder(PaymentRequestDto paymentRequestDto) throws RazorpayException;
}
