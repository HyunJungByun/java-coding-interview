package com.coding.study.sortingandsearching;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RadixSortExam {
    // 10진수 기준으로 구현
    private static final int BUCKET_NUM = 10;

    public static void radixSort(int[] arr) {
        // 10진수 버킷 생성
        Queue<Integer>[] bucket = new LinkedList[BUCKET_NUM];
        for (int i = 0; i < BUCKET_NUM; i++) {
            bucket[i] = new LinkedList<>();
        }

        int maxLen = maxDigitCount(arr);
        // 각 자리수의 숫자 저장
        int digitNumber = 0;
        // 배열에 다시 저장할 때 필요한 변수
        int arrIndex = 0;

        // 자리수만큼 반복
        for (int i = 0; i < maxLen; i++) {
            // 데이터의 개수만큼 반복
            for (int j = 0; j < arr.length; j++) {
                digitNumber = getDigit(arr[j], i);

                bucket[digitNumber].add(arr[j]);
            }

            // 버킷에 들어간 데이터를 순서대로 꺼내 배열에 덮어씌움
            for (int j = 0; j < BUCKET_NUM; j++) {
                while (!bucket[j].isEmpty()) {
                    arr[arrIndex++] = bucket[j].remove();
                }
            }
            arrIndex = 0;
        }
    }

    // 숫자의 자리수 반환
    // getDigit(123, 0) => 3
    // getDigit(123, 1) => 2
    // getDigit(123, 2) => 1
    private static int getDigit(int num, int index) {
        return (int) Math.floor(Math.abs(num) / Math.pow(10, index)) % 10;
    }

    // 숫자의 자리수 구하기
    // digitCount(10) => 2
    // digitCount(1) => 1
    // digitCount(1000) => 4
    private static int digitCount(int num) {
        if (num == 0) {
            return 1;
        }

        // log10을 하면 자리수가 나옴
        // log10(10) => 1
        // log10(100) -> log10(10^2) => 2
        return (int) Math.floor(Math.log10(Math.abs(num))) + 1;
    }

    // 데이터들 중 가장 큰 자리수 반환
    private static int maxDigitCount(int[] arr) {
        int max = 0;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, digitCount(arr[i]));
        }

        return max;
    }

    public static void main(String[] args) {
        int[] testArr = {96, 10, 3, 82, 68, 17, 69, 298, 57, 19, 9, 20, 58};
        System.out.println("before testArr : " + Arrays.toString(testArr));
        radixSort(testArr);
        System.out.println("after testArr : " + Arrays.toString(testArr));
    }
}
