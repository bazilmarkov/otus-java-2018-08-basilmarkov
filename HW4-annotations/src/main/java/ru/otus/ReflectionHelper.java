package ru.otus;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;


class ReflectionHelper {
    private ReflectionHelper() {
    }

    /**
     *
     * @param type
     * @param args
     * @param <T>
     * @return
     */
    static <T> T instantiate(Class<T> type, Object... args) {
        try {
            if (args.length == 0) {
                return type.getDeclaredConstructor().newInstance();
            } else {
                Class<?>[] classes = toClasses(args);
                return type.getDeclaredConstructor(classes).newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param object
     * @param name
     * @return
     */
    static Object getFieldValue(Object object, String name) {
        Field field = null;
        boolean isAccessible = true;
        try {
            field = object.getClass().getDeclaredField(name); //getField() for public fields
            isAccessible = field.isAccessible();
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (field != null && !isAccessible) {
                field.setAccessible(false);
            }
        }
        return null;
    }

    /**
     *
     * @param object
     * @param name
     * @param value
     */
    static void setFieldValue(Object object, String name, Object value) {
        Field field = null;
        boolean isAccessible = true;
        try {
            field = object.getClass().getDeclaredField(name); //getField() for public fields
            isAccessible = field.isAccessible();
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (field != null && !isAccessible) {
                field.setAccessible(false);
            }
        }
    }

    /**
     * Вызывает метод с переменным числом аргументов
     * @param object
     * @param name
     * @param args
     * @return
     */
    static Object callMethod(Object object, String name, Object... args) {
        Method method = null;
        boolean isAccessible = true;
        try {
            if (args == null) {
                method = object.getClass().getMethod(name);
            } else {
                method = object.getClass().getDeclaredMethod(name, toClasses(args));
            }
            isAccessible = method.isAccessible();
            method.setAccessible(true);
            return method.invoke(object, args);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.getCause().printStackTrace();
        } finally {
            if (method != null && !isAccessible) {
                method.setAccessible(false);
            }
        }
        return null;
    }

    /**
     * Возвращает массив методов обьекта
     * @param object
     * @param annotation
     * @return
     */
    static Method[] getMethods(Object object, Class<? extends Annotation> annotation) {
        Method[] methods;
        try {

            methods = Arrays.stream(object.getClass().getDeclaredMethods())
                    .filter(x -> x.isAnnotationPresent(annotation))
                    .toArray(Method[]::new);
            return methods;
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Вызывает каждый из переданного массива методов обьекта
     * @param testClass
     * @param methods
     */
    static void callMethods(Object testClass, Method[] methods) {
        for (Method method : methods) {
            ReflectionHelper.callMethod(testClass, method.getName(), (Object[]) null);
        }

    }

    /**
     *
     * @param args
     * @return
     */
    static private Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    /**
     * Возвращает  List классов в package с использованием библиотеки GoogleGuava
     * @param packageName
     * @return
     * @throws ClassNotFoundException
     * @throws IOException
     */
    static ArrayList<Class<?>> getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (ClassPath.ClassInfo classInfo : ClassPath.from(Thread.currentThread().getContextClassLoader()).getTopLevelClasses(packageName)) {
            Class cls = classInfo.load();
            if (!cls.isInterface()) {
                classes.add(cls);
            }
        }
        return classes;
    }
}
