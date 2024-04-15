package Casino;

import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CasinoPanel extends AppPanel {
    private CasinoView casinoView;
    private Casino casino;

    public CasinoPanel(AppFactory factory) {
        super(factory);

        // Initialize the casino model
        casino = (Casino) model;

        // Set layout for the panel
        setLayout(new BorderLayout());

        // Create the casino view
        casinoView = new CasinoView(casino);
        add(casinoView);

        // Create roll button
        JButton rollButton = new JButton("Roll");
        rollButton.addActionListener(this);

        // Add roll button to the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rollButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        casino.roll();
    }

    // Main method to display the panel
    public static void main(String[] args) {
        AppPanel panel = new CasinoPanel(new CasinoFactory());
        panel.display();
    }
}
