package edu.hw11.Task3;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;

@SuppressWarnings("all")
public class FibGenerator {

    private FibGenerator() {
    }

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

        public Impl() {
        }

        @Override
        public Size apply(
            MethodVisitor mv,
            Implementation.Context context,
            MethodDescription methodDescription
        ) {
            var biggerThanThree = new Label();

            mv.visitVarInsn(Opcodes.ILOAD, 0); // Load to stack N
            mv.visitInsn(Opcodes.ICONST_3); // Load to stack 3
            mv.visitJumpInsn(Opcodes.IF_ICMPGE, biggerThanThree); // Compare if N >= 3
            // if smaller
            mv.visitInsn(Opcodes.ICONST_1); // Load to stack 1
            mv.visitInsn(Opcodes.IRETURN); // Return from func

            mv.visitLabel(biggerThanThree); // if N >= 3
            mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null); // Copy frame stat
            mv.visitVarInsn(Opcodes.ILOAD, 0); // Load to stack N
            mv.visitInsn(Opcodes.ICONST_2); // Load to stack 2
            mv.visitInsn(Opcodes.ISUB); // Subtraction N - 2
            // Invoke same method
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "FibCalc", "fib", "(I)I", false);

            mv.visitVarInsn(Opcodes.ILOAD, 0); // Load to stack N
            mv.visitInsn(Opcodes.ICONST_1); // Load to stack 1
            mv.visitInsn(Opcodes.ISUB); // Subtraction N - 1
            // Invoke same method
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "FibCalc", "fib", "(I)I", false);

            mv.visitInsn(Opcodes.IADD); // Summarizing fib(n-2) + fib(n-1)
            mv.visitInsn(Opcodes.IRETURN); // Return from func

            return new Size(3, 1);
        }

    }

}
