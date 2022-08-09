package com.cycas.algs;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;

/**
 * @author NaXin
 * @since 2022-08-07
 */
public class Test {
    private static Scanner scanner;
    public static void main(String[] args) {

        boolean[][] arr = {{true, true, true}, {false, false, false}, {true, true, true}};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" ");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print((j + 1) + " ");
            }
            break;
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i+1 + ":");
            boolean[] arr2 = arr[i];
            for (int j = 0; j < arr2.length; j++) {
                String str = "";
                if (arr[i][j]) {
                    str = "* ";
                } else {
                    str = " ";
                }
                System.out.print(str);
            }
            System.out.println();
        }
    }
    // 1
    // 2
}
