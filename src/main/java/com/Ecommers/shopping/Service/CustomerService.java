package com.Ecommers.shopping.Service;

import com.Ecommers.shopping.DtoRequest.CustomerRequestDto;
import com.Ecommers.shopping.DtoResponse.CustomerResponseDto;
import com.Ecommers.shopping.Exception.CustomerNotFoundException;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws SQLException;

    public CustomerResponseDto getCustomerByMobile(String mobileNo) throws CustomerNotFoundException;

    public CustomerResponseDto getCustomerByEmail(String email) throws CustomerNotFoundException;

    public List<CustomerResponseDto> getAllCustomer();

    List<String> deleteCustomer(int customerId) throws CustomerNotFoundException;

}
