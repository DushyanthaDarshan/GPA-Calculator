package com.TeamPhoenix.gpaCalculator.service;

public class GpaCalculatorException extends Exception{

    private String message;

    public GpaCalculatorException () {}

    public GpaCalculatorException (String msg) {
        this.message = msg;
    }

    public String toString () {
        return message;
    }
}
