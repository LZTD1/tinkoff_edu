package edu.hw9.Task3;

import edu.hw9.Task3.Entities.Node;
import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class DeepSearch extends RecursiveTask<Boolean> {

    private final int search;
    private final Node node;

    public DeepSearch(Node node, int search) {
        this.node = node;
        this.search = search;
    }

    @Override
    protected Boolean compute() {
        if (this.node.getValue() != this.search) {
            ArrayList<DeepSearch> tasks = new ArrayList<>();
            for (Node children : this.node.getNodes()) {
                DeepSearch task = new DeepSearch(children, this.search);
                tasks.add(task);
                task.fork();
            }

            for (DeepSearch task : tasks) {
                if (task.join()) {
                    return true;
                }
            }
        } else {
            return true;
        }

        return false;
    }
}
