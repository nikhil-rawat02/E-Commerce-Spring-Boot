package com.Ecommers.shopping.Controller;

import com.Ecommers.shopping.DtoRequest.CardRequestDto;

import com.Ecommers.shopping.Exception.CustomerNotFoundException;
import com.Ecommers.shopping.Service.Implementation.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardServiceImpl cardService;

    @PostMapping("/add")
    public String addCard(@RequestBody CardRequestDto cardRequestDto){

        try{
            cardService.addCard(cardRequestDto);
        }catch (CustomerNotFoundException | SQLException e) {
            return e.getMessage();
        }
        return "card Saved";
    }
}
