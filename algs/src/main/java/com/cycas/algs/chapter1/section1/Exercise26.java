package com.cycas.algs.chapter1.section1;

/**
 * @author NaXin
 * @since 2022-10-06
 */
public class Exercise26 {

    public static void main(String[] args) {
        int N = 100;
        int k = 50;
        double[][] arr = new double[N][k];
        arr[0][0] = 1;
        for (int i = 1; i < k; i++) {
            arr[0][i] = 0;
        }
        arr[1][0] = 1;
        binomialArr(arr, N, k, 0.25, 0);
    }

    public static void binomialArr(double[][]  arr, int N, int k, double p, int deep) {
        System.out.println("deep:" + deep);
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < k; j++) {
                arr[i][j] = (1.0 - p) * arr[i - 1][j] + p * arr[i - 1][j - 1];
            }
        }
    }
}
