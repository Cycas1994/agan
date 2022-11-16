package com.cycas.algs.chapter1.section2;

/**
 * @author NaXin
 * @since 2022-11-13
 */
public class Exercise9 {

    private static int rank(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid]) {
                hi = mid - 1;
            } else if (key > a[mid]) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

}
