package com.company.secondtask.figure.orientation;

import com.company.secondtask.Position;

public class East implements Orientation {

    @Override
    public void moveOneCellForward(Position position) {
        position.setX(position.getX() + 1);
    }

    @Override
    public Orientation turnRight() {
        return new South();
    }
}
