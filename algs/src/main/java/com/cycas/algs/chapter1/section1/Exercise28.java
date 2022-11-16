package com.cycas.algs.chapter1.section1;

import java.util.Arrays;

/**
 * @author NaXin
 * @since 2022-10-06
 */
public class Exercise28 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 5, 6, 7, 7, 8};
        int[] newArr = removeDuplicates(arr);
        System.out.println(Arrays.toString(newArr));

    }

    public static int[] removeDuplicates(int[] arr) {
        int[] newArr = new int[arr.length];
        newArr[0] = arr[0];
        int idx = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != newArr[idx]) {
                idx++;
                newArr[idx] = arr[i];
            }
        }
        idx++;
        int[] compactNewWhitelist = new int[idx];
        System.arraycopy(newArr, 0, compactNewWhitelist, 0, idx);
        return compactNewWhitelist;
    }
}
