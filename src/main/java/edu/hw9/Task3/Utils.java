package edu.hw9.Task3;

import edu.hw9.Task3.Entities.Node;
import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    private Utils() {
    }

    public static Node generateTree(int depth, int maxValue) {
        if (depth == 1) {
            return new Node(ThreadLocalRandom.current().nextInt(maxValue));
        }

        var node = new Node(ThreadLocalRandom.current().nextInt(maxValue));

        var countChildren = ThreadLocalRandom.current().nextInt(1, depth);
        for (int i = 0; i < countChildren; i++) {
            Node child = generateTree(depth - 1, maxValue);
            node.appendNode(child);
        }

        return node;
    }
}
