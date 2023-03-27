package com.Ecommers.shopping.Service;

import com.Ecommers.shopping.DtoRequest.ProductRequestDto;
import com.Ecommers.shopping.DtoResponse.ProductResponseDto;
import com.Ecommers.shopping.Exception.DimensionParameterNullException;
import com.Ecommers.shopping.Exception.ProductNotFoundException;
import com.Ecommers.shopping.Exception.SellerNotFoundException;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException, DimensionParameterNullException;

    String updateProduct(int productId, ProductRequestDto productRequestDto) throws ProductNotFoundException;


    List<ProductResponseDto> getAllProducts(int sellerId) throws SellerNotFoundException;

    void deleteProductById(int productId) throws ProductNotFoundException;
}
