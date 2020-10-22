package daewoo.management.system.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This is the GUI class for Passenger Menu extending JFrame and implementing
 * ActionListener
 *
 */
public class PassengerMenu extends JFrame implements ActionListener {

    JLabel heading = new JLabel("Passengers Menu");
    JButton add_ = new JButton("Add Passenger");
    JButton view = new JButton("View Passenger Data");
    JButton s_id = new JButton("Search Passenger by ID");
    JButton s_name = new JButton("Search Passenger by name");
    JButton delete = new JButton("Delete Passenger's data");
    JButton update = new JButton("Update Passenger's data");
    JButton close = new JButton("Close");

    /**
     * This is the default constructor of PassengerMenu class
     */
    public PassengerMenu() {
        super("Daewoo Management System");
        setSize(400, 400);
        setLayout(new BorderLayout());
        heading.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        add(heading, BorderLayout.NORTH);
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 2, 20, 50));
        p1.add(add_);
        p1.add(view);
        p1.add(s_id);
        p1.add(s_name);
        p1.add(delete);
        p1.add(update);
        add(p1, BorderLayout.CENTER);
        add_.addActionListener(this);
        view.addActionListener(this);
        s_id.addActionListener(this);
        s_name.addActionListener(this);
        delete.addActionListener(this);
        update.addActionListener(this);
        add(close, BorderLayout.SOUTH);
        ActionListener close_ = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
        close.addActionListener(close_);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        String text = null;
        switch (s) {
            case "Add Passenger":
                AddPassenger add__ = new AddPassenger();
                add__.setLocationRelativeTo(null);
                add__.setVisible(true);
                break;
            case "View Passenger Data":
                ViewPassenger view_ = new ViewPassenger();
                view_.setLocationRelativeTo(null);
                view_.setVisible(true);
                break;
            case "Search Passenger by ID":
                SearchPassenger search__ = new SearchPassenger();
                search__.setLocationRelativeTo(null);
                search__.setVisible(true);
                break;
            case "Search Passenger by name":
                SearchPassengerN search_name = new SearchPassengerN();
                search_name.setLocationRelativeTo(null);
                search_name.setVisible(true);
                break;
            case "Delete Passenger's data":
                Passengers obj = new Passengers();
                text = JOptionPane.showInputDialog("Enter ID to delete:");
                JOptionPane.showMessageDialog(null, obj.delete(Integer.parseInt(text)));
                break;
            case "Update Passenger's data":
                UpdatePassenger update__ = new UpdatePassenger();
                update__.setLocationRelativeTo(null);
                update__.setVisible(true);
                break;
        }
    }

    /**
     * This is the inner class of PassengerMenu to implement Add Passenger
     * button
     */
    public class AddPassenger extends JFrame {

        JLabel heading1 = new JLabel("Enter Passenger's data");
        JButton add_record = new JButton("Add record");
        JTextField _id = new JTextField("Enter Passenger's ID\t");
        JTextField _name = new JTextField("Enter Passenger's Name");
        JTextField _cnic = new JTextField("Enter Passenegr's CNIC");
        JTextField _mob = new JTextField("Enter Passenger's Mobile no");
        JButton close1 = new JButton("Close");

        /**
         * This is the default constructor of inner class AddPassenger
         */
        public AddPassenger() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(400, 400);
            heading1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            add(heading1, BorderLayout.NORTH);
            JPanel jp = new JPanel();
            jp.setLayout(new GridLayout(5, 1, 50, 50));
            jp.add(_id);
            jp.add(_name);
            jp.add(_cnic);
            jp.add(_mob);
            add(jp, BorderLayout.WEST);
            JPanel jp1 = new JPanel(new FlowLayout());
            jp1.add(add_record);
            jp1.add(close1);
            ActionListener close1_ = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            };
            close1.addActionListener(close1_);
            add(jp1, BorderLayout.SOUTH);
            MouseAdapter m_id = new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    _id.setText(null);
                    int id_c = 0;
                    while (true) {
                        try {
                            String c = JOptionPane.showInputDialog("Enter ID:");
                            id_c = Integer.parseInt(c);
                            break;
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Invalid ID. Enter new ID");
                        }
                    }
                    if (Passengers.checkID(id_c) == true) {
                        _id.setText(Integer.toString(id_c));
                    } else if (Passengers.checkID(id_c) == false) {
                        JOptionPane.showMessageDialog(null, "ID already exists. Enter another ID");
                    }
                }
            };
            MouseAdapter m_name = new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    _name.setText(null);
                    _name.setText(JOptionPane.showInputDialog("Enter name:"));
                }
            };
            MouseAdapter m_cnic = new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    _cnic.setText(null);
                    _cnic.setText(JOptionPane.showInputDialog("Enter cnic:"));
                }
            };
            MouseAdapter m_mob = new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    _mob.setText(null);
                    _mob.setText(JOptionPane.showInputDialog("Enter mobile no:"));
                }
            };
            _id.addMouseListener(m_id);
            _name.addMouseListener(m_name);
            _cnic.addMouseListener(m_cnic);
            _mob.addMouseListener(m_mob);
            ActionListener addRecord = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Passengers p = new Passengers();
                    p.setID(Integer.parseInt(_id.getText()));
                    p.setName(_name.getText());
                    p.setCnic(_cnic.getText());
                    p.setMob(_mob.getText());
                    p.add();
                    _id.setText("Enter Passenger's ID\t");
                    _name.setText("Enter Passenger's Name");
                    _cnic.setText("Enter Passenegr's CNIC");
                    _mob.setText("Enter Passenger's Mobile no");
                }
            };
            add_record.addActionListener(addRecord);

        }
    }

    /**
     * This is the inner class of PassengerMenu to implement View Passenger
     * button
     */
    public class ViewPassenger extends JFrame {

        JLabel heading1 = new JLabel("Passenger's data");
        JButton close1 = new JButton("Close");
        JTextArea text_area = new JTextArea();
        DefaultTableModel tableModel;
        JTable table;

        /**
         * This is the default constructor of the inner class ViewPassenger
         */
        public ViewPassenger() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(500, 500);
            heading1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            add(heading1, BorderLayout.NORTH);
            add(close1, BorderLayout.SOUTH);
            ActionListener close1_ = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            };
            close1.addActionListener(close1_);
            String[] column = {"ID", "Name", "CNIC", "Mobile No"};
            tableModel = new DefaultTableModel(column, 0);
            table = new JTable(tableModel);
            JPanel jp = new JPanel();
            jp.add(table);
            jp.add(new JScrollPane(table));
            add(jp, BorderLayout.WEST);
            ArrayList<Passengers> a = Passengers.viewPassenger();
            Object[] obj1 = new Object[4];
            for (int i = 0; i < a.size(); i++) {
                obj1[0] = a.get(i).getID();
                obj1[1] = a.get(i).getName();
                obj1[2] = a.get(i).getCnic();
                obj1[3] = a.get(i).getMob();
                tableModel.addRow(obj1);
            }
        }
    }

    /**
     * This is the inner class of PassengerMenu to implement Search Passenger by
     * ID button
     */
    public class SearchPassenger extends JFrame {

        JLabel heading1 = new JLabel("Passenger searched by ID");
        JTextArea text_area = new JTextArea();
        JButton close1 = new JButton("Close");

        /**
         * This is the default constructor of inner class SearchPassenger
         */
        public SearchPassenger() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(800, 600);
            heading1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            add(heading1, BorderLayout.NORTH);
            add(close1, BorderLayout.SOUTH);
            ActionListener close1_ = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            };
            close1.addActionListener(close1_);
            int id_c = 0;
            while (true) {
                String s = JOptionPane.showInputDialog("Enter ID to search:");
                try {
                    id_c = Integer.parseInt(s);
                    break;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID. Enter new ID");
                }
            }
            Passengers obj = new Passengers();
            obj = obj.search(id_c);
            text_area.setText(obj.toString());
            add(text_area, BorderLayout.CENTER);
        }
    }

    /**
     * This is the inner class of PassengerMenu to implement search passenger by
     * name button
     */
    public class SearchPassengerN extends JFrame {

        JLabel heading1 = new JLabel("Passenger searched by name");
        JTextArea text_area = new JTextArea();
        JButton close1 = new JButton("Close");

        /**
         * This is the default constructor of the inner class SearchPassengerN
         */
        public SearchPassengerN() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(800, 600);
            heading1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            add(heading1, BorderLayout.NORTH);
            add(close1, BorderLayout.SOUTH);
            ActionListener close1_ = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            };
            close1.addActionListener(close1_);
            String s = JOptionPane.showInputDialog("Enter name to search:");
            text_area.setText(Passengers.searchPassenger(s));
            add(text_area, BorderLayout.CENTER);
        }
    }

    /**
     * This is the inner class of PassengerMenu to implement Update Passenger
     * button
     */
    public class UpdatePassenger extends JFrame {

        JLabel heading1 = new JLabel("Update record of the passenger");
        JButton update_name = new JButton("Update name");
        JButton update_cnic = new JButton("Update cnic");
        JButton update_mob = new JButton("Update mobile no");
        JButton close1 = new JButton("Close");

        /**
         * This is the default constructor of the inner class UpdatePassenger
         */
        public UpdatePassenger() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(300, 300);
            heading1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            add(heading1, BorderLayout.NORTH);
            JPanel jp = new JPanel();
            jp.setLayout(new FlowLayout());
            jp.add(update_name);
            jp.add(update_cnic);
            jp.add(update_mob);
            add(jp, BorderLayout.CENTER);
            add(close1, BorderLayout.SOUTH);
            ActionListener close1_ = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            };
            close1.addActionListener(close1_);
            String s = JOptionPane.showInputDialog("Enter ID to update:");
            int uid = Integer.parseInt(s);
            Passengers obj = new Passengers();
            ActionListener u_name = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String messege = JOptionPane.showInputDialog("Enter name: ");
                    JOptionPane.showMessageDialog(null, obj.update(uid, messege));
                }
            };
            update_name.addActionListener(u_name);
            ActionListener u_cnic = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String messege = JOptionPane.showInputDialog("Enter cnic: ");
                    JOptionPane.showMessageDialog(null, obj.update(uid, messege));
                }
            };
            update_cnic.addActionListener(u_cnic);
            ActionListener u_mob = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String messege = JOptionPane.showInputDialog("Enter mobile no: ");
                    JOptionPane.showMessageDialog(null, obj.update(uid, messege));
                }
            };
            update_mob.addActionListener(u_mob);
        }
    }
}
