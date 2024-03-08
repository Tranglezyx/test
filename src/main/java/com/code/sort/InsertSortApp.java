package com.code.sort;

/**
 * 插入排序
 *
 * @author trangle
 */
public class InsertSortApp {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 5, 9, 4, 2, 6};
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                int tmp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = tmp;
            }
        }
        for (int i : arr) {
            System.out.printf(i + " ");
        }
    }
}
