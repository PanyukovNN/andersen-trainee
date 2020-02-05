package com.company.secondtask;

public class Main {

    public static void main(String[] args) {
        Field field = new Field();
        Position startPosition = new Position(0, 0);
        Orientation startOrientation = Orientation.NORTH;
        Tractor tractor = new Tractor(field, startPosition, startOrientation);

        for (int i = 0; i < 10; i++) {
            tractor.move("F");
        }
    }
}
