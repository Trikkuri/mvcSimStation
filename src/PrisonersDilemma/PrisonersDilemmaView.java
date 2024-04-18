package PrisonersDilemma;

import Simstation.*;
import mvc.*;
import java.awt.*;
import java.util.Iterator;

public class PrisonersDilemmaView extends SimulationView {

    private final Color colorCooperate = Color.GREEN;
    private final Color colorRandomlyCooperate = Color.YELLOW;
    private final Color colorCheat = Color.RED;
    private final Color colorTit4Tat = Color.BLUE;

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
                    gc.setColor(colorCooperate);
                    break;
                case RANDOMLY_COOPERATE:
                    gc.setColor(colorRandomlyCooperate);
                    break;
                case CHEAT:
                    gc.setColor(colorCheat);
                    break;
                case TIT4TAT:
                    gc.setColor(colorTit4Tat);
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
