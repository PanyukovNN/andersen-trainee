package com.company.secondtask.command;

import com.company.secondtask.figure.Tractor;

public class TurnClockwiseTractorCommand implements Command {

    private final Tractor tractor;

    public TurnClockwiseTractorCommand(Tractor tractor) {
        this.tractor = tractor;
    }

    @Override
    public void execute() {
        tractor.setOrientation(tractor.getOrientation().turnRight());
    }
}
