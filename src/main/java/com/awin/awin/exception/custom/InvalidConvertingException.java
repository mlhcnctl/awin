package com.awin.awin.exception.custom;

public class InvalidConvertingException extends RuntimeException{
    public InvalidConvertingException() {
        super("Error acquired while converting from source currency to target currency.");
    }
}
