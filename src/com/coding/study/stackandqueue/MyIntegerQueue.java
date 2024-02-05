package com.coding.study.stackandqueue;

import java.util.NoSuchElementException;

public class MyIntegerQueue {
    private QueueNode first;
    private QueueNode last;

    public void add(Integer item) {
        QueueNode t = new QueueNode(item);
        if (last != null) {
            last.next = t;
        }
        last = t;
        if (first == null) {
            first = last;
        }
    }

    public Integer remove() {
        if (first == null) throw new NoSuchElementException();
        Integer data = first.data;
        first = first.next;
        if (first == null) {
            last = null;
        }
        return data;
    }

    public Integer peek() {
        if (first == null) throw new NoSuchElementException();
        return first.data;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private static class QueueNode {
        private final Integer data;
        private QueueNode next;

        public QueueNode(Integer data) {
            this.data = data;
        }
    }
}
