package com.Ecommers.shopping.Controller;

import com.Ecommers.shopping.DtoRequest.PaymentRequestDto;
import com.Ecommers.shopping.DtoResponse.PaymentResponseDto;
import com.Ecommers.shopping.Service.Implementation.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class RazorPayPaymentController {

    @Autowired
    PaymentServiceImpl paymentService;

    @GetMapping("/createOrder")
    public ResponseEntity createOrder(@RequestBody PaymentRequestDto paymentRequestDto){
        PaymentResponseDto responseDto = null;
        try{
            responseDto = paymentService.createOrder(paymentRequestDto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
