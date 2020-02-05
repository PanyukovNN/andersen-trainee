package com.company.secondtask;

public abstract class Figure {

    protected Field field;

    protected Orientation orientation;

    protected Position position;

    abstract void move(String command);

    abstract void turnClockwise();
}
