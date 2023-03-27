package com.Ecommers.shopping.Controller;

import com.Ecommers.shopping.DtoRequest.OrderRequestDto;
import com.Ecommers.shopping.DtoResponse.OrderResponseDto;
import com.Ecommers.shopping.Service.Implementation.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartServiceImpl cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody OrderRequestDto orderRequestDto){
        String message;
        try{
            message = cartService.addToCart(orderRequestDto);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message,HttpStatus.ACCEPTED);

    }

    @PostMapping("/checkOut/{id}")
    public ResponseEntity checkOutCart( @PathVariable("id") int customerId){
        List<OrderResponseDto> responseDtoList;
        try{
            responseDtoList = cartService.checkOutCart(customerId);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(responseDtoList,HttpStatus.CREATED);
    }
}
