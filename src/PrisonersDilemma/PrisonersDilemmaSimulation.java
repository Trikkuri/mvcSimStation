package PrisonersDilemma;

import mvc.*;
import Simstation.*;

import java.util.Iterator;

public class PrisonersDilemmaSimulation extends Simulation {
    public static final int AGENT_POPULATION = 100;

    @Override
    public void populate() {
        for (int i = 0; i < AGENT_POPULATION; i++) {
            Strategy strategy = switch (i % 4) {
                case 0 -> new AlwaysCooperate();
                case 1 -> new AlwaysCheat();
                case 2 -> new RandomlyCooperate();
                default -> new Tit4Tat();
            };
            addAgent(new Prisoner("Prisoner" + i, strategy));
        }
    }

    public String getAverageFitness() {
        int totalCoop = 0, totalCheat = 0, totalRandom = 0, totalTit4Tat = 0;
        int countCoop = 0, countCheat = 0, countRandom = 0, countTit4Tat = 0;

        Iterator<Agent> it = this.agentIterator();
        while (it.hasNext()) {
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

        return String.format(
                "Average fitness for Always Cooperate: %s\n" +
                        "Average fitness for Always Cheat: %s\n" +
                        "Average fitness for Randomly Cooperate: %s\n" +
                        "Average fitness for Tit for Tat: %s",
                calculateAverage(totalCoop, countCoop),
                calculateAverage(totalCheat, countCheat),
                calculateAverage(totalRandom, countRandom),
                calculateAverage(totalTit4Tat, countTit4Tat)
        );
    }

    private String calculateAverage(int total, int count) {
        return count == 0 ? "N/A" : String.format("%.2f", (double) total / count);
    }


    public static void main(String[] args) {
        SimulationFactory factory = new PrisonersDilemmaFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }

}
