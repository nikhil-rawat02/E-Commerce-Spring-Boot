package com.Ecommers.shopping.converter;

import com.Ecommers.shopping.DtoRequest.CustomerRequestDto;
import com.Ecommers.shopping.DtoResponse.CustomerResponseDto;
import com.Ecommers.shopping.Model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConverter {
    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

      return Customer.builder()
              .name(customerRequestDto.getName())
              .age(customerRequestDto.getAge())
              .email(customerRequestDto.getEmail())
              .mobileNo(customerRequestDto.getMobileNo())
              .build();

    }
    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer,int noOfItems){

        return CustomerResponseDto.builder()
                .CustomerId(customer.getId())
                .age(customer.getAge())
                .name(customer.getName())
                .email(customer.getEmail())
                .mobileNo(customer.getMobileNo())
                .noOfItemsInCart(noOfItems)
                .build();

    }
}
