package PrisonersDilemma;

import Simstation.*;

public class Prisoner extends Agent {
    private int fitness = 0;
    private Strategy strategy;
    protected boolean partnerCheated = false;

    public Prisoner(String name, Strategy strategy) {
        super(name);
        this.strategy = strategy;
        this.heading = Heading.random();
    }

    public boolean cooperate() {
        return strategy.decide(this);
    }

    public void updateFitness(boolean me, boolean other) {
        if (me && other) fitness += 3; // Both cooperated
        else if (me) fitness += 0; // I cooperated, they cheated
        else if (other) fitness += 5; // I cheated, they cooperated
        else fitness += 1; // Both cheated
    }

    @Override
    public void update() {
        Prisoner neighbor = (Prisoner)world.getNeighbor(this, 10);
        if (neighbor != null) {
            boolean myDecision = cooperate();
            boolean theirDecision = neighbor.cooperate();
            updateFitness(myDecision, theirDecision);
            neighbor.updateFitness(theirDecision, myDecision);
            partnerCheated = !theirDecision;
            neighbor.partnerCheated = !myDecision;
        }
        heading = Heading.random();
        move(2);
    }

    public int getFitness() {
        return fitness;
    }

    public Strategy getStrategy() {
        return strategy;
    }
}