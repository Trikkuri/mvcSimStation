package Casino;

import mvc.*;

public class RollCommand extends Command {

    public RollCommand(Model m) {
        super(m);
    }

    @Override
    public void execute() {
        Casino cas = (Casino) model;
        cas.roll();
    }

}
