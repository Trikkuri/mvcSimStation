package Simstation;

import mvc.*;

public class ResumeCommand extends Command {
    public ResumeCommand(Model model) {
        super(model);
    }

    public void execute() throws Exception {
        Simulation simulation = (Simulation)model;
        if (!simulation.isRunning()) {
            Utilities.error("Please start the simulation. It has not been started");
        } else if (simulation.isSuspended()) {
            simulation.resume();
        } else {
            Utilities.error("The simulation is already resumed!");
        }
    }
}