package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class AnnotationsTest {

    @Before
    public void before() {
        System.out.println("Run before test");
    }

    @After
    public void after() {
        System.out.println("Run after test");
    }

    @Test
    public void testIsEquals() {
        System.out.println("Starting test");
        Assert.assertEquals("Objects not equal", "1", "2");
    }
}
