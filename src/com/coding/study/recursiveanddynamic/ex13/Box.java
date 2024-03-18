package com.coding.study.recursiveanddynamic.ex13;

/**
 * 8.13 박스 쌓기
 * 너비 wi, 높이 hi, 깊이 di인 박스 n개가 있다.
 * 상자는 회전시킬 수 없으며, 다른 상자 위에 올려 놓을 수 있다.
 * 단, 아래 높인 상자의 너비, 높이, 깊이가 위에 놓인 상자의 너비, 높이, 깊이보다 더 클 때에만 가능하다.
 * 이 상자들로 쌓을 수 있는 가장 높은 탑을 구하는 메서드를 작성하라.
 * 탑의 높이는 탑을 구성하는 모든 상자 높이의 합이다.
 */
public class Box {
    public int width;
    public int height;
    public int depth;

    public Box(int w, int h, int d) {
        width = w;
        height = h;
        depth = d;
    }

    public boolean canBeUnder(Box b) {
        return width > b.width && height > b.height && depth > b.depth;
    }

    public boolean canBeAbove(Box b) {
        if (b == null) {
            return true;
        }
        return width < b.width && height < b.height && depth < b.depth;
    }

    public String toString() {
        return "Box(" + width + "," + height + "," + depth + ")";
    }
}
