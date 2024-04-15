package CALab;

import mvc.Command;
import mvc.Model;
public class PopulateCommand extends Command {
    public PopulateCommand(Model model) {
        super(model);
    }

    public void execute() {
        Grid grid = (Grid) model;
        grid.repopulate();
    }

}
