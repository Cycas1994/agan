package com.cycas.algs.chapter1.section1;

/**
 * @author NaXin
 * @since 2022-10-06
 */
public class Exercise29 {

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 5, 6, 7, 7, 8};
        int c1 = rank(5, a);
        int c2 = count(5, a);
        for (int i = c1; i <= c1 + c2 - 1; i++) {
            System.out.println(a[i]);
        }
    }

    public static int rank(int key, int[] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < key) {
                count++;
            }
        }
        return count;
    }

    public static int count(int key, int[] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == key) {
                count++;
            }
        }
        return count;
    }
}
