package com.producttrialmaster.back.exception;

public class DuplicateProductCodeException extends RuntimeException{
    public DuplicateProductCodeException(String code){
        super("Le produit avec le code '" + code + "' existe déjà.");
    }
}
