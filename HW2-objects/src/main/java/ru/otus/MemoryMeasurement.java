package ru.otus;

import java.util.ArrayList;

public class MemoryMeasurement {

    public static void printObjectSize(Object object) {
        System.out.println(object.toString() + " Object type: " + object.getClass() +
                ", size: " + MemoryMeasurementAgent.getObjectSize(object) + " bytes");
    }

    public static void printDeepObjectSize(Object object) {
        System.out.println(object.toString() + " Object type: " + object.getClass() +
                ", size: " + MemoryMeasurementAgent.getObjectDeepSize(object) + " bytes");
    }

    public static void main(String[] arguments) {
        String emptyString = "";
        String string = "Estimating Object Size Using Instrumentation";
        String[] stringArray = {emptyString, string, "random string и кириллица"};
        String[] anotherStringArray = new String[100];
        ArrayList<String> stringList = new ArrayList<String>();
        StringBuilder stringBuilder = new StringBuilder(100);
        int maxIntPrimitive = Integer.MAX_VALUE;
        int minIntPrimitive = Integer.MIN_VALUE;
        Integer maxInteger = Integer.MAX_VALUE;
        Integer minInteger = Integer.MIN_VALUE;
        long zeroLong = 0L;
        double zeroDouble = 0.0;
        boolean falseBoolean = false;
        Object object = new Object();

        class EmptyClass {
        }

        class EmptyClass2 {
        }

        EmptyClass emptyClass = new EmptyClass();

        class StringClass extends EmptyClass2 {
            public String s;
            public EmptyClass deepObjectexample = new EmptyClass();
            public EmptyClass anotherEmptyClass;

            public StringClass(EmptyClass anotherEmptyClass) {
                this.anotherEmptyClass = anotherEmptyClass;
            }
        }
        StringClass stringClass = new StringClass(new EmptyClass());

        printObjectSize(emptyString);
        printObjectSize(string);
        printObjectSize(stringArray);
        printObjectSize(anotherStringArray);
        printObjectSize(stringList);
        printObjectSize(stringBuilder);
        printObjectSize(maxIntPrimitive);
        printObjectSize(minIntPrimitive);
        printObjectSize(maxInteger);
        printObjectSize(minInteger);
        printObjectSize(zeroLong);
        printObjectSize(zeroDouble);
        printObjectSize(falseBoolean);
        printObjectSize(MyFirstNameRandomRepresent.Basil);
        printObjectSize(object);
        printObjectSize(emptyClass);
        printDeepObjectSize(stringClass);
    }

    public enum MyFirstNameRandomRepresent {
        Basil, Bazil, Vasily, Vasilii
    }
}
