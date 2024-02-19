package com.coding.study.treeandgraph;

/* 4.2
   최소 트리: 오름차순으로 정렬된 배열이 있다. 이 배열 안에 들어 있는 원소는 정수이며 중복된 값이 없다고 했을 때
   높이가 최소가 되는 이진 탐색 트리를 만드는 알고리즘을 작성하라.
 */
public class ex_4_2 {
    /*
      최소 높이 트리 생성
      => 왼쪽 하위 트리의 노드 개수 == 오른쪽 하위 트리의 노드 개수
      => 루트 노드가 배열의 중앙에 위치
      => 트리 원소 가운데 절반은 루트보다 작고 나머지 절반은 루트보다 커야 함
     */

    // createMinimalBST 메서드를 재귀적으로 사용.
    // 배열의 일부가 주어졌을 때, 해당 배열로 만들 수 있는 최소 높이 트리의 루트를 반환
    TreeNode createMinimalBST(int[] array) {
        return createMinimalBST(array, 0, array.length - 1);
    }

    TreeNode createMinimalBST(int[] arr, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode n = new TreeNode(arr[mid]);    // 배열 가운데 원소를 트리에 삽입
        n.left = createMinimalBST(arr, start, mid - 1); // 왼쪽 하위 트리에 왼쪽 절반 배열 원소들을 삽입하며 재귀호출
        n.right = createMinimalBST(arr, mid + 1, end);  // 오른쪽 하위 트리에 오른쪽 절반 배열 원소들을 삽입하며 재귀호출
        return n;
    }
}
