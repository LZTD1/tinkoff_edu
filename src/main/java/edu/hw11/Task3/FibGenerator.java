package edu.hw11.Task3;

import java.lang.reflect.Method;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

public class FibGenerator {

    public static Class<?> generateFibClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("FibCalc")
            .defineMethod(
                "fib",
                int.class,
                Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC
            )
            .withParameter(int.class, "number")
            .intercept(
                new Implementation.Simple(new Impl())
            )
            .make()
            .load(
                ClassLoader.getSystemClassLoader()
            )
            .getLoaded();
    }

    public static class Impl implements ByteCodeAppender {

        @Override
        public Size apply(
            MethodVisitor mv,
            Implementation.Context context,
            MethodDescription methodDescription
        ) {
            var lbl = new Label();


            // Загружаем значение на вершину стека
            mv.visitInsn(Opcodes.ICONST_3);


            mv.visitLabel(lbl);

            mv.visitInsn(Opcodes.ICONST_5);


            mv.visitInsn(Opcodes.IRETURN);

            return new Size(2, 1);
        }

    }

    public static void main(String[] args) throws Exception {
        Class<?> fibClass = generateFibClass();
        Object fibInstance = fibClass.getDeclaredConstructor().newInstance();

        Method fibMethod = fibClass.getMethod("fib", int.class);
        System.out.println(
            fibMethod.invoke(fibInstance, 45)
        );
    }
}
