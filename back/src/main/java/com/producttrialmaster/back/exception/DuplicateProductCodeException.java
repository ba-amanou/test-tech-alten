package com.producttrialmaster.back.exception;

import org.springframework.http.HttpStatus;

public class DuplicateProductCodeException extends ApiException{
    public DuplicateProductCodeException(String code){
        super("Le produit avec le code '" + code + "' existe déjà.", HttpStatus.CONFLICT);
    }
}
