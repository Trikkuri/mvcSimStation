package Simstation;

import mvc.*;

import java.util.*;

import static Simstation.Agent.WORLD_SIZE;

public class Simulation extends Model {
    private List<Agent> agents;
    transient private Timer timer; // timers aren't serializable
    private int clock;

    // own boolean checking whether its running or suspended
    private boolean isRunning;
    private boolean isSuspended;

    public Simulation() {
        super();
        clock = 0;
        agents = new LinkedList<Agent>();
        isRunning = false;
        isSuspended = false;
    }

    // timer methods given by professor
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }
    private void stopTimer() {
        timer.cancel();
        timer.purge();
    }
    private class ClockUpdater extends TimerTask {
        public void run() {
            clock++;
        }
    }

    // adding agents
    public void addAgent(Agent a) {
        a.xc = Utilities.rng.nextInt(WORLD_SIZE);
        a.yc = Utilities.rng.nextInt(WORLD_SIZE);
        agents.add(a);
        a.setWorld(this);
    }


    // getting agents
    public Iterator<Agent> agentIterator() {
        return agents.iterator();
    }
    public int getAgentCount() {
        return agents.size();
    }


    // getting clock
    public int getClock() {
        return clock;
    }


    // checking whether simulation is running or suspended
    public boolean isRunning() {
        return isRunning;
    }
    public boolean isSuspended() {
        return isSuspended;
    }


    // start, suspend, resume, stop
    public void start()
    {
        clock = 0;
        startTimer();
        agents.clear();
        populate();
        for(Agent a : agents) {
            a.start();
        }
        isRunning = true;
        isSuspended = false;
        changed();
    }
    public void suspend()
    {
        stopTimer();
        for(Agent a : agents) { a.suspend(); }
        isSuspended = true;
        changed();
    }
    public void resume()
    {
        startTimer();
        for(Agent a : agents) { a.resume(); }
        isSuspended = false;
        changed();
    }
    public void stop()
    {
        stopTimer();
        for(Agent a : agents) { a.stop(); }
        isRunning = false;
        isSuspended = false;
        changed();
    }


    // getNeighbor method
    public synchronized Agent getNeighbor(Agent a, double radius) {
        double xcLowerBound = a.xc - radius;
        double xcUpperBound = a.xc + radius;
        double ycLowerBound = a.yc - radius;
        double ycUpperBound = a.yc + radius;

        for(Agent ag : agents)
        {
            if(ag != a &&
                    ag.xc >= xcLowerBound && ag.xc <= xcUpperBound &&
                    ag.yc >= ycLowerBound && ag.yc <= ycUpperBound)
            { return ag; }
        }
        return null;
    }

    public void populate() {}
}
