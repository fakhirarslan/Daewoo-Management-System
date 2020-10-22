package daewoo.management.system.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This is the GUI class of slots menu
 *
 */
public class SlotMenu extends JFrame implements ActionListener {

    JLabel heading = new JLabel("Slot Menu");
    JButton _view = new JButton("View Slot");
    JButton _search = new JButton("Search Slot");
    JButton close = new JButton("Close");

    /**
     * This is the default constructor of the class SlotMenu
     */
    public SlotMenu() {
        super("Daewoo Management System");
        setLayout(new BorderLayout());
        setSize(400, 400);
        add(heading, BorderLayout.NORTH);
        JPanel jp = new JPanel(new FlowLayout());
        jp.add(_view);
        jp.add(_search);
        add(jp, BorderLayout.CENTER);
        _view.addActionListener(this);
        _search.addActionListener(this);
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
            case "View Slot":
                ViewSlot viewSlot = new ViewSlot();
                viewSlot.setLocationRelativeTo(null);
                viewSlot.setVisible(true);
                break;
            case "Search Slot":
                SearchSlot searchSlot = new SearchSlot();
                searchSlot.setLocationRelativeTo(null);
                searchSlot.setVisible(true);
                break;
        }
    }

    /**
     * This is the inner class ViewSlot to implement view slot button
     */
    public class ViewSlot extends JFrame {

        JLabel heading1 = new JLabel("Slot's data");
        JButton close1 = new JButton("Close");
        JTextArea text_area = new JTextArea();

        /**
         * This is the default constructor of the inner class ViewSlot
         */
        public ViewSlot() {
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
            String[] column = {"Slot ID", "Departure City", "Arrival City", "Departure Time", "Arrival Time", "Fares"};
            DefaultTableModel table = new DefaultTableModel(column, 0);
            JTable tablej = new JTable(table);
            JPanel p = new JPanel();
            add(p, BorderLayout.CENTER);
            p.add(tablej);
            p.add(new JScrollPane(tablej));
            ArrayList<Slots> a = Slots.viewSlot();
            Object[] obj = new Object[6];
            for (int i = 0; i < a.size(); i++) {
            obj[0]=a.get(i).getId();
            obj[1]=a.get(i).getDeparture_city();
            obj[2]=a.get(i).getArrival_city();
            obj[3]=a.get(i).getDeparture();
            obj[4]=a.get(i).getArrival();
            obj[5]=a.get(i).getFares();
            table.addRow(obj);
            }
        }
    }

    /**
     * This is the inner class SearchSlot to implement search slot button
     */
    public class SearchSlot extends JFrame {

        JLabel heading1 = new JLabel("Slot searched by ID");
        JTextArea text_area = new JTextArea();
        JButton close1 = new JButton("Close");

        /**
         * This is the default constructor of the inner class SearchSlot
         */
        public SearchSlot() {
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
            Slots obj = Slots.searchSlot(id_c);
            text_area.setText(obj.toString());
            add(text_area, BorderLayout.CENTER);
        }
    }
}
