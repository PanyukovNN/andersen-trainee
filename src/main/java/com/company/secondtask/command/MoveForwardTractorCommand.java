package com.company.secondtask.command;

import com.company.secondtask.*;
import com.company.secondtask.figure.Tractor;

public class MoveForwardTractorCommand implements Command {

    private final Tractor tractor;

    public MoveForwardTractorCommand(Tractor tractor) {
        this.tractor = tractor;
    }

    @Override
    public void execute() {
        Orientation orientation = tractor.getOrientation();
        Position position = tractor.getPosition();
        if (orientation == Orientation.NORTH) {
            position.setY(position.getY() + 1);
        } else if (orientation == Orientation.EAST) {
            position.setX(position.getX() + 1);
        } else if (orientation == Orientation.SOUTH) {
            position.setY(position.getY() - 1);
        } else if (orientation == Orientation.WEST) {
            position.setX(position.getX() - 1);
        }
        if (tractor.getField().outOfField(position)) {
            throw new TractorInDitchException("Tractor went out of field.");
        }
    }
}
