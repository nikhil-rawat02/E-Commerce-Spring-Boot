package com.Ecommers.shopping.Service.Implementation;

import com.Ecommers.shopping.DtoRequest.CardRequestDto;
import com.Ecommers.shopping.Exception.CustomerNotFoundException;
import com.Ecommers.shopping.Model.Card;
import com.Ecommers.shopping.Model.Customer;
import com.Ecommers.shopping.Repository.CustomerRepository;
import com.Ecommers.shopping.Service.CardService;
import com.Ecommers.shopping.converter.CardConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;
    public void addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException, SQLException {
        Customer customer;
        try{
            customer = customerRepository.findById(cardRequestDto.getCustomerId()).get();
        }catch (Exception e){
            throw new CustomerNotFoundException("Enter a valid Customer Id");
        }

        Card card = CardConverter.CardRequestDtoToCard(cardRequestDto,customer);

        customer.getCards().add(card);

        try{
            customerRepository.save(customer);
        }catch (Exception e){
            throw new SQLException("This Card already Exist!");
        }

    }
}
