package Casino;

import mvc.*;

public class CasinoFactory implements AppFactory {
    @Override
    public Model makeModel() {
        return new Casino();
    }

    @Override
    public View makeView(Model model) {
        return new CasinoView(model);
    }

    @Override
    public String getTitle() {
        return "Casino Midterm";
    }

    @Override
    public String[] getHelp() {
        String[] commands = new String[1];
        commands[0] = "Select Roll to roll the dice";
        commands[1] = "Seven wins";
        commands[2] = "Three loses";
        return commands;
    }

    @Override
    public String about() {
        return "Casino Midterm by Stefan Le";
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"Roll"};
    }

    @Override
    public Command makeEditCommand(Model model, String name, Object object) {
        if (name == "Roll") return new RollCommand(model);
        return null;
    }
}
