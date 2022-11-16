package com.cycas.algs.chapter1.section1;

import java.util.Arrays;

/**
 * @author NaXin
 * @since 2022-09-27
 */
public class Exercise13 {
    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6}};
        int[][] newArr = exchange(arr);
        System.out.println(Arrays.deepToString(newArr));
    }
    private static int[][] exchange(int[][] arr) {
        int[][] newArr = new int[arr[0].length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                newArr[j][i] = arr[i][j];
            }
        }
        return newArr;
    }
}
