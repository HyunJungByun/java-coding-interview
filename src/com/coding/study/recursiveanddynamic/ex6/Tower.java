package com.coding.study.recursiveanddynamic.ex6;


import java.util.Stack;

/**
 * 8.6 하노이타워
 * 전형적인 하노이타워(Towers of Hanoi)에서는 크기가 서로 다른 N개의 원반을 세 개의 기둥 중 아무 곳으로나 옮길 수 있다.
 * 초기에 원반은 크기가 맨 위에서부터 아래로 커지는 순서대로 쌓여 있다.
 * 그리고 이 문제에는 다음과 같은 제약조건이 있다.
 * <p>
 * (1) 원반을 한 번에 하나만 옮길 수 있따.
 * (2) 맨 위에 있는 원반 하나를 다른 기둥으로 옮길 수 있다.
 * (3) 원반은 자신보다 크기가 작은 디스크 위에 놓을 수 없다.
 * <p>
 * 스택을 사용하여 모든 원반을 첫 번째 기둥에서 마지막 기둥으로 옮기는 프로그램을 작성하라.
 */
public class Tower {
    public String name;
    private final Stack<Integer> disks = new Stack<>();

    public void add(int d) {
        if (!disks.isEmpty() && disks.peek() <= d) {
            System.out.println("Error placing disk " + d);
        } else {
            disks.push(d);
        }
    }

    public void moveTopTo(Tower t) {
        int top = disks.pop();
        t.add(top);
    }

    public void print() {
        System.out.println("Contents of Tower " + name + ": " + disks.toString());
    }

    public void moveDisks(int quantity, Tower destination, Tower buffer) {
        // 초기 사례
        if (quantity <= 0) return;

        // destination 을 버퍼로 사용해서 맨 위 n-1개의 원판을 origin 에서 buffer 로 옮긴다.
        moveDisks(quantity - 1, buffer, destination);
        System.out.println("Move " + disks.peek() + " from " + this.name + " to " + destination.name);

        // 가장 윗 원판을 origin에서 destination으로 옮긴다.
        moveTopTo(destination);

        // origin을 버퍼로 사용해서 맨 위 n-1개의 원판을 buffer에서 destination으로 옮긴다.
        buffer.moveDisks(quantity - 1, destination, this);
    }
}
