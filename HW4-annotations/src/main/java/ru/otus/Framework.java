package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

public class Framework {

    /**
     * Запускает набор методов, помеченный аннотациями Before-Test-After в классе
     *
     * @param classWithAnnotations
     * @throws InterruptedException
     */
    public static void runTestClass(Class<?> classWithAnnotations) throws InterruptedException {
        Object testClass = ReflectionHelper.instantiate(classWithAnnotations);
        Method[] methodsBefore = ReflectionHelper.getMethods(testClass, Before.class);
        Method[] methodsTest = ReflectionHelper.getMethods(testClass, Test.class);
        Method[] methodsAfter = ReflectionHelper.getMethods(testClass, After.class);
        ReflectionHelper.callMethods(testClass, methodsBefore != null ? methodsBefore : new Method[0]);
        ReflectionHelper.callMethods(testClass, methodsTest != null ? methodsTest : new Method[0]);
        Thread.sleep(100);
        ReflectionHelper.callMethods(testClass, methodsAfter != null ? methodsAfter : new Method[0]);

    }

    /**
     * Получает все классы в package и для каждого вызывает runTestClass
     *
     * @param packageName
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws InterruptedException
     */
    public static void runPackage(String packageName) throws ClassNotFoundException, IOException, InterruptedException {
        for (Class<?> cls : ReflectionHelper.getClasses(packageName)) {
            runTestClass(cls);
        }
    }
}
