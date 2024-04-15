package minimac;

public class Multiply implements Instruction {
    private int src1;
    private int src2;
    private int dest;

    public Multiply(int src1,int src2,int dest) {
        this.src1 = src1;
        this.src2 = src2;
        this.dest = dest;
    }

    public void execute(MiniMac mac) {
        Integer v1 = mac.memory[src1];
        Integer v2 = mac.memory[src2];

        if (v1 != null && v2 != null) {
            int result = v1.intValue() * v2.intValue();
            mac.memory[dest] = result;
        }
        else {
            throw new IllegalStateException("src1 or src2 is null");
        }
    }
}
