package Plague;

import Simstation.*;
import mvc.*;

import java.util.Iterator;

public class PlagueSimulation extends Simulation {
    public static int VIRULENCE = 50; // % chance of infection
    public static int RESISTANCE = 2; // % chance of resisting infection
    public static final int AGENT_POPULATION = 50;

    // populate the board with extension of agent
    public void populate() {
        for (int i = 0; i < AGENT_POPULATION; i++) {
            PlagueAgent pa = new PlagueAgent();
            addAgent(pa);

            if (Utilities.rng.nextInt(100) < 5) {
                pa.setInfected(true);
            }
        }
    }

    // getting the number of agents infected
    public double getPercentInfected(){
        Iterator<Agent> people = this.agentIterator();
        double numInfected = 0;
        while(people.hasNext()){
            PlagueAgent pa = (PlagueAgent) people.next();
            if (pa.getInfected()){
                numInfected++;
            }
        }
        return (numInfected / AGENT_POPULATION) * 100;
    }

    // main method to display simulation
    public static void main(String[] args){
        AppPanel panel = new SimulationPanel(new PlagueFactory());
        panel.display();
    }
}
