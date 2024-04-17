package PrisonersDilemma;

import Simstation.*;
import mvc.*;

public class PrisonersDilemmaStatsCommand extends StatsCommand {
    public PrisonersDilemmaStatsCommand(Model model) {
        super(model);
    }

    @Override
    public String[] getStatsMessage() {
        PrisonersDilemmaSimulation pdSim = (PrisonersDilemmaSimulation) model;
        return new String[] {
                "# Prisoners: " + pdSim.AGENT_POPULATION,
                "Average Fitness: " + pdSim.getAverageFitness()
        };
    }

    @Override
    public void execute() {
        PrisonersDilemmaSimulation sim = (PrisonersDilemmaSimulation) getModel();
        String statsMessage = sim.getAverageFitness();
        Utilities.inform(statsMessage);
    }
}
