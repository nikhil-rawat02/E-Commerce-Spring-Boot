package com.Ecommers.shopping.converter;

import com.Ecommers.shopping.DtoRequest.ProductRequestDto;
import com.Ecommers.shopping.DtoResponse.ProductResponseDto;
import com.Ecommers.shopping.Model.Product;
import com.Ecommers.shopping.Model.Seller;

public class ProductConverter {

    public static Product productRequestDtoToProduct(ProductRequestDto product, Seller seller){
        String productDimens = product.getWeight() + "$" + product.getHeight() + "$" + product.getWidth() + "$" + product.getLength();

        return Product.builder()
                .name(product.getName())
                .productDetail(product.getProductDetail()).price(product.getPrice())
                .productCategory(product.getProductCategory())
                .quantity(product.getQuantity())
                .seller(seller)
                .productDimension(productDimens)
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productCategory(product.getProductCategory())
                .productDetail(product.getProductDetail())
                .sellerName(product.getSeller().getName())
                .sellerAddress(product.getSeller().getSellerAddress())
                .productDimension(product.getProductDimension())
                .build();
    }
}
