package com.Ecommers.shopping.DtoResponse;

import com.Ecommers.shopping.Enum.ProductCategory;
import com.Ecommers.shopping.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponseDto {
    private String productName;
    private int price;
    private ProductCategory productCategory;
    private ProductStatus productStatus;

}
