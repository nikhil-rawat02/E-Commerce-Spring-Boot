package com.Ecommers.shopping.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="seller")
public class Seller {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    @Column(unique = true)
    private String mobileNo;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String panNo;

    private String sellerAddress;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    List<Product> productList = new ArrayList<>();

}
