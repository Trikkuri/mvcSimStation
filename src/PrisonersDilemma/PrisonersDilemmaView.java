package PrisonersDilemma;

import Simstation.*;
import mvc.*;
import java.awt.*;
import java.util.Iterator;

public class PrisonersDilemmaView extends SimulationView {

    public PrisonersDilemmaView(Model model) {
        super(model);
    }

    @Override
    protected void drawAgents(Graphics gc) {
        Simulation simulation = (Simulation) model;
        Iterator<Agent> it = simulation.agentIterator();

        while (it.hasNext()) {
            Agent agent = it.next();
            if (agent instanceof Prisoner) {
                Prisoner prisoner = (Prisoner) agent;
                Color color = prisoner.cooperate() ? Color.GREEN : Color.RED;
                gc.setColor(color);

                int x = prisoner.xc;
                int y = prisoner.yc;
                int size = AGENT_SIZE;
                int halfSize = size / 2;
                gc.fillOval(x - halfSize, y - halfSize, size, size);
            }
        }
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc); // Ensure the background is cleared
        drawAgents(gc); // Custom agent drawing
    }
}
