package com.company.secondtask;

public enum Orientation {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Orientation turnRight() {
        if (this == Orientation.NORTH) {
            return Orientation.EAST;
        } else if (this == Orientation.EAST) {
            return Orientation.SOUTH;
        } else if (this == Orientation.SOUTH) {
            return Orientation.WEST;
        } else {
            return Orientation.NORTH;
        }
    }
}
