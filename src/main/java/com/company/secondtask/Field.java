package com.company.secondtask;

public class Field {

    private int[][] field;

    public void initRectangleField(int size) {
        this.field = new int[5][5];
    }

    public int[][] getField() {
        return field;
    }
}
