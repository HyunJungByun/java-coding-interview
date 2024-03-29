package com.coding.study.stackandqueue.exam1;

import com.coding.study.stackandqueue.EmptyStackException;
import com.coding.study.stackandqueue.FullStackException;

public class MultiStack {
    private final StackInfo[] info;
    private final int[] values;

    public MultiStack(int numberOfStacks, int defaultSize) {
        // 모든 스택에 대한 메타데이터를 만든다.
        info = new StackInfo[numberOfStacks];
        for (int i = 0; i < numberOfStacks; i++) {
            info[i] = new StackInfo(defaultSize * i, defaultSize);
        }
        values = new int[numberOfStacks * defaultSize];
    }

    //  stackNum 위치에 값을 넣어주고, 필요에 따라 스택을 움직이거나 늘려줌
    //  모든 스택이 꽉 찬 경우를 대비해서 예외 처리
    public void push(int stackNum, int value) throws Exception {
        if (allStacksAreFull()) {
            throw new FullStackException();
        }
        //  스택이 꽉 찼다면 크기를 늘려준다
        StackInfo stack = info[stackNum];
        if (stack.isFull()) {
            expand(stackNum);
        }
        //  크기를 하나 증가시킨 스택의 마지막 원소의 인덱스를 찾은 뒤 스택 포인터를 늘림
        stack.size++;
        values[stack.lastElementIndex()] = value;
    }

    //  스택에서 값 하나를 제거
    public int pop(int stackNum) throws Exception {
        StackInfo stack = info[stackNum];
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        //  마지막 원소를 제거
        int value = values[stack.lastElementIndex()];
        values[stack.lastElementIndex()] = 0;   //  마지막 원소를 삭제
        stack.size--;
        return value;
    }

    //  스택의 꼭대기 값을 구한다
    public int peek(int stackNum) {
        StackInfo stack = info[stackNum];
        return values[stack.lastElementIndex()];
    }

    /*
     * 스택의 원소들을 하나 옮김
     * 만약 스택에 여유 공간이 있다면 스택의 크기를 원소 하나 줄여준다.
     * 여유 공간이 없다면 그 다음에 위치한 스택 또한 옮겨야 함
     */
    private void shift(int stackNum) {
        System.out.println("/// Shifting " + stackNum);
        StackInfo stack = info[stackNum];
        // 스택이 꽉 차 있다면, 그 다음에 위치한 스택을 원소 하나 크기만큼 옮겨야 함. 그래야 현재 스택에 여유 공간이 생김
        if (stack.size >= stack.capacity) {
            int nextStack = (stackNum + 1) % info.length;
            shift(nextStack);
            stack.capacity++;   //  다음 스택에서 얻어온 가용 공간
        }
        //  스택의 모든 원소를 하나씩 옮김
        int index = stack.lastCapacityIndex();
        while (stack.isWithinStackCapacity(index)) {
            values[index] = values[previousIndex(index)];
            index = previousIndex(index);
        }
        //  스택의 데이터를 변경
        values[stack.start] = 0;    //  원소를 삭제
        stack.start = nextIndex(stack.start);   //  시작 지점을 옮김
        stack.capacity--;   //  용량을 줄임
    }

    //  인덱스를 0에서 length - 1 범위 안에 있도록 조절
    private int adjustIndex(int index) {
        /* 자바의 mod 연산자는 음수값을 반환할 수도 있다.
        예를 들어, (-11 % 5)는 4가 아닌 -1을 반환한다.
        하지만 우리가 실제로 원하는 값은 4일 것이다(인덱스가 배열 범위 안에서 순환하기 때문에)*/
        int max = values.length;
        return ((index % max) + max) % max;
    }

    // (배열의 순환을 적용) 현재 위치의 다음 인덱스를 반환
    private int nextIndex(int index) {
        return adjustIndex(index + 1);
    }

    //  (배열의 순환을 적용) 현재 위치의 이전 인덱스를 반환
    private int previousIndex(int index) {
        return adjustIndex(index - 1);
    }


    //  다른 스택으로 옮김으로써 스택의 크기를 늘려준다
    private void expand(int stackNum) {
        shift((stackNum + 1) % info.length);
        info[stackNum].capacity++;
    }

    //  현재 스택에 존재하는 원소의 개수를 반환
    public int numberOfElements() {
        int size = 0;
        for (StackInfo stackInfo : info) {
            size += stackInfo.size;
        }
        return size;
    }

    //  모든 스택이 꽉 차있다면 true를 반환
    public boolean allStacksAreFull() {
        return numberOfElements() == values.length;
    }

    /*
     * StackInfo는 각 스택에 대한 자료를 들고 있는 간단한 클래스
     * 스택의 실제 아이템을 들고 있지는 않는다.
     * 개별적인 변수 다발을 사용해서 실제 아이템을 들고 있게 할 수도 있지만,
     * 이 방법은 굉장히 지저분하고 실제로 이렇게 해서 얻을 수 있는 게 많지 않다.
     */
    private class StackInfo {
        public int start, size, capacity;

        public StackInfo(int start, int capacity) {
            this.start = start;
            this.capacity = capacity;
        }

        // 주어진 배열 내의 인덱스가 스택의 구역 내에 있는지 확인
        // 스택은 순환해서 배열의 시작 지점으로 옮겨갈 수도 있다.
        public boolean isWithinStackCapacity(int index) {
            // 배열의 범위를 벗어나면 false를 반환
            if (index < 0 || index >= values.length) {
                return false;
            }
            // 인덱스가 배열을 순환해야 한다면 그 값을 알맞게 고쳐줌
            int contiguousIndex = (index < start) ? (index + values.length) : index;
            int end = start + capacity;
            return (start <= contiguousIndex) && (contiguousIndex < end);
        }

        public int lastCapacityIndex() {
            return adjustIndex(start + capacity - 1);
        }

        public int lastElementIndex() {
            return adjustIndex(start + size - 1);
        }

        public boolean isFull() {
            return size == capacity;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }
}
