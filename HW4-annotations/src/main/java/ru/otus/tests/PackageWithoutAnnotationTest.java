package ru.otus.tests;

import ru.otus.Assert;


public class PackageWithoutAnnotationTest {

    public void before() {
        System.out.println("Run before test in package (never used)");
    }


    public void after() {
        System.out.println("Run after test in package (never used)");
    }


    public void testIsEquals() {
        System.out.println("Starting test in package (never used)");
        Assert.assertEquals("Objects not equal", 2, 3);
    }
}
