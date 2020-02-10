package com.company.secondtask.figure.orientation;

import com.company.secondtask.Position;

public class North implements Orientation {

    @Override
    public void moveOneCellForward(Position position) {
        position.setY(position.getY() + 1);
    }

    @Override
    public Orientation turnRight() {
        return new East();
    }
}
