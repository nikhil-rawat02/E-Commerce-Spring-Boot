package com.Ecommers.shopping.Exception;

public class InsufficientQunatityException extends  Exception{
    InsufficientQunatityException(String errorMessage){
        super(errorMessage);
    }
}
