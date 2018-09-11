package ru.otus;

import java.lang.instrument.Instrumentation;

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
}