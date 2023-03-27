package com.Ecommers.shopping.Service;

import com.Ecommers.shopping.DtoRequest.OrderRequestDto;
import com.Ecommers.shopping.DtoResponse.OrderResponseDto;
import com.Ecommers.shopping.Exception.CustomerNotFoundException;

import java.util.List;

public interface CartService {
    String addToCart(OrderRequestDto orderRequestDto) throws Exception;


    List<OrderResponseDto> checkOutCart(int customerId) throws Exception;
}
