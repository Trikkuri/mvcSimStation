package Plague;

import Simstation.*;
import mvc.*;

public class PlagueStatsCommand extends StatsCommand {

    // ctr
    public PlagueStatsCommand(Model model) {
        super(model);
    }

    // method to print the stats command for Plague
    public String[] getStatsMessage() {
        PlagueSimulation plagueSimu = (PlagueSimulation) model;

        // variable for infectedPercent
        double infectedPercent = plagueSimu.getPercentInfected();

        // printing the stats command
        return new String[] {
                "#agents: " + plagueSimu.AGENT_POPULATION,
                "clock: " + plagueSimu.getClock(),
                "%infected: " + infectedPercent
        };
    }
}
