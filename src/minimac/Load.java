package minimac;

public class Load implements Instruction {
    int loc;
    int value;

    public Load(int loc,int value) {
        this.loc = loc;
        this.value = value;
    }

    public void execute(MiniMac mac) {
        mac.memory[loc] = value;
    }
}
