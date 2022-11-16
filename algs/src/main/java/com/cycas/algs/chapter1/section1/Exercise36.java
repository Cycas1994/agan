package com.cycas.algs.chapter1.section1;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Random;

/**
 * @author NaXin
 * @since 2022-10-06
 */
public class Exercise36 {

    public static void main(String[] args) {
        int m = 5;
        int n = 2000;
        double[] array = new double[m];
        int[][] positions = new int[m][m];

        for (int i = 0; i < n; i++) {
            initializeArray(array);
            shuffle(array);
            for (int j = 0; j < array.length; j++) {
                positions[(int)array[j]][j]++;
            }
        }
        printTable(positions);
    }

    public static void initializeArray(double[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    public static void shuffle(double[] array) {
        int n = array.length;
        for (int i = 0; i < array.length; i++) {
            int randomIdx = i + StdRandom.uniform(n - i);
//            int randomIdx = StdRandom.uniform(n - i);
            double temp = array[i];
            array[i] = array[randomIdx];
            array[randomIdx] = temp;
        }
    }

    public static void printTable(int[][] positions) {
        System.out.println("Table");
        for (int i = 0; i < positions.length; i++) {
            System.out.printf("%3d  ", i);
            for (int j = 0; j < positions[i].length; j++) {
                System.out.printf("%4d ", positions[i][j]);
            }
            System.out.println();
        }

    }
}
