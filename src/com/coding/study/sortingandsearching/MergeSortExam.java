package com.coding.study.sortingandsearching;

import java.util.Arrays;

public class MergeSortExam {

    public static int mergeCallCnt = 0;

    public static void mergesort(int[] array) {
        int[] helper = new int[array.length];
        mergesort(array, helper, 0, (array.length - 1));
    }

    // {96, 10, 3, 82, 68, 17, 69, 298, 57, 19, 9, 20, 58};
    // 0 ~ 6
    // 0 ~ 3
    // 0 ~ 1
    public static void mergesort(int[] array, int[] helper, int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;
            mergesort(array, helper, low, middle);  // 왼쪽 절반 정렬
            mergesort(array, helper, (middle + 1), high);     // 오른쪽 절반 정렬
            merge(array, helper, low, middle, high);
        }
    }

    public static void merge(int[] array, int[] helper, int low, int middle, int high) {
        System.out.println("low : " + low);
        System.out.println("high : " + high);
        System.out.println("middle : " + middle);

        mergeCallCnt++;

        // 절반짜리 두 배열을 helper 배열에 복사한다.
        if ((high + 1 - low) >= 0) {
            System.arraycopy(array, low, helper, low, (high + 1 - low));
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;
        System.out.println("helperLeft : " + helperLeft);
        System.out.println("helperRight : " + helperRight);
        System.out.println("current : " + current);

        // helper 배열 순회. 왼쪽 절반과 오른쪽 절반을 비교하여 '작은 원소'를 원래 배열에 복사해 넣는다.
        while ((helperLeft <= middle) && (helperRight <= high)) {

            System.out.println("helper[" + helperLeft + "]=" + helper[helperLeft]);
            System.out.println("helper[" + helperRight + "]=" + helper[helperRight]);

            if (helper[helperLeft] <= helper[helperRight]) {
                array[current] = helper[helperLeft];
                helperLeft++;
            } else {    // 오른쪽 원소가 왼쪽 원소보다 작으면
                array[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }
        // 왼쪽 절반 배열에 남은 원소들을 원래 배열에 복사해 넣는다.
        int remaining = middle - helperLeft;
        System.out.println("remaining : " + remaining);
        if ((remaining + 1) >= 0) {
            System.arraycopy(helper, helperLeft, array, current, (remaining + 1));
        }

        System.out.println(mergeCallCnt + " helper : " + Arrays.toString(helper));
        System.out.println(mergeCallCnt + " array : " + Arrays.toString(array));
        System.out.println("--------");
    }

    public static void main(String[] args) {
        int[] testArr = {96, 10, 3, 82, 68, 17, 69, 298, 57, 19, 9, 20, 58};
        System.out.println("before testArr : " + Arrays.toString(testArr));
        mergesort(testArr);
        System.out.println("after testArr : " + Arrays.toString(testArr));
    }
}
