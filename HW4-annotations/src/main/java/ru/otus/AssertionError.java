package ru.otus;

public class AssertionError extends Error {
    public AssertionError(String message) {
        
        super(message);
        System.out.println("----------");
    }
}
