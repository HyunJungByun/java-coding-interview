package com.coding.study.stackandqueue.exam2;

import java.util.Stack;

public class StackWithMin extends Stack {
    Stack stack2;

    public StackWithMin() {
        stack2 = new Stack();
    }

    public void push(int value) {
        if (value <= min()) {
            stack2.push(value);
        }
        super.push(value);
    }

    public Integer pop() {
        int value = (int) super.pop();
        if (value == min()) {
            stack2.pop();
        }
        return value;
    }

    public int min() {
        if (stack2.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return (int) stack2.peek();
        }
    }
}

