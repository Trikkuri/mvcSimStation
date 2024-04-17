package Flocking;

import Simstation.*;
import mvc.*;

public class Bird extends Agent {
    private int speed;

    public Bird(String name) {
        super(name);
        this.heading = Heading.random(); // Random direction
        this.speed = Utilities.rng.nextInt(5) + 1;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void update() {
        Agent neighbor = world.getNeighbor(this, 10);
        if (neighbor != null && neighbor instanceof Bird) {
            Bird nearbyBird = (Bird) neighbor;
            this.heading = nearbyBird.heading; // Align direction
            this.speed = nearbyBird.speed; // Align speed
        }
        move(speed);
    }
}