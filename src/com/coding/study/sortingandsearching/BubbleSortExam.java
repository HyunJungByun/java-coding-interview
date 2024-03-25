package com.coding.study.sortingandsearching;

import java.util.Arrays;

public class BubbleSortExam {
    public static void bubbleSort(int[] a) {
        bubbleSort(a, a.length);
    }

    public static void bubbleSort(int[] a, int size) {

        // round는 배열 크기 - 1 만큼 진행
        for (int i = 1; i < size; i++) {

            // 각 라운드별 비교횟수는 배열 크기의 현재 라운드를 뺀 만큼 비교
            for (int j = 0; j < size - i; j++) {

                /*
                 *  현재 원소가 다음 원소보다 클 경우
                 *  서로 원소의 위치를 교환
                 */
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                }
            }
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
        bubbleSort(testArr);
        System.out.println("after testArr : " + Arrays.toString(testArr));
    }
}

