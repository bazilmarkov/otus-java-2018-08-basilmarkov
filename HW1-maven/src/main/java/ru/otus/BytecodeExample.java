package ru.otus;


public class BytecodeExample {
    private final String name;

    public BytecodeExample(String name) {
        this.name = name;
    }


    public  void printName(){
        System.out.println("Message from another class for " + this.name);
    }
}