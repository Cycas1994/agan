package com.cycas.algs.chapter1.section1;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

/**
 * @author NaXin
 * @since 2022-10-06
 */
public class Exercise39 {

    public static void main(String[] args) {
        int n1 = 1000;
        int n2 = 10000;
        int n3 = 100000;
        int n4 = 1000000;

        int t = 10;
        int[] res = new int[4];
        for (int i = 0; i < t; i++) {
            res[0] += experiment(n1);
            res[1] += experiment(n2);
            res[2] += experiment(n3);
            res[3] += experiment(n4);
        }

        System.out.printf("%10s %37s \n", "Array size", "AVG number of values in both arrays");
        System.out.printf("10^3 %17.2f \n", ((double) res[0]) / t);
        System.out.printf("10^4 %17.2f \n", ((double) res[1]) / t);
        System.out.printf("10^5 %17.2f \n", ((double) res[2]) / t);
        System.out.printf("10^6 %17.2f \n", ((double) res[3]) / t);
    }

    private static int experiment(int size) {
        int[] a1 = new int[size];
        initializeArray(a1);
        int[] a2 = new int[size];
        initializeArray(a2);

        Arrays.sort(a1);
        Arrays.sort(a2);
        int count = 0;
        for (int i = 0; i < a1.length; i++) {
            if (binarySearch(a1[i], a2, 0, a2.length - 1)) {
                count++;
            }
        }
        return count;
    }

    public static void initializeArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = StdRandom.uniform(100000, 1000000);
        }
    }

    public static boolean binarySearch(int key, int[] a, int lo, int hi) {
        if (lo > hi) {
            return false;
        }
        int middle = lo + (hi - lo) / 2;
        if (key < a[middle]) {
            return binarySearch(key, a, 0, middle - 1);
        } else if (key > a[middle]) {
            return binarySearch(key, a, middle + 1, hi);
        }
        return true;
    }
}
