package Simstation;

import mvc.*;

public class SuspendCommand extends Command {
    public SuspendCommand(Model model) {
        super(model);
    }

    public void execute() throws Exception {
        Simulation simulation = (Simulation)model;
        if (!simulation.isRunning()) {
            Utilities.error("Please start the simulation. It has not been started.");
        } else if (!simulation.isSuspended()) {
            simulation.suspend();
        } else {
            Utilities.error("The simulation is already suspended!");
        }
    }
}