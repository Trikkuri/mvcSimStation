package Simstation;

import Flocking.*;
import PrisonersDilemma.*;
import Plague.*;

import mvc.*;


import java.util.*;

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
            int[] speedsCount = new int[5];  // Array to hold counts for speeds 1-5

            Iterator<Agent> it = simulation.agentIterator();
            while (it.hasNext()) {
                Agent agent = it.next();
                if (agent instanceof Bird) {
                    Bird bird = (Bird) agent;
                    int speed = bird.getSpeed();  // Assume Bird has a getSpeed() method
                    if (speed >= 1 && speed <= 5) {
                        speedsCount[speed - 1]++;
                    }
                }
            }

            for (int i = 0; i < speedsCount.length; i++) {
                stats.add("#birds @ speed " + (i + 1) + " = " + speedsCount[i]);
            }
        }
        else if (model instanceof PrisonersDilemmaSimulation) {
            PrisonersDilemmaSimulation pdSim = (PrisonersDilemmaSimulation) model;
            stats.add("# Prisoners: " + pdSim.AGENT_POPULATION);
            stats.add("Average Fitness: " + pdSim.getAverageFitness());
        }

        return stats.toArray(new String[0]);
    }

    public void execute() throws Exception {
        Utilities.inform(getStatsMessage());
    }
}
