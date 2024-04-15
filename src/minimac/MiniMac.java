package minimac;

import mvc.Publisher;

import java.io.Serializable;
import java.util.List;

public class MiniMac extends Publisher implements Serializable {

    public int ip = 0;
    public boolean halt = false;
    public int size = 32;
    public Integer[] memory = new Integer[size];

    public List<Instruction> program;

    public void execute() {
        // fetch execute
        while (ip < program.size() && !halt) {
            Instruction next = program.get(ip++);
            next.execute(this);
            notifySubscribers();
        }

        ip = 0;
        halt = false;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            memory[i] = 0;
        }
        notifySubscribers();
    }
}
