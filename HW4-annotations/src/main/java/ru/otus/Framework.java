package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Method;

public class Framework {

    public static void runTestClass(Class<?> classWithAnnotations) {
        Object testClass = ReflectionHelper.instantiate(classWithAnnotations);
        Method[] methodsBefore = ReflectionHelper.getMethods(testClass, Before.class);
        Method[] methodsTest = ReflectionHelper.getMethods(testClass, Test.class);
        Method[] methodsAfter = ReflectionHelper.getMethods(testClass, After.class);
        ReflectionHelper.callMethods(testClass, methodsBefore != null ? methodsBefore : new Method[0]);
        ReflectionHelper.callMethods(testClass, methodsTest != null ? methodsTest : new Method[0]);
        ReflectionHelper.callMethods(testClass, methodsAfter != null ? methodsAfter : new Method[0]);

    }

    public void runPackage(String packageName) {

    }
}
