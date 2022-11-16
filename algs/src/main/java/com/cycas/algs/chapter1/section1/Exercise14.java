package com.cycas.algs.chapter1.section1;

import edu.princeton.cs.algs4.StdOut;

/**
 * @author NaXin
 * @since 2022-09-27
 */
public class Exercise14 {
    public static void main(String[] args) {
        StdOut.print(lg(1));
        StdOut.print(lg(2));
        StdOut.print(lg(3));
        StdOut.print(lg(4));
    }

    private static int lg(int n) {

        int logInt = 0;

        while (n > 0) {
            logInt++;

            n /= 2;
        }

        return logInt - 1;
    }
}
