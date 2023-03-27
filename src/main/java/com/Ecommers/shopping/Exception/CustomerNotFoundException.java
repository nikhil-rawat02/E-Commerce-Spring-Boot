package com.Ecommers.shopping.Exception;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
