package Plague;

import Simstation.*;
import mvc.*;

public class PlagueFactory extends SimulationFactory {
    public Model makeModel(){
        return new PlagueSimulation();
    }
    public View makeView(Model model){
        return new PlagueView(model);
    }
    public String getTitle(){
        return "Plague Customization";
    }
    protected StatsCommand statsCommand(Model model){
        return new PlagueStatsCommand(model);
    }
}
