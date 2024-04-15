package CALab;

import mvc.Command;
import mvc.Model;

public class RunCommand extends Command {
    private int loops;

    public RunCommand(Model model, int loops) {
        super(model);
        this.loops = loops;
    }

    @Override
    public void execute() {
        Grid grid = (Grid) model;
        grid.updateLoop(loops);
    }
}