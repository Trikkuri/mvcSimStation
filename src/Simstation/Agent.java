package Simstation;

import mvc.*;
import java.io.Serializable;
import java.lang.Runnable;

public abstract class Agent implements Serializable, Runnable {
    protected Simulation world;
    protected String name;
    protected Heading heading;
    public int xc;
    public int yc;
    private boolean suspended;
    private boolean stopped;
    transient protected Thread myThread;

    public Agent(String name){
        this();
        this.name = name;
    }
    public Agent() {
        super();
        suspended = false;
        stopped = false;
        myThread = null;
    }

    public void setWorld(Simulation world){
        this.world = world;
    }

    public void run(){
        myThread = Thread.currentThread();
        checkSuspended();
        onStart();
        while(!isStopped()){
            try {
                update();
                Thread.sleep(20);
                checkSuspended();
            } catch(InterruptedException e){
                Utilities.error(e);
            }
        }
        onExit();
    }

    public synchronized void start() {
        myThread = new Thread(this);
        myThread.start();
    }
    public synchronized void suspend(){
        suspended = true;
    }
    public synchronized boolean isSuspended(){
        return suspended;
    }
    public synchronized void resume(){
        notify();
    }
    public synchronized void stop(){
        stopped = true;
    }
    public synchronized boolean isStopped(){
        return stopped;
    }
    private synchronized void checkSuspended() {
        try {
            while(!stopped && suspended) {
                // onInterrupted();
                wait();
                suspended = false;
            }
        } catch (InterruptedException e) {
            Utilities.error(e);
        }
    }

    public abstract void update();

    public void move(int steps){
        int xTemp, yTemp;
        switch (heading){
            case NORTH: {
                for (int i = 0; i < steps; i++) {
                    yc = ((yc - 1) % 250 + 250) % 250;
                    world.changed();
                }
                break;
            }
            case EAST:{
                for(int i = 0; i < steps; i++) {
                    xc = ((xc + 1) % 250) % 250;
                    world.changed();
                }
                break;
            }
            case SOUTH:{
                for(int i = 0; i < steps; i++) {
                    yc = ((yc + 1) % 250) % 250;
                    world.changed();
                }
                break;
            }
            case WEST:{
                for(int i = 0; i < steps; i++) {
                    xc = ((xc - 1) % 250 + 250) % 250;
                    world.changed();
                }
                break;
            }
        }
    }

    // empty methods to be called in run()
    public void onStart() {
        // override
    }

    public void onInterrupted() {
        // override
    }

    public void onExit() {
        // override
    }
}