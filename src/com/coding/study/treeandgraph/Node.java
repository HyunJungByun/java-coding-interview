package com.coding.study.treeandgraph;

public class Node {
    public String name;
    public Node[] children;
    public State state;

    public Node[] getAdjacent() {
        return children;
    }
}
