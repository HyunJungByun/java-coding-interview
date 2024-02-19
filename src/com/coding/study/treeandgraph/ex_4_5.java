package com.coding.study.treeandgraph;

/* 4.5
   BST 검증: 주어진 이진 트리가 이진 탐색 트리인지 확인하는 함수를 작성하라
 */
public class ex_4_5 {
    /*
      중위 순회(in-order traversal) - 왼쪽 가지(branch), 현재 노드, 오른쪽 가지 순서로 노드를 '방문'하고 출력하는 방법
                                   이진 탐색 트리를 이 방식으로 순회한다면 오름차순으로 방문하게 된다.
      트리에 중복된 값이 없다고 가정하고 중위 순회 이용
     */

    static Integer last_printed = null; // 값이 설정된 적이 있었는지 살펴보기 위해 int 대신 Integer 사용

    boolean checkBSTByIOT(TreeNode n) {
        if (n == null) {
            return true;
        }

        // 왼쪽을 재귀적으로 검사
        if (!checkBSTByIOT(n.left)) {
            return false;
        }

        // 현재 노드 검사
        if (last_printed != null && n.data <= last_printed) {
            return false;
        }
        last_printed = n.data;

        // 오른쪽을 재귀적으로 검사
        return checkBSTByIOT(n.right);// 검사 통과
    }

    /*
      이진 탐색 트리 - '모든 왼쪽 자식들 <= n < 모든 오른쪽 자식들' 속성은 모든 노드 n에 대해서 반드시 참이어야 한다.
      이진 탐색 트리는 모든 노드에 대해서 그 왼쪽 자식들의 값이 현재 노드 값보다 작거나 같도록 하고,
      그리고 오른쪽 자식들의 값은 현재 노드의 값보다 반드시 커야 한다.
     */
    /*
                  20
               10    30
            5     15
         3    7      17

        min = NULL, max = NULL 에서 시작. NULL 은 min 과 max 가 존재하지 않는다는 뜻
        루트 노드는 처음에 min, max 가 존재하지 않음.

        루트 노드의 왼쪽 노드들은 min = NULL, max = 20 의 범위 안에 있어야 함
        루트 노드의 오른쪽 노드들은 min = 20, max = NULL 의 범위 안에 있어야 함

        이 방법을 사용해 트리를 훑으면,
        왼쪽으로 분기하면 max를 갱신, 오른쪽으로 분기하면 min 을 갱신
        언제든 범위에 어긋나는 데이터를 발견하면 트리 순회를 중단하고 false를 반환

        시간 복잡도 O(N) N은 트리내의 노드의 개수.
        공간 복잡도는 재귀 호출을 수행하므로 균형 잡힌 트리에서 O(logN)
     */
    boolean checkBSTByMinMax(TreeNode n) {
        return checkBSTByMinMax(n, null, null);
    }

    boolean checkBSTByMinMax(TreeNode n, Integer min, Integer max) {
        if (n == null) {
            return true;
        }
        if ((min != null && n.data <= min) || (max != null && n.data > max)) {
            return false;
        }
        return checkBSTByMinMax(n.left, min, n.data) && checkBSTByMinMax(n.right, n.data, max);
    }

}
