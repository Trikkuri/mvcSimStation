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
    public void paintComponent(Graphics gc) {
        super.paintComponent(gc);
        Simulation sim = (Simulation) model;
        Iterator<Agent> it = sim.agentIterator();

        int index = 0;
        int rowSize = (int) Math.sqrt(sim.getAgentCount());  // Determine the row size if you want a grid layout

        while (it.hasNext()) {
            Prisoner p = (Prisoner) it.next();
            int x = (index % rowSize) * 20 + 10;  // 20 pixels apart, 10 pixel offset
            int y = (index / rowSize) * 20 + 10;

            Color color = p.cooperate() ? Color.GREEN : Color.RED;  // Green for cooperate, Red for cheat
            gc.setColor(color);
            gc.fillOval(x, y, 10, 10);  // Draw each prisoner at calculated coordinates

            index++;
        }
    }
}
