package Plague;

import Simstation.*;
import mvc.*;

import java.awt.*;
import java.util.Iterator;

public class PlagueView extends SimulationView {
    // constants for agent color
    private static final Color INFECTED_COLOR = Color.RED;
    private static final Color SAFE_COLOR = Color.GREEN;

    // ctr
    public PlagueView(Model model) {
        super(model);
        setOpaque(true);
    }

    @Override
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        PlagueSimulation plague = (PlagueSimulation) model;
        Iterator<Agent> people = plague.agentIterator();

        int centerOffset = AGENT_SIZE / 2;
        while(people.hasNext()) {
            PlagueAgent p = (PlagueAgent) people.next();
            if (p.getInfected()) {
                gc.setColor(INFECTED_COLOR);
            } else {
                gc.setColor(SAFE_COLOR);
            }
            gc.fillOval(p.xc - centerOffset, p.yc - centerOffset, AGENT_SIZE, AGENT_SIZE);
        }
    }
}