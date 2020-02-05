package com.company.secondtask.command;

import com.company.secondtask.*;
import com.company.secondtask.figure.Tractor;
import com.company.secondtask.figure.orientation.Orientation;

public class MoveForwardTractorCommand implements Command {

    private final Tractor tractor;

    public MoveForwardTractorCommand(Tractor tractor) {
        this.tractor = tractor;
    }

    @Override
    public void execute() {
        Orientation orientation = tractor.getOrientation();
        Position position = tractor.getPosition();
        orientation.moveOneCellForward(position);
        if (tractor.getField().outOfField(position)) {
            throw new TractorInDitchException("Tractor went out of field.");
        }
    }
}
