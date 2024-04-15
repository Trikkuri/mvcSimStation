package minimac;

public class Blt implements Instruction {
    int loc;
    int offset;

    public Blt(int loc,int offset) {
        this.loc = loc;
        this.offset = offset;
    }

    public void execute(MiniMac mac) {
        if (mac.memory[loc] < 0) {
            mac.ip += offset;
        }
    }
}
