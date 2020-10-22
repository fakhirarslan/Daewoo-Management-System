package daewoo.management.system.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This is the GUI class for Bus Menu
 *
 */
public class BusMenu extends JFrame implements ActionListener {

    JLabel heading = new JLabel("Buses Menu");
    JButton _add = new JButton("Add Bus");
    JButton _view = new JButton("View Buses");
    JButton _search = new JButton("Search Buses");
    JButton close = new JButton("Close");

    /**
     * This is the default constructor of the class BusMenu
     */
    public BusMenu() {
        super("Daewoo Management System");
        setLayout(new BorderLayout());
        setSize(400, 400);
        add(heading, BorderLayout.NORTH);
        JPanel jp = new JPanel(new FlowLayout());
        jp.add(_add);
        jp.add(_view);
        jp.add(_search);
        add(jp, BorderLayout.CENTER);
        _add.addActionListener(this);
        _view.addActionListener(this);
        _search.addActionListener(this);
        add(close, BorderLayout.SOUTH);
        ActionListener close_ = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
        close.addActionListener(close_);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        String text = null;
        switch (s) {
            case "Add Bus":
                AddBus addBus = new AddBus();
                addBus.setLocationRelativeTo(null);
                addBus.setVisible(true);
                break;
            case "View Buses":
                ViewBus viewBus = new ViewBus();
                viewBus.setLocationRelativeTo(null);
                viewBus.setVisible(true);
                break;
            case "Search Buses":
                SearchBus searchBus = new SearchBus();
                searchBus.setLocationRelativeTo(null);
                searchBus.setVisible(true);
                break;
        }
    }

    public class AddBus extends JFrame {

        JLabel heading1 = new JLabel("Add Bus data");
        JLabel bus_id_label = new JLabel("Enter Bus ID here");
        JTextField bus_id = new JTextField();
        JLabel slot_id_label = new JLabel("Enter Slot ID here");
        JTextField slot_id = new JTextField();
        JLabel driver_id_label = new JLabel("Enter Driver ID here");
        JTextField driver_id = new JTextField();
        JButton add_button = new JButton("Add Bus");
        JButton _close = new JButton("Close");

        public AddBus() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(400, 400);
            add(heading1, BorderLayout.NORTH);
            JPanel jp = new JPanel(new GridLayout(5, 2, 100, 20));
            jp.add(bus_id_label);
            jp.add(bus_id);
            jp.add(slot_id_label);
            jp.add(slot_id);
            jp.add(driver_id_label);
            jp.add(driver_id);
            add(jp, BorderLayout.CENTER);
            JPanel jp1 = new JPanel(new FlowLayout());
            jp1.add(add_button);
            jp1.add(_close);
            add(jp1, BorderLayout.SOUTH);
            ActionListener _add = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Buses b = new Buses(Integer.parseInt(bus_id.getText()), Integer.parseInt(slot_id.getText()), Integer.parseInt(driver_id.getText()));
                    b.addBus();
                }
            };
            add_button.addActionListener(_add);
            ActionListener _close_ = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            };
            _close.addActionListener(_close_);
        }
    }

    /**
     * This is the inner class ViewBus implementing view bus button
     */
    public class ViewBus extends JFrame {

        JLabel heading1 = new JLabel("Bus's data");
        JButton close1 = new JButton("Close");
        JTextArea text_area = new JTextArea();

        /**
         * This is the default constructor of the inner class ViewBus
         */
        public ViewBus() {
            super("Daewoo Management System");
            setLayout(new BorderLayout());
            setSize(500, 500);
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
            String[] column = {"Bus ID", "Slot ID", "Driver's name"};
            DefaultTableModel tableModel = new DefaultTableModel(column, 0);
            JTable table = new JTable(tableModel);
            JPanel jp = new JPanel();
            jp.add(table);
            jp.add(new JScrollPane(table));
            add(jp, BorderLayout.WEST);
            ArrayList<Buses> a = Buses.viewBus();
            Object[] obj1 = new Object[3];
            for (int i = 0; i < a.size(); i++) {
                obj1[0] = a.get(i).getB_id();
                obj1[1] = a.get(i).getSlot_id();
                obj1[2] = a.get(i).getD_name();
                tableModel.addRow(obj1);
            }
        }
    }

    /**
     * This is the inner class SearchBus to implement search bus button
     */
    public class SearchBus extends JFrame {

        JLabel heading1 = new JLabel("Bus searched by ID");
        JTextArea text_area = new JTextArea();
        JButton close1 = new JButton("Close");

        /**
         * This is the default constructor of the inner class SearchBus
         */
        public SearchBus() {
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
            Buses obj = Buses.searchBus(id_c);
            text_area.setText(obj.toString());
            add(text_area, BorderLayout.CENTER);
        }
    }
}
