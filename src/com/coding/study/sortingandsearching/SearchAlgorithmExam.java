package com.coding.study.sortingandsearching;

public class SearchAlgorithmExam {

    public static int binarySearch(int[] a, int x) {
        int low = 0;
        int high = a.length - 1;
        int mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (a[mid] < x) {
                low = mid + 1;
            } else if (a[mid] > x) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;    // 에러
    }

    public static int binarySearchRecursive(int[] a, int x, int low, int high) {
        if (low > high) {
            return -1;    // 에러
        }

        int mid = (low + high) / 2;
        if (a[mid] < x) {
            return binarySearchRecursive(a, x, (mid + 1), high);
        } else if (a[mid] > x) {
            return binarySearchRecursive(a, x, low, (mid - 1));
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        int[] testSortedArr = {3, 9, 10, 17, 19, 20, 57, 58, 68, 69, 82, 96, 298};
        int questValue = 96;
        int answer1 = binarySearch(testSortedArr, questValue);
        System.out.println("sortedArr " + questValue + " binarySearch index : " + answer1);
        int answer2 = binarySearchRecursive(testSortedArr, questValue, 0, (testSortedArr.length - 1));
        System.out.println("sortedArr " + questValue + " binarySearchRecursive index : " + answer2);

        System.out.println("-----------------");

        int[] testUnsortedArr = {96, 10, 3, 82, 68, 17, 69, 298, 57, 19, 9, 20, 58};
        int answer3 = binarySearch(testUnsortedArr, questValue);
        System.out.println("unsortedArr " + questValue + " binarySearch index : " + answer3);
        int answer4 = binarySearchRecursive(testUnsortedArr, questValue, 0, (testSortedArr.length - 1));
        System.out.println("unsortedArr " + questValue + " binarySearchRecursive index : " + answer4);
    }

}