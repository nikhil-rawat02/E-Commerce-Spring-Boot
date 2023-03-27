package com.Ecommers.shopping.DtoResponse;

import com.Ecommers.shopping.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private String name;
    private int price;
    private int quantity;
    private ProductCategory productCategory;
    private String productDetail;
    private String sellerName;
    private String sellerAddress;
    private String productDimension;
}
