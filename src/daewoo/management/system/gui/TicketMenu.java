package daewoo.management.system.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This is the GUI class of ticket menu
 *
 */
public class TicketMenu extends JFrame implements ActionListener {

    JLabel heading = new JLabel("Tickets Menu");
    JButton _add = new JButton("Add Ticket");
    JButton _view = new JButton("View Tickets");
    JButton _delete = new JButton("Cancel Ticket");
    JButton _print = new JButton("Print Ticket");
    JButton close = new JButton("Close");

    /**
     * This is the default constructor of the class TicketMenu
     */
    public TicketMenu() {
        super("Daewoo Management System");
        setLayout(new BorderLayout());
        setSize(400, 400);
        add(heading, BorderLayout.NORTH);
        add(close, BorderLayout.SOUTH);
        close.addActionListener(this);
        JPanel jp = new JPanel(new GridLayout(6, 1, 0, 20));
        jp.add(_add);
        jp.add(_view);
        jp.add(_delete);
        add(jp, BorderLayout.WEST);
        _add.addActionListener(this);
        _view.addActionListener(this);
        _delete.addActionListener(this);
        _print.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        switch (s) {
            case "Add Ticket":
                AddTicket addTicket = new AddTicket();
                addTicket.setLocationRelativeTo(null);
                addTicket.setVisible(true);
                break;
            case "View Tickets":
                ViewTicket viewTicket = new ViewTicket();
                viewTicket.setLocationRelativeTo(null);
                viewTicket.setVisible(true);
                break;
            case "Cancel Ticket":
                String cancel_id = JOptionPane.showInputDialog("Enter Ticket id to cancel");
                int ticket_no = 0;
                boolean check_seat = false;
                try {
                    ticket_no = Integer.parseInt(cancel_id);
                    check_seat = true;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input");
                }
                if (check_seat == true) {
                    Tickets.cancelTicket(ticket_no);
                }
                break;
            case "Close":
                dispose();
                break;
        }
    }

    /**
     * This is the inner class to implement add ticket button
     */
    public class AddTicket extends JFrame {

        JLabel heading1 = new JLabel("Add Ticket details");
        JTextField _id = new JTextField("Enter Ticket id here");
        JTextField _passenger = new JTextField("Enter ID of the passenger");
        JTextField _bus = new JTextField("Enter ID of the bus");
        JTextField _city = new JTextField("Where you want to go");
        JTextField _seat = new JTextField("Enter seat no");
        JTextField _discount = new JTextField("Enter discount promo(if any)");
        JButton _close = new JButton("Close");
        JButton _add = new JButton("Add Ticket");
        JButton check_id = new JButton("Check ID validation");
        JButton check_p_id = new JButton("Check ID validation");
        JButton check_b_id = new JButton("Check ID validation");
        JButton check_city = new JButton("Check city validation");
        JButton check_seat = new JButton("Check seat availability");
        JButton check_discount = new JButton("Check discount availability");
        Tickets t;
        Passengers p = new Passengers();
        Buses b = new Buses();

