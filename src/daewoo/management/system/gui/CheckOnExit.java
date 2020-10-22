package daewoo.management.system.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is the class to implement Window Listener
 *
 */
public class CheckOnExit implements WindowListener {

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        ConfirmWindow check = new ConfirmWindow();
        check.setVisible(true);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    /**
     * This is the inner class ConfirmWindow which appear before exiting the
     * program
     */
    public class ConfirmWindow extends JFrame implements ActionListener {

        /**
         * This is the default constructor of the inner class ConfirmWindow
         */
        public ConfirmWindow() {
            setSize(200, 100);
            setLayout(new BorderLayout());
            setLocationRelativeTo(null);
            JLabel confirmLabel = new JLabel("Are you sure you want to exit?");
            add(confirmLabel, BorderLayout.CENTER);
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            JButton exitButton = new JButton("Yes");
            exitButton.addActionListener(this);
            buttonPanel.add(exitButton);
            JButton cancelButton = new JButton("No");
            cancelButton.addActionListener(this);
            buttonPanel.add(cancelButton);
            add(buttonPanel, BorderLayout.SOUTH);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            switch (s) {
                case "Yes":
                    System.exit(0);
                    break;
                case "No":
                    dispose();
                    break;
                default:
                    System.out.println("Unexpected error");
            }
        }
    }
}
