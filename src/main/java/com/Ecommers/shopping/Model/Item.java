package com.Ecommers.shopping.Model;

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
@Table(name ="item")
public class Item {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    private int requiredQuantity;

    @ManyToOne
    @JoinColumn
    Cart cart;

    @OneToOne
    @JoinColumn
    Product product;

    @ManyToOne
    Ordered order;
}
