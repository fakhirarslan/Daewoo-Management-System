package daewoo.management.system.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is the GUI class of main menu
 *
 * 
 */
public class MainMenu extends JFrame {

    ImageIcon h_icon = new ImageIcon("mainmenu.png");
    JLabel heading = new JLabel();
    ImageIcon p_icon = new ImageIcon("person.png");
    JButton passenger = new JButton("Passenger Menu");
    ImageIcon d_icon = new ImageIcon("driver.png");
    JButton driver = new JButton("Drivers Menu");
    ImageIcon s_icon = new ImageIcon("route.png");
    JButton slot = new JButton("Slot Menu");
    ImageIcon b_icon = new ImageIcon("bus.png");
    JButton buses = new JButton("Buses Menu");
    ImageIcon t_icon = new ImageIcon("ticket.png");
    JButton tickets = new JButton("Tickets Menu");

    /**
     * This is the default constructor of MainMenu class
     */
    public MainMenu() {
        super("Daewoo Management System");
        getContentPane().setBackground(Color.white);
        setSize(565, 600);
        setLayout(new BorderLayout());
        heading.setFont(new Font("Times New Roman", Font.BOLD, 24));
        heading.setIcon(h_icon);
        heading.setBackground(Color.white);
        add(heading, BorderLayout.NORTH);
        JPanel p1 = new JPanel();
        p1.setBackground(Color.white);
        p1.setLayout(new GridLayout(2, 3, 0, 0));
        passenger.setIcon(p_icon);
        passenger.setBackground(Color.white);
        p1.add(passenger);
        driver.setIcon(d_icon);
        driver.setBackground(Color.white);
        p1.add(driver);
        slot.setIcon(s_icon);
        slot.setBackground(Color.white);
        p1.add(slot);
        buses.setIcon(b_icon);
        buses.setBackground(Color.white);
        p1.add(buses);
        tickets.setIcon(t_icon);
        tickets.setBackground(Color.white);
        p1.add(tickets);
        add(p1, BorderLayout.WEST);
        passenger.addActionListener(new _Passenger());
        driver.addActionListener(new _Driver());
        slot.addActionListener(new _Slot());
        buses.addActionListener(new _Buses());
        tickets.addActionListener(new _Tickets());
    }

    /**
     * This is inner class used to implement ActionListener of passenger button
     */
    public class _Passenger implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            PassengerMenu p_menu = new PassengerMenu();
            p_menu.setLocationRelativeTo(null);
            p_menu.setVisible(true);
            p_menu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    /**
     * This is inner class used to implement ActionListener of driver button
     */
    public class _Driver implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            DriverMenu d_menu = new DriverMenu();
            d_menu.setLocationRelativeTo(null);
            d_menu.setVisible(true);
            d_menu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }

    /**
     * TThis is inner class used to implement ActionListener of slot button
     */
    public class _Slot implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SlotMenu slotMenu = new SlotMenu();
            slotMenu.setLocationRelativeTo(null);
            slotMenu.setVisible(true);
        }
    }

    /**
     * This is inner class used to implement ActionListener of bus button
     */
    public class _Buses implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            BusMenu busMenu = new BusMenu();
            busMenu.setLocationRelativeTo(null);
            busMenu.setVisible(true);
        }
    }

    /**
     * This is inner class used to implement ActionListener of ticket button
     */
    public class _Tickets implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            TicketMenu ticketMenu = new TicketMenu();
            ticketMenu.setLocationRelativeTo(null);
            ticketMenu.setVisible(true);
        }
    }
}
