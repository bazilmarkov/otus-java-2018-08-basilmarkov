package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class AnnotationsTest {

    @Before
    public void before() {
        System.out.println("----------");
        System.out.println("Run before test in class");
        System.out.println("----------");
    }

    @After
    public void after() {
        System.out.println("----------");
        System.out.println("Run after test in class");
    }

    @Test
    public void testIsEquals() {
        System.out.println("Starting test in class");
        Assert.assertEquals("Objects not equal", "1", "2");
    }
}
