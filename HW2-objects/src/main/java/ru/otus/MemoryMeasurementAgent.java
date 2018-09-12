package ru.otus;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class MemoryMeasurementAgent {

    private static volatile Instrumentation globalInstrumentation;

    public static void premain(final String agentArgs, final Instrumentation instrumentation) {
        globalInstrumentation = instrumentation;
    }

    public static long getObjectSize(final Object object) {
        if (globalInstrumentation == null) {
            throw new IllegalStateException("Agent not initialized");
        }
        return globalInstrumentation.getObjectSize(object);
    }

    public static long getObjectDeepSize(final Object object) {
        long totalSize = 0;
        ArrayList<Object> visitedObjects = new ArrayList<Object>();
        Queue<Object> stack = Collections.asLifoQueue(new ArrayDeque<>());
        stack.add(object);
        while (!stack.isEmpty()) {
            totalSize += getInternalObjectSize(stack.poll(), stack, visitedObjects);
        }

        return totalSize;

    }


    private static long getInternalObjectSize(Object object, Queue stack, ArrayList visitedObjects) {
        if (skipObject(object, visitedObjects)) {
            return 0;
        }

        Class currentClass = object.getClass();
        if (currentClass.isArray()) {
            addArrayElementsToStack(currentClass, object, stack);
        } else {
            while (currentClass != null) {
                Field[] fields = currentClass.getDeclaredFields();
                for (Field field : fields) {
                    if (!Modifier.isStatic(field.getModifiers())
                            && !field.getType().isPrimitive()) {
                        field.setAccessible(true);
                        try {
                            if (field.get(object) != null) {
                                stack.add(field.get(object));
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                currentClass = currentClass.getSuperclass();
            }
        }
        visitedObjects.add(object);
        return getObjectSize(object);
    }


    private static boolean skipObject(Object object, ArrayList visitedObjects) {
        return object == null
                || visitedObjects.contains(object)
                || isNotContainsInnerObjects(object);
    }


    private static boolean isNotContainsInnerObjects(Object object) {
        if (object instanceof Comparable) {
            if (object instanceof Enum || object instanceof String || object instanceof Boolean || object instanceof Integer
                    || object instanceof Short || object instanceof Byte || object instanceof Long || object instanceof Character)
                return true;
        }
        return false;
    }

    private static void addArrayElementsToStack(
            Class currentClass, Object object, Queue stack) {
        if (!currentClass.getComponentType().isPrimitive()) {
            int length = Array.getLength(object);
            for (int i = 0; i < length; i++) {
                stack.add(Array.get(object, i));
            }
        }
    }
}
