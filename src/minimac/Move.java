package minimac;

public class Move implements Instruction {
    private int src;
    private int dest;

    public Move(int src,int dest) {
        this.src = src;
        this.dest = dest;
    }

    public void execute(MiniMac mac) {
        mac.memory[dest] = mac.memory[src];
    }
}