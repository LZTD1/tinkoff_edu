package edu.project5;

import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.function.Function;

public class LambdaMetafactoryExample {
    interface MyInterface {
        void myMethod();
    }

    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup caller = MethodHandles.lookup();
        MethodType invokedType = MethodType.methodType(MyInterface.class);
        MethodType methodType = MethodType.methodType(void.class);

        CallSite site = LambdaMetafactory.metafactory(caller,
            "myMethod",
            invokedType,
            methodType,
            caller.findStatic(LambdaMetafactoryExample.class, "myMethodImpl", methodType),
            methodType);

        MyInterface myInterfaceInstance = (MyInterface) site.getTarget().invokeExact();

        // вызов метода
        myInterfaceInstance.myMethod();
    }

    private static void myMethodImpl() {
        System.out.println("Hello, world!");
    }
}
