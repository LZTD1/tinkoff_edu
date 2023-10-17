package edu.hw2.Task3;

import edu.hw2.Task3.Exceptions.FaultyCommandExecute;
import edu.hw2.Task3.Exceptions.OutOfLimitMaxAttemptsExecutions;
import edu.hw2.Task3.Interfaces.IConnectionManager;

public class CommandExecutor {
    public static final class PopularCommandExecutor {
        private final IConnectionManager manager;
        private final int maxAttempts;
        private final Connection connection;

        public PopularCommandExecutor(IConnectionManager manager, int maxAttempts) {
            this.manager = manager;
            this.connection = manager.getConnection();
            this.maxAttempts = maxAttempts;
        }

        public void updatePackages() {
            tryExecute("apt update && apt upgrade -y");
        }

        public void tryExecute(String command) {
            int currentAttempt = 1;
            while (currentAttempt <= this.maxAttempts) {
                /*
                Я подумал сомнительно будет после каждого tryExecute закрывать соединение
                с помощью try-with-resources, и создавать тем самым сайд эффект
                поэтому я реализовал явное закрытие соединения
                */
                try {
                    manager.executeCommand(command);
                    break;
                } catch (FaultyCommandExecute e) {
                    currentAttempt += 1;
                    if (currentAttempt > this.maxAttempts) {
                        throw new OutOfLimitMaxAttemptsExecutions(
                            "The upper limit of attempts has been reached!",
                            e.getCause()
                        );
                    }
                }
            }
        }
    }

}
