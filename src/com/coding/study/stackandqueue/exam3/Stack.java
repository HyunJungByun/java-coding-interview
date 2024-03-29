package com.coding.study.stackandqueue.exam3;

import org.w3c.dom.Node;

public class Stack {
    public Node top, bottom;
    public int size = 0;
    private final int capacity;

    public Stack(int capacity) {
        this.capacity = capacity;
    }

    public boolean isFull() {
        return capacity == size;
    }

    public void join(Node above, Node below) {
        if (below != null) {
            below.above = above;
        }
        if (above != null) {
            above.below = below;
        }
    }

    public boolean push(int v) {
        if (size >= capacity) return false;
        size++;
        Node n = new Node(v);
        if (size == 1) {
            bottom = n;
        }
        join(n, top);
        top = n;
        return true;
    }

    public int pop() {
        Node t = top;
        top = top.below;
        size--;
        return t.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int removeBottom() {
        Node b = bottom;
        bottom = bottom.above;
        if (bottom != null) {
            bottom.below = null;
            size--;
            return b.value;
        }
    }
}
