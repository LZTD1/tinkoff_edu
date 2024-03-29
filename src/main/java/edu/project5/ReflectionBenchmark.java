package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import static java.lang.invoke.MethodType.methodType;

@State(Scope.Thread)
@SuppressWarnings("all")
public class ReflectionBenchmark {

    private Student student;
    private Method method;
    private MethodHandle handle;
    private CallSite callSite;
    private Supplier<String> lambdaFunction;

    record Student(String name, String surname) {
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(ReflectionBenchmark.class.getSimpleName())
            .shouldFailOnError(true) // thrown on error
            .shouldDoGC(true) // garbage collector before each
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(1)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(1)
            .measurementTime(TimeValue.seconds(240))
            .build();

        new Runner(options).run();
    }

    @Setup
    public void setup() throws Throwable {
        student = new Student("Alexander", "Biryukov");
        method = student.getClass().getMethod("name");

        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType methodType = methodType(String.class); // Returned type
        handle = lookup.findVirtual(Student.class, "name", methodType);

        callSite = LambdaMetafactory.metafactory(
            lookup,
            "get",
            MethodType.methodType(Supplier.class, Student.class), //signature CallSite
            MethodType.methodType(Object.class), // Signature and return type
            handle, // method to invoke
            MethodType.methodType(String.class) // Casting to String
        );

        lambdaFunction = (Supplier<String>) callSite.getTarget().invokeExact(student);
    }

    @Benchmark
    public void directAccess(Blackhole bh) {
        Object name = student.name();
        bh.consume(name);
    }

    @Benchmark
    public void reflection(Blackhole bh) throws InvocationTargetException, IllegalAccessException {
        Object name = method.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void methodHandlers(Blackhole bh) throws Throwable {
        Object name = handle.invoke(student);
        bh.consume(name);
    }

    @Benchmark
    public void lambdaMetafactory(Blackhole bh) {
        String name = lambdaFunction.get();
        bh.consume(name);
    }
}
