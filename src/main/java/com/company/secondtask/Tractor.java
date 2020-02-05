package com.company.secondtask;

public class Tractor extends Figure {

    public Tractor(Field field, Position position, Orientation orientation) {
        this.field = field;
        this.position = position;
        this.orientation = orientation;
    }

    public void move(String command) {
        if (command.equals("F")) {
            moveForwards();
        } else if (command.equals("T")) {
            turnClockwise();
        }
    }

    public void moveForwards() {
        if (orientation == Orientation.NORTH) {
            position.setY(position.getY() + 1);
        } else if (orientation == Orientation.EAST) {
            position.setX(position.getX() + 1);
        } else if (orientation == Orientation.SOUTH) {
            position.setY(position.getY() - 1);
        } else if (orientation == Orientation.WEST) {
            position.setX(position.getX() - 1);
        }
        if (position.getX() > field.getField()[0].length || position.getX() < 0
                || position.getY() > field.getField().length || position.getY() < 0) {
            throw new TractorInDitchException("Tractor went out of field.");
        }
    }

    public void turnClockwise() {
        if (orientation == Orientation.NORTH) {
            orientation = Orientation.EAST;
        } else if (orientation == Orientation.EAST) {
            orientation = Orientation.SOUTH;
        } else if (orientation == Orientation.SOUTH) {
            orientation = Orientation.WEST;
        } else if (orientation == Orientation.WEST) {
            orientation = Orientation.NORTH;
        }
    }
}
