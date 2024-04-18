package PrisonersDilemma;

import Simstation.*;
import mvc.*;
import java.awt.*;
import java.util.Iterator;

public class PrisonersDilemmaView extends SimulationView {

    private final Color COLOR_COOPERATE = Color.GREEN;
    private final Color COLOR_RANDOMLY_COOPERATE = Color.YELLOW;
    private final Color COLOR_CHEAT = Color.RED;
    private final Color COLOR_TIT4TAT = Color.BLUE;

    public PrisonersDilemmaView(Model model) {
        super(model);
    }

    @Override
    protected void drawAgents(Graphics gc) {
        PrisonersDilemmaSimulation ps = (PrisonersDilemmaSimulation) model;
        Iterator<Agent> it = ps.agentIterator();

        int centerOffset = AGENT_SIZE / 2;

        while (it.hasNext()) {
            Prisoner p = (Prisoner) it.next();
            StrategyType type = p.getStrategy().getType();

            switch (type) {
                case COOPERATE:
                    gc.setColor(COLOR_COOPERATE);
                    break;
                case RANDOMLY_COOPERATE:
                    gc.setColor(COLOR_RANDOMLY_COOPERATE);
                    break;
                case CHEAT:
                    gc.setColor(COLOR_CHEAT);
                    break;
                case TIT4TAT:
                    gc.setColor(COLOR_TIT4TAT);
                    break;
            }

            // Draw the agent
            gc.fillOval(p.xc - centerOffset, p.yc - centerOffset, AGENT_SIZE, AGENT_SIZE);
        }
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        drawAgents(gc);
    }
}
