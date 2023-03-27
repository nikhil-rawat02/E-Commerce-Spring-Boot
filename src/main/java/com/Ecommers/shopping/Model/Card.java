package com.Ecommers.shopping.Model;

import com.Ecommers.shopping.Enum.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="card")
public class Card {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String cardNo;

    private String cvv;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @ManyToOne
    @JoinColumn
    Customer customer;



}
