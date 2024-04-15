package minimac;

import mvc.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AppPanel extends JPanel implements ActionListener {
    private MiniMac mac;
    private ControlPanel controls;
    private MiniMacView view;

    public AppPanel() {
        mac = new MiniMac();
        view = new MiniMacView(mac);
        controls = new ControlPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controls);
        this.add(view);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("MiniMac Program");
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"Change"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            switch (cmmd) {
                case "Save": {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.mac);
                    os.close();
                    break;
                }

                case "Open": {

                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        mac = (MiniMac) is.readObject();
                        view.setMiniMac(mac);
                        is.close();
                    }

                    break;

                }

                case "New": {
                    mac = new MiniMac();
                    view.setMiniMac(mac);
                    break;
                }

                case "Quit": {
                    System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform("Cyberdellic Designs Turtle Graphics, 2021. All rights reserved.");
                    break;
                }

                case "Help": {
                    String[] cmmds = new String[]{
                            "Parse: Parses the program",
                    };
                    Utilities.inform(cmmds);
                    break;
                }

                case "Parse": {
                    String program = JOptionPane.showInputDialog(this, "Enter program file name:");
                    if (program != null && !program.isEmpty()) {
                        try {
                            mac.program = MiniMacParser.parse(program);
                            JOptionPane.showMessageDialog(this, "Program parsed successfully.");
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(this, "Error parsing program: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                }

                case "Run": {
                    mac.execute();
                    view.repaint();
                    break;
                }

                case "Clear": {
                    mac.clear();
                    view.repaint();
                    break;
                }

                default: {
                    throw new Exception("Unrecognized command: " + cmmd);
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }

    class ControlPanel extends JPanel {
        public ControlPanel() {
            setBackground(Color.WHITE);
            setLayout(new GridLayout(3, 1));

            JButton parseButton = new JButton("Parse");
            parseButton.addActionListener(AppPanel.this);
            add(parseButton);

            JButton runButton = new JButton("Run");
            runButton.addActionListener(AppPanel.this);
            add(runButton);

            JButton clearButton = new JButton("Clear");
            clearButton.addActionListener(AppPanel.this);
            add(clearButton);
        }
    }

    public static void main(String[] args) {
        AppPanel app = new AppPanel();
    }
}