package com.coding.study.stackandqueue.exam5;

import java.util.Stack;

public class Exam5 {
    void sort(Stack s) {
        Stack r = new Stack();
        while (!s.isEmpty()) {
            // s의 원소를 정렬된 순서로 r에 삽입
            int temp = s.pop();
            while (!r.isEmpty() && r.peek() > temp) {
                s.push(r.pop());
            }
            r.push(temp);
        }

        // r에서 s로 원소를 다시 옮겨준다.
        while (!r.isEmpty()) {
            s.push(r.pop());
        }
    }
}
