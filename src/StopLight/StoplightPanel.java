package StopLight;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;


public class StoplightPanel extends AppPanel {
    private JButton change;
    public StoplightPanel(AppFactory factory) {
        super(factory);
        change = new JButton("Change");
        change.addActionListener(this);
        controls.add(change);
    }

    public static void main(String[] args) {
        AppFactory factory = new StoplightFactory();
        AppPanel panel = new StoplightPanel(factory);
        panel.display();
    }

}