package com.Ecommers.shopping.Service.Implementation;

import com.Ecommers.shopping.Communication.EmailService;
import com.Ecommers.shopping.Communication.MobileCommunication;
import com.Ecommers.shopping.DtoRequest.CustomerRequestDto;
import com.Ecommers.shopping.DtoResponse.CustomerResponseDto;
import com.Ecommers.shopping.Exception.CustomerNotFoundException;
import com.Ecommers.shopping.Model.Cart;
import com.Ecommers.shopping.Model.Customer;
import com.Ecommers.shopping.Repository.CartRepository;
import com.Ecommers.shopping.Repository.CustomerRepository;
import com.Ecommers.shopping.Service.CustomerService;
import com.Ecommers.shopping.converter.CustomerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;


    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws SQLException {
        Customer customer = CustomerConverter.customerRequestDtoToCustomer(customerRequestDto);
        EmailService emailService = new EmailService();
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);
        customer.setCart(cart);
        try{
//            emailService.send(customer.getEmail(),"Created","THis is text");
            MobileCommunication.thankYou(customer.getMobileNo());
            customerRepository.save(customer);
        }catch (Exception e){
            e.printStackTrace();
            throw new SQLException("User already exist by using this Email or mobile number!");
        }

        return  CustomerConverter.customerToCustomerResponseDto(customer,customer.getCart().getItems().size());
    }

    @Override
    public CustomerResponseDto getCustomerByMobile(String mobileNo) throws CustomerNotFoundException {
        int customerId = customerRepository.findByMobileNo(mobileNo);
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }catch (Exception e){
            throw new CustomerNotFoundException("Mobile no Does not exit, Enter a valid Mobile No!");
        }

        return CustomerConverter.customerToCustomerResponseDto(customer,customer.getCart().getItems().size());
    }

    @Override
    public CustomerResponseDto getCustomerByEmail(String email) throws CustomerNotFoundException {
        int customerId = customerRepository.findByEmail(email);
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }catch (Exception e){
            throw new CustomerNotFoundException("Mobile no Does not exit, Enter a valid Mobile No!");
        }

        return CustomerConverter.customerToCustomerResponseDto(customer,customer.getCart().getItems().size());

    }

    @Override
    public List<CustomerResponseDto> getAllCustomer() {
        List<Integer> customerIds = customerRepository.getAllCustomerIds();
        List<CustomerResponseDto> customers = new ArrayList<>();

        for(Integer customerId : customerIds){
            Customer customer = customerRepository.findById(customerId).get();
            CustomerResponseDto customerResponseDto = CustomerConverter.customerToCustomerResponseDto(customer,customer.getCart().getItems().size());
            customers.add(customerResponseDto);
        }
        return customers;
    }

    @Override
    public List<String> deleteCustomer(int customerId) throws CustomerNotFoundException {
        Customer customer;
        EmailService emailService = new EmailService();
        try {
            customer = customerRepository.findById(customerId).get();
        }catch (NoSuchElementException e){
            throw new CustomerNotFoundException("Enter a valid Customer Id!");
        }
        List<String> customerInfo = new ArrayList<>();
        customerInfo.add(customer.getEmail());
        customerInfo.add(customer.getMobileNo());
        customerInfo.add(Integer.toString(customer.getId()));
        cartRepository.delete(customer.getCart());

        emailService.send(customer.getEmail(),"Deleted", "THis is text");
        customerRepository.delete(customer);


        return customerInfo;
    }

}
