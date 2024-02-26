package com.coding.study.treeandgraph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 4.9
 * BST 수열 : 배열의 원소를 왼쪽에서부터 차례로 트리에 삽입함으로써 이진 탐색 트리를 생성할 수 있다.
 * 이진 탐색 트리 안에서 원소가 중복되지 않는다고 할 때, 해당 트리를 만들어 낼 수 있는 가능한 배열을 모두 출력하라.
 */
public class ex_4_9 {

    ArrayList<LinkedList<Integer>> allSequences(TreeNode node) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();

        if (node == null) {
            result.add(new LinkedList<>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(node.data);

        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);

        for (LinkedList left : leftSeq) {
            for (LinkedList right : rightSeq) {
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }
        return result;
    }

    void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
        if (first.size() == 0 || second.size() == 0) {
            LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
        } else {
            int headFirst = first.removeFirst();
            prefix.addLast(headFirst);
            weaveLists(first, second, results, prefix);
            prefix.removeLast();
            first.addFirst(headFirst);

            int headSecond = second.removeFirst();
            prefix.addLast(headSecond);
            weaveLists(first, second, results, prefix);
            prefix.removeLast();
            second.addFirst(headSecond);
        }
    }
}

