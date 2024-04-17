import java.awt.*;

public class PlagueView extends SimulationView {
    // constants for agent color
    private static final Color INFECTED_COLOR = Color.RED;
    private static final Color SAFE_COLOR = Color.GREEN;

    public PlagueView(Model model) {
        super(model);
    }

    // Implement the draw method to draw the agents on the board
    @Override
    public void draw(Graphics g) {
        super.draw(g); // Call the superclass draw method to draw the environment

        PlagueSimulation plagueSimulation = (PlagueSimulation) getSimulation();
        Graphics2D g2d = (Graphics2D) g;

        // Get the agents from the simulation
        Iterator<Agent> agentIterator = plagueSimulation.agentIterator();

        // Loop through the agents and draw them
        while (agentIterator.hasNext()) {
            PlagueAgent agent = (PlagueAgent) agentIterator.next();
            Point location = agent.getLocation();
            boolean infected = agent.getInfected();

            // Set color based on infection status
            g2d.setColor(infected ? INFECTED_COLOR : SAFE_COLOR);

            // Draw the agent (e.g., as a circle)
            int agentSize = 10; // Adjust the size as needed
            g2d.fillOval(location.x, location.y, agentSize, agentSize);
        }
    }
}
