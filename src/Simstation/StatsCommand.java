package Simstation;

import Flocking.*;
import mvc.*;
import Plague.*;
import randomwalk.*;

import java.util.ArrayList;
import java.util.List;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }

    protected String[] getStatsMessage() {
        Simulation simulation = (Simulation)model;
        List<String> stats = new ArrayList<>();

        stats.add("#agents = " + simulation.getAgentCount());
        stats.add("clock = " + simulation.getClock());

        if (model instanceof PlagueSimulation) {
            PlagueSimulation plagueSimulation = (PlagueSimulation)model;
            double infectedPercent = plagueSimulation.getPercentInfected();
            stats.add("%infected = " + infectedPercent);
        } else if (model instanceof FlockingSimulation) {

        }

        return stats.toArray(new String[0]);
    }

    public void execute() throws Exception {
        Utilities.inform(getStatsMessage());
    }
}
