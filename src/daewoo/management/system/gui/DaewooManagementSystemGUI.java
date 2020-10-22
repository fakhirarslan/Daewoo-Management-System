package daewoo.management.system.gui;

import javax.swing.JFrame;

/**
 * This is main GUI class of our project
 *
 */
public class DaewooManagementSystemGUI {

    /**
     * This is the main method of our project
     *
     * @param args
     */
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setVisible(true);
        mainMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainMenu.addWindowListener(new CheckOnExit());
    }

}
