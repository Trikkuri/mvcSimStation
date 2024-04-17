package PrisonersDilemma;

import mvc.*;
import Simstation.*;

public class PrisonersDilemmaFactory extends SimulationFactory {
    @Override
    public Simulation makeModel() {
        return new PrisonersDilemmaSimulation();
    }

    @Override
    public View makeView(Model model) {
        return new PrisonersDilemmaView(model);
    }

    @Override
    public String getTitle() {
        return "Prisoner's Dilemma Simulation";
    }
}
