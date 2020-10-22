package daewoo.management.system.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is the GUI class of the drivers that are paid weekly
 *
 */
public class WeeklyMenu extends JFrame implements ActionListener {

    JLabel heading = new JLabel("Weekly salaried Drivers");
    JButton add_ = new JButton("Add Driver");
    JButton view = new JButton("View Driver Data");
    JButton s_id = new JButton("Search Driver by ID");
    JButton s_name = new JButton("Search Driver by name");
    JButton delete = new JButton("Delete Driver's data");
    JButton update = new JButton("Update Driver's data");
    JButton close = new JButton("Close");

    /**
     * This is the default constructor of the class WeeklyDriver
     */
    public WeeklyMenu() {
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
            @Override
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
            case "Add Driver":
                AddDriver add__ = new AddDriver();
                add__.setLocationRelativeTo(null);
                add__.setVisible(true);
                break;
            case "View Driver Data":
                ViewDriver view_ = new ViewDriver();
                view_.setLocationRelativeTo(null);
                view_.setVisible(true);
                break;
            case "Search Driver by ID":
                SearchDriver search__ = new SearchDriver();
                search__.setLocationRelativeTo(null);
                search__.setVisible(true);
                break;
            case "Search Driver by name":
                SearchDriverN search_name = new SearchDriverN();
                search_name.setLocationRelativeTo(null);
                search_name.setVisible(true);
                break;
            case "Delete Driver's data":
                text = JOptionPane.showInputDialog("Enter ID to delete:");
                JOptionPane.showMessageDialog(null, WeeklyDriver.deleteDriver(Integer.parseInt(text)));
                break;
            case "Update Driver's data":
                UpdateDriver update__ = new UpdateDriver();
                update__.setLocationRelativeTo(null);
                update__.setVisible(true);
                break;
        }
    }

    /**
     * This is the inner class AddDriver implementing the add driver button
     */
    public class AddDriver extends JFrame {

        JLabel heading1 = new JLabel("Enter Driver's data");
        JButton add_record = new JButton("Add record");
        JTextField _id = new JTextField("Enter Driver's ID\t");
        JTextField _name = new JTextField("Enter Driver's Name");
        JTextField _mob = new JTextField("Enter Driver's Mobile no");
        JTextField _salary = new JTextField("Enter Driver's weekly salary");
        JTextField _weeks = new JTextField("Enter number of weeks");
        JButton close1 = new JButton("Close");

        /**
         * This is the default constructor of the class AddDriver
         */
        public AddDriver() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(400, 400);
            heading1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            add(heading1, BorderLayout.NORTH);
            JPanel jp = new JPanel();
            jp.setLayout(new GridLayout(6, 1, 20, 20));
            jp.add(_id);
            jp.add(_name);
            jp.add(_mob);
            jp.add(_salary);
            jp.add(_weeks);
            add(jp, BorderLayout.CENTER);
            add(add_record, BorderLayout.EAST);
            MouseAdapter m_id = new MouseAdapter() {
                @Override
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
                    if (WeeklyDriver.checkID(id_c) == true) {
                        _id.setText(Integer.toString(id_c));
                    } else if (WeeklyDriver.checkID(id_c) == false) {
                        JOptionPane.showMessageDialog(null, "ID already exists. Enter another ID");
                    }
                }
            };
            MouseAdapter m_name = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    _name.setText(null);
                    _name.setText(JOptionPane.showInputDialog("Enter name:"));
                }
            };
            MouseAdapter m_mob = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    _mob.setText(null);
                    _mob.setText(JOptionPane.showInputDialog("Enter mobile no:"));
                }
            };
            MouseAdapter m_salary = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    _salary.setText(null);
                    _salary.setText(JOptionPane.showInputDialog("Enter weekly salary:"));
                }
            };
            MouseAdapter m_weeks = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    _weeks.setText(null);
                    String c = JOptionPane.showInputDialog("Enter number of weeks:");
                    int c_w = 0;
                    try {
                        c_w = Integer.parseInt(c);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Input. Enter again");
                    }
                    _weeks.setText(Integer.toString(c_w));
                }
            };
            _id.addMouseListener(m_id);
            _name.addMouseListener(m_name);
            _mob.addMouseListener(m_mob);
            _salary.addMouseListener(m_salary);
            _weeks.addMouseListener(m_weeks);
            ActionListener addRecord = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    WeeklyDriver d = new WeeklyDriver();
                    d.setdID(Integer.parseInt(_id.getText()));
                    d.setdName(_name.getText());
                    d.setdMob(_mob.getText());
                    d.setwpay(Double.parseDouble(_salary.getText()));
                    d.setn_o_w(Integer.parseInt(_weeks.getText()));
                    d.add();
                    _id.setText("Enter Driver's ID\t");
                    _name.setText("Enter Driver's Name");
                    _mob.setText("Enter Driver's Mobile no");
                    _salary.setText("Enter Driver's salary");
                    _weeks.setText("Enter number of weeks");
                }
            };
            add_record.addActionListener(addRecord);
            add(close1, BorderLayout.SOUTH);
            ActionListener close1_ = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            };
            close1.addActionListener(close1_);
        }
    }

    /**
     * This is the inner class ViewDriver to implement the view driver button
     */
    public class ViewDriver extends JFrame {

        JLabel heading1 = new JLabel("Weekly Driver's data");
        JButton close1 = new JButton("Close");
        JTextArea text_area = new JTextArea();

        /**
         * This is the default constructor of the class ViewDriver
         */
        public ViewDriver() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(800, 600);
            heading1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            add(heading1, BorderLayout.NORTH);
            add(close1, BorderLayout.SOUTH);
            ActionListener close1_ = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            };
            close1.addActionListener(close1_);
            add(text_area, BorderLayout.CENTER);
            text_area.setText("ID\tName\t\tMobile No\t\tWeekly salary\n\n" + WeeklyDriver.viewDriver());
        }
    }

    /**
     * This is the inner class SearchDriver implementing the search driver by id
     * button
     */
    public class SearchDriver extends JFrame {

        JLabel heading1 = new JLabel("Driver searched by ID");
        JTextArea text_area = new JTextArea();
        JButton close1 = new JButton("Close");

        /**
         * This is the default constructor of the class SearchDriver
         */
        public SearchDriver() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(800, 600);
            heading1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            add(heading1, BorderLayout.NORTH);
            add(close1, BorderLayout.SOUTH);
            ActionListener close1_ = new ActionListener() {
                @Override
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
            text_area.setText(WeeklyDriver.searchDriver(id_c));
            add(text_area, BorderLayout.CENTER);
        }
    }

    /**
     * This is the inner class SearchDriverN implementing the search driver by
     * name button
     */
    public class SearchDriverN extends JFrame {

        JLabel heading1 = new JLabel("Driver searched by name");
        JTextArea text_area = new JTextArea();
        JButton close1 = new JButton("Close");

        /**
         * This is the default constructor of the class SearchDiverN
         */
        public SearchDriverN() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(800, 600);
            heading1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            add(heading1, BorderLayout.NORTH);
            add(close1, BorderLayout.SOUTH);
            ActionListener close1_ = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            };
            close1.addActionListener(close1_);
            String s = JOptionPane.showInputDialog("Enter name to search:");
            text_area.setText(WeeklyDriver.searchDriver(s));
            add(text_area, BorderLayout.CENTER);
        }
    }

    /**
     * This is the inner class UpdateDriver implementing the update driver
     * button
     */
    public class UpdateDriver extends JFrame {

        JLabel heading1 = new JLabel("Update record of the driver");
        JButton update_name = new JButton("Update name");
        JButton update_mob = new JButton("Update mobile no");
        JButton update_salary = new JButton("Update salary");
        JButton close1 = new JButton("Close");

        /**
         * This is the default constructor of the class UpdateDriver
         */
        public UpdateDriver() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(300, 300);
            heading1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
            add(heading1, BorderLayout.NORTH);
            JPanel jp = new JPanel();
            jp.setLayout(new FlowLayout());
            jp.add(update_name);
            jp.add(update_mob);
            jp.add(update_salary);
            add(jp, BorderLayout.CENTER);
            add(close1, BorderLayout.SOUTH);
            ActionListener close1_ = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            };
            close1.addActionListener(close1_);
            String s = JOptionPane.showInputDialog("Enter ID to update:");
            int uid = Integer.parseInt(s);
            ActionListener u_name = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String messege = JOptionPane.showInputDialog("Enter name: ");
                    JOptionPane.showMessageDialog(null, WeeklyDriver.updateDriver(uid, messege));
                }
            };
            update_name.addActionListener(u_name);
            ActionListener u_mob = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String messege = JOptionPane.showInputDialog("Enter mobile no: ");
                    JOptionPane.showMessageDialog(null, WeeklyDriver.updateDriver(uid, messege));
                }
            };
            update_mob.addActionListener(u_mob);
            ActionListener u_salary = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String messege = JOptionPane.showInputDialog("Enter weekly salary: ");
                    JOptionPane.showMessageDialog(null, WeeklyDriver.updateDriver(uid, messege));
                }
            };
            update_salary.addActionListener(u_salary);
        }
    }
}
