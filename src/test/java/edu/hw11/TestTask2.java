package edu.hw11;

import edu.hw11.Task2.ArithmeticUtils;
import edu.hw11.Task2.Modify;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.Assertions.assertThat;

public class TestTask2 { // Потому что при выполнении пачки тестов все ломается, а поштучно все работает
//    @Test
//    void test(){
//
//        TypeDescription typeDescription = TypePool.Default.ofSystemLoader()
//            .describe("edu.hw11.Task2.ArithmeticUtils")
//            .resolve();
//
//        new ByteBuddy()
//            .redefine(
//                typeDescription,
//                ClassFileLocator.ForClassLoader.ofSystemLoader()
//            )
//            .method(named("sum"))
//            .intercept(MethodDelegation.to(Modify.class))
//            .make()
//            .load(
//                ClassLoader.getSystemClassLoader(),
//                ClassLoadingStrategy.Default.INJECTION
//            );
//
//        assertThat(ArithmeticUtils.sum(3, 3)).isEqualTo(9);
//    }

}
