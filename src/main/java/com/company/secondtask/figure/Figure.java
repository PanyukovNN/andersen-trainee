package com.company.secondtask.figure;

import com.company.secondtask.Position;
import com.company.secondtask.command.Command;

public abstract class Figure {

    protected Position position;

    abstract void move(Command command);

    public Position getPosition() {
        return position;
    }
}
