package com.coding.study.treeandgraph;

import java.util.ArrayList;
import java.util.LinkedList;

/* 4.3
   깊이의 리스트: 이진 트리가 주어졌을 때 같은 깊이에 있는 노드를 연결리스트로 연결해 주는 알고리즘을 설계하라.
   즉, 트리의 깊이가 D라면 D개의 연결리스트를 만들어야 한다.

 */
public class ex_4_3 {
    /*
      전위 순회(pre order traversal) - 자식 노드보다 현재 노드를 먼저 방문하는 방법
                                    전위 순회에서 가장 먼저 방문하게 될 노드는 언제나 루트

     */

    // 깊이 우선 탐색 기법(DFS) 사용
    void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level) {
        if (root == null) {
            return; // 초기 사례
        }

        LinkedList<TreeNode> list = null;
        if (lists.size() == level) {    // 리스트에 해당 레벨이 없다.
            list = new LinkedList();
            /*
                    1
                 2      3
              4    5  6   7

              깊이가 증가하는 순서로 순회했다는 사실에 유의하자.
              따라서 깊이 #i를 처음 마주쳤다면, 0부터 i-1번째까지는 이전에 이미 lists에 추가되어야 한다.
              따라서 새로운 깊이 #i를 lists의 끝에 추가해도 안전하다.
             */
            lists.add(list);
        } else {
            list = lists.get(level);
        }
        list.add(root);
        createLevelLinkedList(root.left, lists, level + 1);
        createLevelLinkedList(root.right, lists, level + 1);
    }

    ArrayList<LinkedList<TreeNode>> createLevelLinkedListByDFS(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> lists = new ArrayList<>();
        createLevelLinkedList(root, lists, 0);
        return lists;
    }

    // 너비 우선 탐색 기법(BFS) 사용
    ArrayList<LinkedList<TreeNode>> createLevelLinkedListByBFS(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> result = new ArrayList<>();
        // 루트 '방문'
        LinkedList<TreeNode> current = new LinkedList();
        if (root != null) {
            current.add(root);
        }

        while (current.size() > 0) {
            result.add(current);    // 이전 깊이 추가
            LinkedList<TreeNode> parents = current;   //  다음 깊이로 진행
            current = new LinkedList();
            for (TreeNode parent : parents) {
                // 자식 노드들 방문
                if (parent.left != null) {
                    current.add(parent.left);
                }
                if (parent.right != null) {
                    current.add(parent.right);
                }
            }
        }
        return result;
    }


}
