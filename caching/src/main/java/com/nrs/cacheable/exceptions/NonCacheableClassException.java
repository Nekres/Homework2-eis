/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nrs.cacheable.exceptions;

/**
 * Thrown when class could not be cacheable.
 * @author root
 */
public class NonCacheableClassException extends NonCacheableException{
    
    private final String message;

    public NonCacheableClassException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message; 
    }
    
    
}
