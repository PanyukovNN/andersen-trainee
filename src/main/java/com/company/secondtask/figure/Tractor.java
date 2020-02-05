package com.company.secondtask.figure;

import com.company.secondtask.Field;
import com.company.secondtask.Position;
import com.company.secondtask.command.Command;
import com.company.secondtask.figure.orientation.Orientation;

public class Tractor extends Figure {

    private final Field field;

    private Orientation orientation;

    public Tractor(Field field, Position position, Orientation orientation) {
        this.field = field;
        this.position = position;
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Field getField() {
        return field;
    }

    public void move(Command command) {
        command.execute();
    }
}
