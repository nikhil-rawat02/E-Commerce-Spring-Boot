package com.Ecommers.shopping.Service;

import com.Ecommers.shopping.DtoRequest.CardRequestDto;
import com.Ecommers.shopping.Exception.CustomerNotFoundException;

import java.sql.SQLException;

public interface CardService {

    public void addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException, SQLException;
}
