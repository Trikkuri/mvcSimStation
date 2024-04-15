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

    // implement run method !!!!!

    public synchronized void start(){
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

    // implement move method !!!

}