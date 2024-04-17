package Simstation;

import Flocking.*;
import PrisonersDilemma.*;
import Plague.*;

import mvc.*;


import java.util.ArrayList;
import java.util.List;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }

    protected String[] getStatsMessage() {
        Simulation simulation = (Simulation)model;
        List<String> stats = new ArrayList<>();

        // default random walks stats
        stats.add("#agents = " + simulation.getAgentCount());
        stats.add("clock = " + simulation.getClock());

        // specific stats for Plague, Flocking, & PD
        if (model instanceof PlagueSimulation) {
            PlagueSimulation plagueSimulation = (PlagueSimulation)model;
            double infectedPercent = plagueSimulation.getPercentInfected();
            stats.add("%infected = " + infectedPercent);
        } else if (model instanceof FlockingSimulation) {

        }
        else if (model instanceof PrisonersDilemmaSimulation) {

        }

        return stats.toArray(new String[0]);
    }

    public void execute() throws Exception {
        Utilities.inform(getStatsMessage());
    }
}
