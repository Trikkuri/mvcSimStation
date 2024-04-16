package Simstation;

import mvc.*;

public class SimulationFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new Simulation();
    }

    @Override
    public View makeView(Model model) {
        return new SimulationView(model);
    }

    @Override
    public String getTitle() {
        return "SimStation Group 8";
    }

    @Override
    public String[] getHelp() {
        return new String[] {
                "Start: Begins a new simulation",
                "Suspend: Suspends an active simulation",
                "Resume: Resumes a suspended simulation.",
                "Stop: Ends a simulation completely without being able to resume.",
                "Stats: Displays simulation information."};
    }

    @Override
    public String about() {
        return "Simstation from Stefan Le,...";
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"Start", "Suspend", "Resume", "Stop", "Stats"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source) {
        switch (type) {
            case "Start" -> {
                return new StartCommand(model);
            }
            case "Suspend" -> {
                return new SuspendCommand(model);
            }
            case "Resume" -> {
                return new ResumeCommand(model);
            }
            case "Stop" -> {
                return new StopCommand(model);
            }
            case "Stats" -> {
                return new StatsCommand(model);
            }
            default -> {
                return null;
            }
        }
    }
}
