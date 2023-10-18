package edu.hw2.Task4;

import edu.hw2.Task4.Exceptopns.ThrowableStackError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CallingUtil {

    private final static Logger LOGGER = LogManager.getLogger();
    protected CallingInfo callingInfo;

    public CallingUtil() {
        StackTraceElement[] throwableStack = new Throwable().getStackTrace();
        if (throwableStack.length > 1) {
            this.callingInfo = new CallingInfo(
                throwableStack[1].getClassName(),
                throwableStack[1].getMethodName()
            );
        } else {
            throw new ThrowableStackError("Empty stack!");
        }
    }

    public CallingUtil(int offset) {
        StackTraceElement[] throwableStack = new Throwable().getStackTrace();
        if (throwableStack.length > 1) {
            this.callingInfo = new CallingInfo(
                throwableStack[offset].getClassName(),
                throwableStack[offset].getMethodName()
            );
        } else {
            throw new ThrowableStackError("Empty stack!");
        }
    }

    public CallingInfo getCallingInfo() {
        return this.callingInfo;
    }

    public void outputCallingInfoToLogger() {
        LOGGER.info(
            this.callingInfo.className()
        );
        LOGGER.info(
            this.callingInfo.methodName()
        );
    }

}
