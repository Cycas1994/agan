package com.cycas.algs.chapter1.section1;

/**
 * @author NaXin
 * @since 2022-10-06
 */
public class Exercise24 {

    public static void main(String[] args) {
        int res = euclid(1234567, 1111111);
        System.out.println();
        System.out.println("res:" + res);
    }

    public static int euclid(int p, int q) {
        System.out.println();
        System.out.print("p:" + p + " - q:" + q);
        if (p % q == 0) {
            return q;
        }
        return euclid(q, p % q);
    }

}
