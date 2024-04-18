package Plague;

import Simstation.*;
import mvc.*;
import static Plague.PlagueSimulation.RESISTANCE;
import static Plague.PlagueSimulation.VIRULENCE;

public class PlagueAgent extends Agent {
    private boolean infected;
    private static final int INITIAL_INFECTED = 10;

    // ctr
    public PlagueAgent() {
//        int startingInfected = Utilities.rng.nextInt(10) + 1;
//        if (startingInfected < INITIAL_INFECTED){
//            infected = true;
//        } else {
//            infected = false;
//        }
        heading = Heading.random();
    }

    // getter for infected
    public boolean getInfected() {
        return infected;
    }
    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    // updating the infected
    public void update() {
        PlagueAgent neighbor = (PlagueAgent) world.getNeighbor(this,10);

        // if there are neighbors...
        if (neighbor != null) {
            // creates agents that are either infected or resistant
            int infection = Utilities.rng.nextInt(100) + 1;
            int resist = Utilities.rng.nextInt(100) + 1;

            // if they are infected...
            if (neighbor.getInfected() && !this.getInfected()){
                if (infection > VIRULENCE){
                    this.infected = true;
                    if (resist > RESISTANCE){
                        this.infected = false;
                    }
                }
            }
        }
        heading = Heading.random();
        move(5);
    }
}
