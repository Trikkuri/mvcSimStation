package Casino;

import mvc.Model;
import mvc.View;

import javax.swing.*;
import java.awt.*;

public class CasinoView extends View {

    // taken from midterm page
    JTextField die1Field = new JTextField("1", 5); // displays 1 in 5 columns
    JTextField die2Field = new JTextField("1",5);
    JTextField total = new JTextField("0",5);

    public CasinoView(Model m) {
        super(m);
        setLayout(new GridLayout(1,2));

        // adding JLabels and JTextFields to display
        add(new JLabel("Die 1:"));
        add(die1Field);
        add(new JLabel("Die 2:"));
        add(die2Field);
        add(new JLabel("Score:"));
        add(total);
    }

    public void update() {
        Casino casino = (Casino) model;

        // update text fields
        die1Field.setText(Integer.toString(casino.getDie1()));
        die2Field.setText(Integer.toString(casino.getDie2()));
        total.setText(Integer.toString(casino.getWins() - casino.getLosses()));
    }
}
