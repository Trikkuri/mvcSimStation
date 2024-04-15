package minimac;

import javax.swing.*;
import java.awt.*;

public class MiniMacComponent extends JComponent {
    private JList<String> memory;
    private JList<String> instructions;
    private DefaultListModel<String> mModel;
    private DefaultListModel<String> iModel;

    public MiniMacComponent(MiniMac mac) {
        // sets the layout for 2 vertically cut boxes
        setLayout(new GridLayout(2, 1));

        // implemented with the JLists
        mModel = new DefaultListModel<>();
        updateLists(mac);
        memory = new JList<>(mModel);

        // implemented with the JLists from gpt
        iModel = new DefaultListModel<>();
        instructions = new JList<>(iModel);

        // add the panels
        add(new JScrollPane(memory));
        add(new JScrollPane(instructions));
    }

    public void updateLists(MiniMac mac) {
        // clears the memory model to update the list
        mModel.clear();

        // loop through the memory
        for (int i = 0; i < mac.memory.length; i++) {
            mModel.addElement("memory[" + i + "] = " + mac.memory[i]);
        }

        // if the list is not null clear it and convert the memory to strings
        if (mac.program != null) {
            iModel.clear();
            for (Instruction ins : mac.program) {
                iModel.addElement(ins.toString());
            }
        }
    }

}