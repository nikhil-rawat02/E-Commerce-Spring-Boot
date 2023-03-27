package com.Ecommers.shopping.Model;

import com.Ecommers.shopping.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="ordered")
public class Ordered {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    private Date orderDate;

    private String TransactionId;

    private OrderStatus status;

    private int totalCost;

    private int deliveryCharges;

    private String cardUsedForPayment;

    private String deliveryAddress;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();


}
