package minimac;

public class Bgt implements Instruction {
    int loc;
    int offset;

    public Bgt(int loc,int offset) {
        this.loc = loc;
        this.offset = offset;
    }

    public void execute(MiniMac mac) {
        if (0 < mac.memory[loc]) {
            mac.ip += offset;
        }
    }
}
