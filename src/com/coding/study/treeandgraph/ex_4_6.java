package com.coding.study.treeandgraph;

/* 4.6
   후속자: 이진 탐색 트리에서 주어진 노드의 '다음' 노드(중위 후속자(in-order successor))를 찾는 알고리즘을 작성하라.
   각 노드에는 부모 노드를 가리키는 링크가 존재한다고 가정하자.
 */
public class ex_4_6 {
    /*
      이진 탐색 트리 - '모든 왼쪽 자식들 <= n < 모든 오른쪽 자식들' 속성은 모든 노드 n에 대해서 반드시 참이어야 한다.
      이진 탐색 트리는 모든 노드에 대해서 그 왼쪽 자식들의 값이 현재 노드 값보다 작거나 같도록 하고,
      그리고 오른쪽 자식들의 값은 현재 노드의 값보다 반드시 커야 한다.

      중위 순회(in-order traversal) - 왼쪽 가지(branch), 현재 노드, 오른쪽 가지 순서로 노드를 '방문'하고 출력하는 방법
                                   이진 탐색 트리를 이 방식으로 순회한다면 오름차순으로 방문하게 된다.
     */

    TreeNode inorderSucc(TreeNode n) {
        if (n == null) {
            return null;
        }

        // 오른쪽 자식이 존재 => 오른쪽 부분 트리에서 가장 왼쪽 노드를 반환
        if (n.right != null) {
            return leftMostChild(n.right);
        } else {
            TreeNode q = n;
            TreeNode x = q.parent;
            // 오른쪽이 아닌 왼쪽에 있을 때 까지 위로 올라간다
            while (x != null && x.left != q) {
                q = x;
                x = x.parent;
            }
            return x;
        }
    }

    TreeNode leftMostChild(TreeNode n) {
        if (n == null) {
            return null;
        }
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }
}
