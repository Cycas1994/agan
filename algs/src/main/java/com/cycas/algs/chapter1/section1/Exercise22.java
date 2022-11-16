package com.cycas.algs.chapter1.section1;

/**
 * @author NaXin
 * @since 2022-10-06
 */
public class Exercise22 {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        int idx = rank(2, a);
        System.out.println();
        System.out.println("idx:" + idx);
    }

    public static int rank(int key, int[] a) {
        return rank(key, a, 0, a.length - 1, 0);
    }

    public static int rank(int key, int[] a, int lo, int hi, int deep) {
        if (deep != 0) {
            System.out.println();
        }
        for (int i = 0; i < deep; i++) {
            System.out.print(" ");
        }
        System.out.print("lo: " + lo + " - hi:" + hi);

        if (lo > hi) {
            return -1;
        }
        int middle = lo + (hi - lo) / 2;
        if (a[middle] > key) {
            return rank(key, a, lo, middle - 1, ++deep);
        }
        if (a[middle] < key) {
            return rank(key, a, middle + 1, hi, ++deep);
        }
        return middle;
    }
}
