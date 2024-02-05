package com.coding.study.stackandqueue.exam1;

import com.coding.study.stackandqueue.EmptyStackException;
import com.coding.study.stackandqueue.FullStackException;

/**
 * 고정 크기 할당
 * 스택 #1은 [0, n/3) 에 할당
 * 스택 #2는 [n/3, 2n/3) 에 할당
 * 스택 #3은 [2n/3, n) 에 할당
 */
public class FixedMultiStack {
    private final int numberOfStacks = 3;
    private final int stackCapacity;
    private final int[] values;
    private final int[] sizes;

    public FixedMultiStack(int stackSize) {
        stackCapacity = stackSize;
        values = new int[stackSize * numberOfStacks];
        sizes = new int[numberOfStacks];
    }

    // 스택에 값 추가
    public void push(int stackNum, int value) throws Exception {
        // 여유 공간이 있는지 체크
        if (isFull(stackNum)) {
            throw new FullStackException();
        }
        // 스택 포인터를 증가시키고 가장 꼭대기 값을 갱신
        sizes[stackNum]++;
        values[indexOfTop(stackNum)] = value;
    }

    public int pop(int stackNum) throws Exception {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }
        int topIndex = indexOfTop(stackNum);
        int value = values[topIndex];   // 가장 꼭대기 값을 꺼냄
        values[topIndex] = 0;   //  꼭대기 값을 지움
        sizes[stackNum]--;  // 스택의 크기를 줄임
        return value;
    }

    public int peek(int stackNum) throws Exception {
        if (isEmpty(stackNum)) {
            throw new EmptyStackException();
        }
        return values[indexOfTop(stackNum)];
    }

    // 스택이 꽉 차 있으면 true 반환
    public boolean isFull(int stackNum) {
        return sizes[stackNum] == stackCapacity;
    }

    //  스택이 비어 있으면 true를 반환
    public boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0;
    }

    // 스택의 꼭대기 값을 가리키는 인덱스를 반환
    private int indexOfTop(int stackNum) {
        int offset = stackNum * stackCapacity;
        int size = sizes[stackNum];
        return offset + size - 1;
    }

}
