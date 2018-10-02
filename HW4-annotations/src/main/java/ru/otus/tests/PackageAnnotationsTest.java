package ru.otus.tests;

import ru.otus.Assert;
import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class PackageAnnotationsTest {

    @Before
    public void before() {
        System.out.println("----------");
        System.out.println("Run before test in package");
        System.out.println("----------");
    }

    @After
    public void after() {
        System.out.println("----------");
        System.out.println("Run after test in package");
    }

    @Test
    public void testIsEquals() {
        System.out.println("Starting test in package");
        Assert.assertEquals("Objects not equal", 2, 2);
    }
}
