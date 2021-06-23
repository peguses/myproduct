package com.myproduct.myproduct.exceptions;

public class CalculationNotSupportedException extends RuntimeException{
    public CalculationNotSupportedException(String message) {
        super(message);
    }
}
