package com.coding.study.treeandgraph;

/**
 * 4.12
 * 합의 경로 : 각 노드의 이 정수(음수 및 양수)인 이진 트리가 있다.
 * 이때 정수의 합이 특정 값이 되도록 하는 경로의 개수를 세려고 한다.
 * 경로가 꼭 루트에서 시작해서 말단 노드에서 끝날 필요는 없지만 반드시 아래로 내려가야한다.
 * 즉，부모노드에서 자식 노드로만 움직일 수 있다. 알고리즘을 어떻게 설계할 것인가?
 */
public class ex_4_12 {

    int countPathWithSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);

        int pathsOnLeft = countPathWithSum(root.left, targetSum);
        int pathsOnRight = countPathWithSum(root.right, targetSum);

        return pathsFromRoot + pathsOnLeft + pathsOnRight;
    }

    int countPathsWithSumFromNode(TreeNode node, int targetSum, int currentSum) {
        if (node == null) {
            return 0;
        }

        currentSum += node.data;

        int totalPaths = 0;
        if (currentSum == targetSum) {
            totalPaths++;
        }

        totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum);
        totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum);
        return totalPaths;
    }
}
