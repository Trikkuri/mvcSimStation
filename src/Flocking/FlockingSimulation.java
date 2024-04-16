package Flocking;

import mvc.*;
import Simstation.*;

class Bird extends Agent {
    private int speed;

    public Bird(String name) {
        super(name);
        this.heading = Heading.random(); // Random initial direction
        this.speed = Utilities.rng.nextInt(5) + 1; // Speed between 1 and 5
    }

    @Override
    public void update() {
        Agent neighbor = world.getNeighbor(this, 10); // Try to find a neighbor within 10 steps
        if (neighbor != null && neighbor instanceof Bird) {
            Bird nearbyBird = (Bird) neighbor;
            this.heading = nearbyBird.heading; // Align direction
            this.speed = nearbyBird.speed; // Align speed
        }
        move(speed);
    }
}

class FlockingFactory extends SimulationFactory {
    @Override
    public Model makeModel() {
        return new FlockingSimulation();
    }

    @Override
    public String getTitle() {
        return "Flocking Simulation";
    }
}

public class FlockingSimulation extends Simulation{

    @Override
    public void populate() {
        for (int i = 0; i < 100; i++) { // Create 100 birds
            Bird b = new Bird("Bird" + i);
            this.addAgent(b);
        }
    }

    public static void main(String[] args) {
        AppFactory factory = new FlockingFactory();
        AppPanel panel = new SimulationPanel(factory);
        panel.display();
    }

}
