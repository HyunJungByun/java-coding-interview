package com.coding.study.sortingandsearching;

import java.util.Arrays;

public class SelectionSortExam {
    public static void selectionSort(int[] a) {
        selectionSort(a, a.length);
    }

    public static void selectionSort(int[] a, int size) {

        for (int i = 0; i < size - 1; i++) {
            int min_index = i;

            // 최솟값을 갖고있는 인덱스 찾기
            for (int j = i + 1; j < size; j++) {
                if (a[j] < a[min_index]) {
                    min_index = j;
                }
            }

            // i번째 값과 찾은 최솟값을 서로 교환
            swap(a, min_index, i);
        }
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] testArr = {96, 10, 3, 82, 68, 17, 69, 298, 57, 19, 9, 20, 58};
        System.out.println("before testArr : " + Arrays.toString(testArr));
        selectionSort(testArr);
        System.out.println("after testArr : " + Arrays.toString(testArr));
    }
}
