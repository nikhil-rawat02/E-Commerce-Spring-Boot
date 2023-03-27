package com.Ecommers.shopping.converter;

import com.Ecommers.shopping.DtoRequest.CardRequestDto;
import com.Ecommers.shopping.Model.Card;
import com.Ecommers.shopping.Model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardConverter {
    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto, Customer customer){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .customer(customer)
                .build();
    }
}
