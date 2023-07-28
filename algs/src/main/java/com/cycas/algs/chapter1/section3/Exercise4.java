package com.cycas.algs.chapter1.section3;

import edu.princeton.cs.algs4.StdOut;

public class Exercise4 {

    public static void main(String[] args) {
        Exercise4 exercise4 = new Exercise4();
        String input = "[()]";
        StdOut.println("Is Balanced " + input + ": " + exercise4.isBalanced(input)
                + " Expected: true");
        StdOut.println("Is Balanced [()]{}{[()()]()}: " + exercise4.isBalanced("[()]{}{[()()]()}")
                + " Expected: true");
        StdOut.println("Is balanced [(]): " + exercise4.isBalanced("[(])") + " Expected: false");
        StdOut.println("Is balanced [(): " + exercise4.isBalanced("[()") + " Expected: false");
    }

    private boolean isBalanced(String input) {
        char[] inputCharArr = input.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char inputChar : inputCharArr) {
            if (inputChar == '[' || inputChar == '(' || inputChar == '{') {
                stack.push(inputChar);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char item = stack.pop();
                if (inputChar == ']' && item != '['
                        || inputChar == ')' && item != '('
                        || inputChar == '}' && item != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

}
