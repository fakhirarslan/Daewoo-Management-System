package daewoo.management.system.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is the class DriverMenu of the drivers menu
 *
 */
public class DriverMenu extends JFrame {

    JLabel heading = new JLabel("Driver's menu");
    JButton monthly = new JButton("Monthly salaried driver");
    JButton weekly = new JButton("Weekly salaried driver");
    JButton close = new JButton("Close");

    /**
     * This is the default constructor of the class DriverMenu
     */
    public DriverMenu() {
        super("Daewoo Management System");
        setLayout(new BorderLayout());
        setSize(400, 400);
        heading.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        add(heading, BorderLayout.NORTH);
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        jp.add(monthly);
        jp.add(weekly);
        add(jp, BorderLayout.CENTER);
        ActionListener monthly_ = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MonthlyMenu monthly_menu = new MonthlyMenu();
                monthly_menu.setLocationRelativeTo(null);
                monthly_menu.setVisible(true);
                monthly_menu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        };
        monthly.addActionListener(monthly_);
        ActionListener weekly_ = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WeeklyMenu weekly_menu = new WeeklyMenu();
                weekly_menu.setLocationRelativeTo(null);
                weekly_menu.setVisible(true);
                weekly_menu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        };
        weekly.addActionListener(weekly_);
        add(close, BorderLayout.SOUTH);
        ActionListener close_ = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
        close.addActionListener(close_);
    }
}