        /**
         * This is the default constructor of the inner class AddTicket
         */
        public AddTicket() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(400, 400);
            add(heading1, BorderLayout.NORTH);
            JPanel jp = new JPanel(new GridLayout(7, 2, 10, 20));
            jp.add(_id);
            jp.add(check_id);
            jp.add(_passenger);
            jp.add(check_p_id);
            jp.add(_city);
            jp.add(check_city);
            jp.add(_bus);
            jp.add(check_b_id);
            jp.add(_seat);
            jp.add(check_seat);
            jp.add(_discount);
            jp.add(check_discount);
            add(jp, BorderLayout.CENTER);
            JPanel jp1 = new JPanel(new FlowLayout());
            jp1.add(_add);
            jp1.add(_close);
            add(jp1, BorderLayout.SOUTH);
            ActionListener close_ = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            };
            _close.addActionListener(close_);
            ActionListener add_ = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    t = new Tickets(Integer.parseInt(_id.getText()), Integer.parseInt(_passenger.getText()), Integer.parseInt(_bus.getText()), Integer.parseInt(_seat.getText()), _city.getText(), _discount.getText());
                    if (Tickets.checkcount(Integer.parseInt(_bus.getText())) <= 40) {
                        t.add();
                        JOptionPane.showMessageDialog(null, "Record added");
                    } else {
                        JOptionPane.showMessageDialog(null, "No seats available");
                    }
                }
            };
            _add.addActionListener(add_);
            ActionListener check_id_ = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String read = _id.getText();
                    int c_id = 0;
                    boolean id_check = true;
                    try {
                        c_id = Integer.parseInt(read);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID not valid. Enter valid ID");
                        id_check = false;
                    }
                    if (Tickets.checkID(c_id) == true && id_check == true) {
                        JOptionPane.showMessageDialog(null, "ID is valid");
                        _id.setText(Integer.toString(c_id));
                    } else if (Tickets.checkID(c_id) == false && id_check == true) {
                        JOptionPane.showMessageDialog(null, "ID already exists. Enter another one");
                        _id.setText("Enter Ticekt id here");
                    }
                }
            };
            check_id.addActionListener(check_id_);
            ActionListener check_p_id_ = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String read = _passenger.getText();
                    int c_id = 0;
                    boolean id_check = true;
                    try {
                        c_id = Integer.parseInt(read);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID not valid. Enter valid ID");
                        id_check = false;
                    }
                    if (Passengers.checkID(c_id) == false && id_check == true) {
                        Passengers obj2 = new Passengers();
                        obj2 = obj2.search(c_id);
                        JOptionPane.showMessageDialog(null, "Relevant record is " + obj2.getName());
                        _passenger.setText(Integer.toString(c_id));
                    } else if (Passengers.checkID(c_id) == true && id_check == true) {
                        JOptionPane.showMessageDialog(null, "No passenger is found on this id. Enter another");
                    }
                }
            };
            check_p_id.addActionListener(check_p_id_);
            ActionListener check_b_id_ = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String read = _bus.getText();
                    int c_id = 0;
                    boolean id_check = true;
                    try {
                        c_id = Integer.parseInt(read);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID not valid. Enter valid ID");
                        id_check = false;
                    }
                    if (Buses.checkID(c_id) == false && id_check == true) {
                        JOptionPane.showMessageDialog(null, "Bus's data exists on this id");
                        _bus.setText(Integer.toString(c_id));
                        Slots s = Slots.searchSlot(c_id);
                        _city.setText(s.getArrival_city());
                    } else if (Buses.checkID(c_id) == true && id_check == true) {
                        JOptionPane.showMessageDialog(null, "No bus is found on this id. Enter another");
                    }
                }
            };
            check_b_id.addActionListener(check_b_id_);
            ActionListener check_city_ = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String read = _city.getText();
                    Slots[] s = Tickets.checkRoute(read);
                    for (int i = 0; i < s.length; i++) {
                        if (s[i].getArrival_city().equalsIgnoreCase(read)) {
                            JOptionPane.showMessageDialog(null, "Route available to " + read + " on bus no. " + s[i].getId() + " departing at " + s[i].getDeparture());
                            _bus.setText(Integer.toString(s[i].getId()));
                        } else {
                            JOptionPane.showMessageDialog(null, "No route found to " + read);
                        }
                    }
                }
            };
            check_city.addActionListener(check_city_);
            ActionListener check_seat_ = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String read = _seat.getText();
                    int c_id = 0;
                    boolean id_check = true;
                    try {
                        c_id = Integer.parseInt(read);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "seat is not valid. Enter valid seat no");
                        id_check = false;
                    }
                    if (Tickets.checkSeat(c_id, Integer.parseInt(_bus.getText())) == false && id_check == true) {
                        JOptionPane.showMessageDialog(null, "seat's available");
                        _seat.setText(Integer.toString(c_id));
                    } else if (Tickets.checkSeat(c_id, Integer.parseInt(_bus.getText())) == true && id_check == true) {
                        JOptionPane.showMessageDialog(null, "seat is taken. Enter another");
                    }
                }
            };
            check_seat.addActionListener(check_seat_);
            ActionListener check_discount_ = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String read = _discount.getText();
                    if (Tickets.checkDiscount(read) == true) {
                        JOptionPane.showMessageDialog(null, "you got 40% discount");
                    } else {
                        JOptionPane.showMessageDialog(null, "Promo doesn't exists");
                    }
                }
            };
            check_discount.addActionListener(check_discount_);
        }
    }

    /**
     * This is the inner class ViewTicket to implement view ticket button
     */
    public class ViewTicket extends JFrame {

        JLabel heading1 = new JLabel("Tickets");
        JButton close1 = new JButton("Close");

        /**
         * This is the default constructor of the inner class ViewTicket
         */
        public ViewTicket() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(500, 520);
            heading1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            add(heading1, BorderLayout.NORTH);
            add(close1, BorderLayout.SOUTH);
            ActionListener close1_ = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            };
            close1.addActionListener(close1_);
            String[] column = {"Ticekt ID", "Name", "Bus ID", "Seat no", "Fares"};
            DefaultTableModel tableModel = new DefaultTableModel(column, 0);
            JTable table = new JTable(tableModel);
            JPanel jp = new JPanel();
            jp.add(table);
            jp.add(new JScrollPane(table));
            add(jp, BorderLayout.WEST);
            ArrayList<Tickets> a = Tickets.viewTicket();
            Object[] obj1 = new Object[5];
            for (int i = 0; i < a.size(); i++) {
                obj1[0] = a.get(i).getT_id();
                obj1[1] = a.get(i).getName();
                obj1[2] = a.get(i).getBus_id();
                obj1[3] = a.get(i).getSeat_no();
                obj1[4] = a.get(i).getFare();
                tableModel.addRow(obj1);
            }
        }
    }
}
