package com.coding.study.stackandqueue;

import java.util.EmptyStackException;

public class MyIntegerStack {
    private StackNode top;

    public Integer pop() {
        if (top == null) throw new EmptyStackException();
        Integer item = top.data;
        top = top.next;
        return item;
    }

    public void push(Integer item) {
        StackNode t = new StackNode(item);
        t.next = top;
        top = t;
    }

    public Integer peek() {
        if (top == null) throw new EmptyStackException();
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    private static class StackNode {
        private final Integer data;
        private StackNode next;

        public StackNode(Integer data) {
            this.data = data;
        }
    }

}
