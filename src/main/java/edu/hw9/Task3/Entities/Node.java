package edu.hw9.Task3.Entities;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private final int value;
    private List<Node> nodes = new ArrayList<>();

    public Node(int value, List<Node> nodes) {
        this.value = value;
        this.nodes = nodes;
    }

    public Node(int value) {
        this.value = value;
    }

    public void setNode(List<Node> node) {
        this.nodes = node;
    }

    public void appendNode(Node node) {
        this.nodes.add(node);
    }

    public List<Node> getNodes() {
        return this.nodes;
    }

    public int getValue() {
        return this.value;
    }
}
