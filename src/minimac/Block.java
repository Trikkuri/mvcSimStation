package minimac;

import java.util.List;

public class Block implements Instruction {
    private List<Instruction> instructions;

    public Block(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public void execute(MiniMac mac) {
        for (Instruction instruction : instructions) {
            instruction.execute(mac);
        }
    }
}
