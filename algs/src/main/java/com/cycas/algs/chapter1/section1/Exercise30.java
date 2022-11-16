package com.cycas.algs.chapter1.section1;

/**
 * @author NaXin
 * @since 2022-10-06
 */
public class Exercise30 {

    public static void main(String[] args) {
        int n = 10;
        boolean[][] a = new boolean[n][n];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = isCoprime(i, j);
            }
        }
    }

    public static boolean isCoprime(int i, int j) {
        if (i == j && i != 1) {
            return false;
        }
        return gcd(i, j) == 1;
    }

    public static int gcd(int number1, int number2) {
        if (number2 == 0) {
            return number1;
        }
        return gcd(number2, number1 % number2);
    }
}
