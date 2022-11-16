package com.cycas.algs.chapter1.section3;

public class Exercise1 { // FixedCapacityStackOfStrings

    private String[] elementData;
    private int size;

    public Exercise1(int capacity) {
        elementData = new String[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(String item) {
        elementData[size++] = item;
    }

    public String pop() {
        return elementData[--size];
    }

    public boolean isFull() {
        return size == elementData.length;
    }


}
