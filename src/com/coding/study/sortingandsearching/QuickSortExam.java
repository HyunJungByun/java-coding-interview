package com.coding.study.sortingandsearching;

import java.util.Arrays;

public class QuickSortExam {

    public static void quickSort(int[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (left < (index - 1)) {   // 왼쪽 절반 정렬
            quickSort(arr, left, (index - 1));
        }
        if (index < right) {
            quickSort(arr, index, right);   // 오른쪽 절반 정렬
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];    // 분할 기준 원소 선정
        while (left <= right) {
            // 왼쪽에서 오른쪽으로 옮겨야 하는 원소 탐색
            while (arr[left] < pivot) {
                left++;
            }
            // 오른쪽에서 왼쪽으로 옮겨야 하는 원소 탐색
            while (arr[right] > pivot) {
                right--;
            }
            // 원소를 스왑한 뒤 left와 right를 이동
            if (left <= right) {
                swap(arr, left, right); // 스왑
                left++;
                right--;
            }
        }
        return left;
    }

    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static void main(String[] args) {
        int[] testArr = {96, 10, 3, 82, 68, 17, 69, 298, 57, 19, 9, 20, 58};
        System.out.println("before testArr : " + Arrays.toString(testArr));
        quickSort(testArr, 0, testArr.length - 1);
        System.out.println("after testArr : " + Arrays.toString(testArr));
    }

}
