package com.cycas.algs.chapter1.section1;

import java.util.Arrays;

/**
 * @author NaXin
 * @since 2022-09-27
 */
public class Exercise15 {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 9};
        int[] newArr = histogram(a, 7);
        System.out.println(Arrays.toString(newArr));
    }

    private static int[] histogram(int[] a, int m) {
        int[] newArr = new int[m];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = cnt(a, i);
        }
        return newArr;
    }

    private static int cnt(int[] a, int c) {
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == c) {
                j++;
            }
        }
        return j;
    }
}
