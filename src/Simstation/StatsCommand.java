package Simstation;

import mvc.*;
import java.util.*;

public class StatsCommand extends Command {
    public StatsCommand(Model model) {
        super(model);
    }

    protected String[] getStatsMessage() {
        Simulation simulation = (Simulation)model;

        // new textbox for stats
        return new String[]{
                "#agents = " + simulation.getAgentCount(),
                "clock = " + simulation.getClock()};
    }

    public void execute() throws Exception {
        // send message
        Utilities.inform(getStatsMessage());
    }
}