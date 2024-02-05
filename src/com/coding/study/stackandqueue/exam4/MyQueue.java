package com.coding.study.stackandqueue.exam4;

import java.util.Stack;

public class MyQueue {
    Stack stackNewest, stackOldest;

    public MyQueue() {
        stackNewest = new Stack();
        stackOldest = new Stack();
    }

    public int size() {
        return stackOldest.size() + stackNewest.size();
    }

    public void add(T value) {
        // 새로운 원소가 상단에 놓이도록 stackNewest 원소를 삽입
        stackNewest.push(value);
    }

    /*
    stackNewest에서 stackOldest로 원소를 옮긴다
    stackOldest 연산을 수행하기 위한 작업
     */
    private void shiftStacks() {
        if (stackOldest.isEmpty()) {
            while (!stackNewest.isEmpty()) {
                stackOldest.push(stackNewest.pop());
            }
        }
    }

    public T peek() {
        shiftStacks();  // stackOldest에 현재 원소들이 들어있다
        return stackOldest.peek();  //  가장 오래된 원소를 반환
    }

    public T remove() {
        shiftStacks();  //  stackOldest에 현재 원소들이 들어있다
        return stackOldest.pop();   //  가장 오래된 원소를 제거
    }
}
