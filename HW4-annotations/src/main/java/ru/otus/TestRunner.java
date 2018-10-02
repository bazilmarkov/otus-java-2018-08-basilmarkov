package ru.otus;

import java.io.IOException;

public class TestRunner {

    /**
     * Точка входа приложения
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Framework.runPackage("ru.otus.tests");
            Framework.runTestClass(AnnotationsTest.class);
        } catch (ClassNotFoundException | IOException | InterruptedException e) {
            System.out.println("The internal exception was thrown " + e.getMessage());
        }
    }

}
