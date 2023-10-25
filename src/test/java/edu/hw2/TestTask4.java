package edu.hw2;

import edu.hw2.Task4.CallingInfo;
import edu.hw2.Task4.CallingUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask4 {
    @Test
    public void TestMethodLogger() {
        var callingInfo = new classA().methodA();
        var callingInfo2 = new classA().methodB();

        assertThat(callingInfo).isEqualTo(new CallingInfo("edu.hw2.TestTask4$classA", "methodA"));
        assertThat(callingInfo2).isEqualTo(new CallingInfo("edu.hw2.TestTask4$classA", "methodB"));
    }

    public static class classA {
        public CallingInfo methodA() {
            return new CallingUtil().getCallingInfo();
        }

        public CallingInfo methodB() {
            return new CallingUtil().getCallingInfo();
        }
    }
}
