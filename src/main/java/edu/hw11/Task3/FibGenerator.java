package edu.hw11.Task3;

import java.lang.reflect.Method;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

public class FibGenerator {

    public static Class<?> generateFibClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("FibCalc")
            .defineMethod(
                "returnFive",
                int.class,
                Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC
            )
            .intercept(
                new Implementation.Simple(new Impl())
            )
            .make()
            .load(
                ClassLoader.getSystemClassLoader(),
                ClassLoadingStrategy.Default.WRAPPER
            )
            .getLoaded();
    }

    public static class Impl implements ByteCodeAppender {

        @Override
        public Size apply(
            MethodVisitor methodVisitor,
            Implementation.Context context,
            MethodDescription methodDescription
        ) {
            methodVisitor.visitCode();

            // Загрузка константы 5 на стек
            methodVisitor.visitLdcInsn(5);
            methodVisitor.visitInsn(Opcodes.IRETURN);

            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();

            return new Size(2, 2);
        }

    }

    public static void main(String[] args) throws Exception {
        Class<?> fibClass = generateFibClass();
        Object fibInstance = fibClass.getDeclaredConstructor().newInstance();

        Method returnFiveMethod = fibClass.getMethod("returnFive");
        System.out.println(returnFiveMethod.invoke(fibInstance));
    }
}
