package PrisonersDilemma;

import mvc.*;
import Simstation.*;

import java.util.Iterator;

class Prisoner extends Agent {
    private int fitness = 0;
    private Strategy strategy;
    protected boolean partnerCheated = false;

    public Prisoner(String name, Strategy strategy) {
        super(name);
        this.strategy = strategy;
    }

    public boolean cooperate() {
        return strategy.decide(this);
    }

    public void updateFitness(boolean me, boolean other) {
        if (me && other) {
            fitness += 3; // Both cooperated
        } else if (me && !other) {
            fitness += 0; // I cooperated, they cheated
        } else if (!me && other) {
            fitness += 5; // I cheated, they cooperated
        } else {
            fitness += 1; // Both cheated
        }
    }

    @Override
    public void update() {
        Agent neighbor = world.getNeighbor(this, 10); // Find a random neighbor within 10 steps
        if (neighbor instanceof Prisoner) {
            Prisoner other = (Prisoner) neighbor;
            boolean myDecision = cooperate();
            boolean theirDecision = other.cooperate();

            updateFitness(myDecision, theirDecision);
            other.updateFitness(theirDecision, myDecision);

            partnerCheated = !theirDecision;
            other.partnerCheated = !myDecision;
        }
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public int getFitness() {
        return fitness;
    }
}

interface Strategy {
    boolean decide(Prisoner me);
}

class AlwaysCooperate implements Strategy {
    @Override
    public boolean decide(Prisoner me) {
        return true;
    }
}

class AlwaysCheat implements Strategy {
    @Override
    public boolean decide(Prisoner me) {
        return false;
    }
}

class RandomlyCooperate implements Strategy {
    @Override
    public boolean decide(Prisoner me) {
        return Utilities.rng.nextBoolean();
    }
}

class Tit4Tat implements Strategy {
    @Override
    public boolean decide(Prisoner me) {
        return me.partnerCheated;
    }
}

class PrisonersDilemmaFactory extends SimulationFactory {
    @Override
    public Model makeModel() {
        return new PrisonersDilemmaSimulation();
    }

    @Override
    public String getTitle() {
        return "Prisoner's Dilemma Simulation";
    }
}

public class PrisonersDilemmaSimulation extends Simulation{
    @Override
    public void populate() {
        for (int i = 0; i < 100; i++) { // Create 100 prisoners
            Strategy strategy;
            switch (i % 4) {
                case 0: strategy = new AlwaysCooperate(); break;
                case 1: strategy = new AlwaysCheat(); break;
                case 2: strategy = new RandomlyCooperate(); break;
                default: strategy = new Tit4Tat(); break;
            }
            Prisoner p = new Prisoner("Prisoner" + i, strategy);
            this.addAgent(p);
        }
    }

    public void showStats() {
        int totalCoop = 0, totalCheat = 0, totalRandom = 0, totalTit4Tat = 0;
        int countCoop = 0, countCheat = 0, countRandom = 0, countTit4Tat = 0;

        for (Iterator<Agent> it = agentIterator(); it.hasNext(); ) {
            Agent a = it.next();
            if (a instanceof Prisoner) {
                Prisoner p = (Prisoner) a;
                int fitness = p.getFitness();
                Strategy strategy = p.getStrategy();
                if (strategy instanceof AlwaysCooperate) {
                    totalCoop += fitness;
                    countCoop++;
                } else if (strategy instanceof AlwaysCheat) {
                    totalCheat += fitness;
                    countCheat++;
                } else if (strategy instanceof RandomlyCooperate) {
                    totalRandom += fitness;
                    countRandom++;
                } else if (strategy instanceof Tit4Tat) {
                    totalTit4Tat += fitness;
                    countTit4Tat++;
                }
            }
        }

        System.out.println("Average fitness for Always Cooperate: " + (countCoop == 0 ? 0 : totalCoop / countCoop));
        System.out.println("Average fitness for Always Defect: " + (countCheat == 0 ? 0 : totalCheat / countCheat));
        System.out.println("Average fitness for Random Strategy: " + (countRandom == 0 ? 0 : totalRandom / countRandom));
        System.out.println("Average fitness for Tit for Tat: " + (countTit4Tat == 0 ? 0 : totalTit4Tat / countTit4Tat));
    }

    public static void main(String[] args) {
        AppFactory factory = new PrisonersDilemmaFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }

}
