package Simstation;

import mvc.*;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

public class SimulationPanel extends AppPanel{

    public SimulationPanel(SimulationFactory factory) {
        super(factory);

        // Strings for the buttons
        String[] buttonNames = {"Start", "Suspend", "Resume", "Stop", "Stats"};

        // setting the panel layout
        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));

        // creating the buttons itself
        for (String b : buttonNames) {
            JPanel panel = new JPanel();
            JButton button = new JButton();
            panel.add(button);
            button.addActionListener(this);
            controls.add(panel);
        }
    }


    // Implement actionPerformed method



    // unlike other projects, we don't have a main method because we are mot running Simstation itself,
    // we will run the sub-projects (Plague, randomwalks, etc..)
}
