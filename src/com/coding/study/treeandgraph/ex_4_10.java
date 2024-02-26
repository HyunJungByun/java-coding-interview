package com.coding.study.treeandgraph;

/**
 * 4.10
 * 하위 트리 확인: 두 개의 커다란 이진 트리 T1과 T2가 있다고 하자.T1이 T2보다 훨씬 크다고 했을 때，
 * T2가 T1의 하위 트리 (subtree)인지 판별하는 알고리즘을 만들라.
 * T1 안에 있는 노드 n의 하위 트리가 T2와 동일하면， T2는 T1의 하위 트리다.
 * 다시 말해 , T1 에서 노드 n의 아래쪽을 끊어 냈을 때 그 결과가 T2와 동일해야 한다.
 */
public class ex_4_10 {

    boolean containsTree(TreeNode t1, TreeNode t2) {
        StringBuilder string1 = new StringBuilder();
        StringBuilder string2 = new StringBuilder();

        getOrderString(t1, string1);
        getOrderString(t2, string2);

        return string1.indexOf(string2.toString()) != -1;
    }

    void getOrderString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("X");
        } else {
            sb.append(node.data + " ");
            getOrderString(node.left, sb);
            getOrderString(node.right, sb);
        }
    }
}
