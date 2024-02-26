package com.coding.study.treeandgraph;

/* 4.4
   균형 확인: 이진 트리가 균형 잡혀있는지 확인하는 함수를 작성하라.
   이 문제에서 균형 잡힌 트리란 모든 노드에 대해서 왼쪽 부분 트리의 높이와 오른쪽 부분 트리의 높이의 차이가 최대 하나인 트리를 의미한다.

                        1
                    2       3
                 4    5   6    7
              8    9

 */
public class ex_4_4 {

    // 전체 트리를 재귀적으로 순회하면서 각 노드에 대해 하위 트리의 높이를 계산하는 방법
    int getHeight(TreeNode root) {
        if (root == null) {
            return -1;  // 초기 사례
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    boolean isBalancedCase1(TreeNode root) {
        if (root == null) {
            return true;    //  초기 사례
        }

        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(heightDiff) > 1) { // 왼쪽 부분 트리의 높이와 오른쪽 부분 트리의 높이의 차이가 1보다 클경우 균형잡힌 트리가 아님
            return false;
        } else {    // 재귀
            return isBalancedCase1(root.left) && isBalancedCase1(root.right);
        }
    }

    /*
      위의 해법은 그다지 효율적이지 않음.
      각 노드에서 전체 하위 트리를 재귀적으로 탐색, 같은 노드에 대해 getHeight가 반복적으로 호출
      위의 알고리즘은 각 노드는 그보다 위쪽에 있는 노드들을 전부 한번씩 건드리기 때문에 공간 복잡도는 O(NlogN)
      => getHeight 호출하는 횟수를 줄일 필요가 있음.

      getHeight가 높이를 검사하는 동시에 트리가 균형 잡혀 있는지도 검사 => 하위 트리가 균형 잡혀 있지 않다면 그냥 에러를 반환하도록 하여 알고리즘 개선
     */

    // 위의 해법과 차이점은 하위 트리가 균형 잡힌 상태일 경우 해당 하위 트리의 실제 높이를 반환하고,
    // 그렇지 않은 경우에는 에러를 반환. 에러가 반환된 경우 즉시 실행 중단
    // Integer.MIN_VALUE 를 에러로 사용
    // 시간 복잡도 O(N), 공간 복잡도 O(H) H는 트리의 높이를 나타냄
    int checkHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }

        int leftHeight = checkHeight(root.left);
        if (leftHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;   // 균형 잡혀 있지 않은 경우로 에러 반환
        }

        int rightHeight = checkHeight(root.right);
        if (rightHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;   // 균형 잡혀 있지 않은 경우로 에러 반환
        }

        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            // 왼쪽 부분 트리의 높이와 오른쪽 부분 트리의 높이의 차이가 1보다 큰 경우는 균형 잡힌 트리가 아니므로 에러 반환
            return Integer.MIN_VALUE;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    boolean isBalancedByCase2(TreeNode root) {
        return checkHeight(root) != Integer.MIN_VALUE;
    }
}
