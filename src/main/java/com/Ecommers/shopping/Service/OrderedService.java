package com.Ecommers.shopping.Service;

import com.Ecommers.shopping.DtoRequest.OrderRequestDto;
import com.Ecommers.shopping.DtoResponse.OrderResponseDto;
import com.Ecommers.shopping.Exception.CustomerNotFoundException;
import com.Ecommers.shopping.Exception.ProductNotFoundException;

import javax.naming.InsufficientResourcesException;

public interface OrderedService {
    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws Exception;
}
