package com.Ecommers.shopping.Service.Implementation;

import com.Ecommers.shopping.DtoRequest.PaymentRequestDto;
import com.Ecommers.shopping.DtoResponse.PaymentResponseDto;
import com.Ecommers.shopping.Service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class PaymentServiceImpl implements PaymentService {

    RazorpayClient client;

    @Override
    public PaymentResponseDto createOrder(PaymentRequestDto paymentRequestDto) throws RazorpayException {
        PaymentResponseDto responseDto = new PaymentResponseDto();
        client = new RazorpayClient(SECRET_ID,SECRET_KEY);

        Order order = createRazorPayOrder(paymentRequestDto.getAmount());
        System.out.println("-------------------");
        String orderId = order.get("id");
        System.out.println("Order Id : " + orderId);
        System.out.println("-------------------");
        responseDto.setRazorPayOrderId(orderId);
        responseDto.setSecretId(SECRET_ID);
        responseDto.setSecretKey(SECRET_KEY);
        responseDto.setApplicationFee(paymentRequestDto.getAmount().toString());
        responseDto.setPgName("Razor Pay"); // pg : Payment Gateway
        // in db i can set primary key order id, order status, payment mode / Payment Gateway , customer id and customer to payment is one to many relation
        return responseDto;

    }

    private Order createRazorPayOrder(BigInteger amount) throws RazorpayException {
        JSONObject object = new JSONObject();
        object.put("amount",amount.multiply(new BigInteger("100")));
        object.put("currency","INR");
        object.put("receipt","txn_123456");
        object.put("payment_capture",1);
        return client.orders.create(object);
    }
}
