package com.cycas.algs.chapter1.section1;

import java.util.Scanner;

/**
 * @author NaXin
 * @since 2022-10-06
 */
public class Exercise21 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            String[] values = str.split(" ");
            format(values);
        }
    }

    private static void format(String[] values) {
        System.out.printf("%8s", values[0]);
        System.out.printf("%8s", values[1]);
        System.out.printf("%8s", values[2]);

        double value1 = Double.parseDouble(values[1]);
        double value2 = Double.parseDouble(values[2]);
        double result = value1 / value2;
        System.out.printf("%7.3f \n", result);


    }
}
