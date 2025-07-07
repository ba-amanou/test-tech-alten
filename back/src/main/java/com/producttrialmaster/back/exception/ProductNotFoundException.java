package com.producttrialmaster.back.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ApiException{
    public ProductNotFoundException(Long id){
        super("Le produit avec l' identifiant '" + id + "' n'existe pas.", HttpStatus.NOT_FOUND);
    }
}
