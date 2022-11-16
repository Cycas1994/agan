package com.cycas.algs.chapter1.section1;

/**
 * @author NaXin
 * @since 2022-10-06
 */
public class Exercise38 {

    public static int bruteForseSearch(int key, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (key == arr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int key, int[] arr, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }
        int middle = lo + (hi - lo) / 2;
        if (arr[middle] > key) {
            return binarySearch(key, arr, 0, middle - 1);
        } else if (arr[middle] < key) {
            return binarySearch(key, arr, middle + 1, hi);
        }
        return middle;
    }

}
