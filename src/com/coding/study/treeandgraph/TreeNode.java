package com.coding.study.treeandgraph;

import java.util.Random;

/*
  이진 탐색 트리 구현
 */

/**
 * 4.11
 * 임의의 노드 : 이진 트리 클래스를 바닥부터 구현하려고 한다.
 * 노드의 삽입， 검색， 삭제뿐만 아니라 임의의 노드를 반환하는 getRandomNode() 메서드도 구현한다.
 * 모든 노드를 같은 확률로 선택해주는 getRandomNode 메서드를 설계하고 구현하라.
 * 또한 나머지 메서드는 어떻게 구현할지 설명하라.
 */
public class TreeNode {
    public int data;
    public TreeNode left, right, parent;
    private int size = 0;

    public TreeNode(int d) {
        data = d;
        size = 1;
    }

    public TreeNode getRandomNode() {
        int leftSize = this.left == null ? 0 : this.left.size();
        Random random = new Random();
        int index = random.nextInt(this.size);
        if (index < leftSize) {
            return this.left.getRandomNode();
        } else if (index == leftSize) {
            return this;
        } else {
            return right.getRandomNode();
        }
    }

    public void insertInOrder(int d) {
        if (d <= data) {
            if (left == null) {
                setLeftChild(new TreeNode(d));
            } else {
                left.insertInOrder(d);
            }
        } else {
            if (right == null) {
                setRightChild(new TreeNode(d));
            } else {
                right.insertInOrder(d);
            }
        }
        size++;
    }

    public int size() {
        return this.size;
    }

    public int data() {
        return this.data;
    }

    public TreeNode find(int d) {
        if (d == data) {
            return this;
        } else if (d <= data) {
            return left != null ? left.find(d) : null;
        } else if (d > data) {
            return right != null ? right.find(d) : null;
        }
        return null;
    }

    public void setLeftChild(TreeNode left) {
        this.left = left;
        if (left != null) {
            left.parent = this;
        }
    }

    public void setRightChild(TreeNode right) {
        this.right = right;
        if (right != null) {
            right.parent = this;
        }
    }
}
