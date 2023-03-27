package com.Ecommers.shopping.Model;

import com.Ecommers.shopping.Enum.ProductCategory;
import com.Ecommers.shopping.Enum.ProductStatus;
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
@Table(name ="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int price;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    private String productDetail;

    private String productDimension; // weight$height$width$length

    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToOne(mappedBy = "product",cascade = CascadeType.ALL)
    Item item;

}
