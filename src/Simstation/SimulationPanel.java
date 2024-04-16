package Simstation;

import mvc.AppFactory;
import mvc.AppPanel;

import java.awt.*;

public class SimulationPanel extends AppPanel {
    public SimulationPanel(AppFactory factory) {
        super(factory);
        controls.setLayout(new GridLayout(5, 1));
        addButtons();
    }
}