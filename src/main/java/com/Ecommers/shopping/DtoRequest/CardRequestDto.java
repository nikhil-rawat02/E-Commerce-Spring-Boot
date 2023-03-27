package com.Ecommers.shopping.DtoRequest;

import com.Ecommers.shopping.Enum.CardType;
import com.Ecommers.shopping.Model.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto {

    private String cardNo;

    private String cvv;

    private CardType cardType;

    private int customerId;
}
