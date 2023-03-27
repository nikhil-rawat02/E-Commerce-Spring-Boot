package com.Ecommers.shopping.Exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
