package daewoo.management.system.gui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * This is the class bus which perform functions on bus file
 *
 */
public class Buses extends Slots {

    /**
     * this is static file object
     */
    public static File file = new File("buses.txt");

    /**
     * This is static scanner object
     */
    public static Scanner input = new Scanner(System.in);
    private int b_id;
    private int d_id;
    private int slot_id;
    private String d_name;
    private Slots s = new Slots();
    private SalariedDriver sd = new SalariedDriver();

    /**
     * This is overloaded constructor of the class
     *
     * @param b_id
     * @param slot_id
     * @param d_name
     */
    public Buses(int b_id, int slot_id, int d_id) {
        this.b_id = b_id;
        this.sd = SalariedDriver.searchDriver(d_id);
        this.s = Slots.searchSlot(slot_id);
    }

    public Buses(int b_id, int slot_id, String name) {
        this.b_id = b_id;
        this.slot_id = slot_id;
        this.d_name = name;
    }

    /**
     * This is the default constructor of the class Buses
     */
    public Buses() {
        b_id = 0;
        d_id = 0;
        slot_id = 0;
    }

    /**
     * This is the mutator method to set the value of bus id
     *
     * @param b_id
     */
    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    /**
     * This is the mutator method to set the name of the driver
     *
     * @param d_name
     */
    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    /**
     * This is the mutator method to set the value of slot id
     *
     * @param slot_id
     */
    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    /**
     * This is the accessors method to get the id of the bus
     *
     * @return integer
     */
    public int getB_id() {
        return b_id;
    }

    /**
     * This is the accessors method to get the name of the driver
     *
     * @return String
     */
    public String getD_name() {
        return d_name;
    }

    /**
     * This is the accessors method to get the id if the slot
     *
     * @return integer
     */
    public int getSlot_id() {
        return slot_id;
    }

    @Override
    public String toString() {
        return (b_id + "\t" + s.getId() + "\t" + sd.getdName());
    }

    /**
     * This is the static function to check that if bus id is already present in
     * the file or not
     *
     * @param ID
     * @return boolean
     */
    public static boolean checkID(int ID) {
        Scanner read = null;
        boolean check = true;
        if (file.exists()) {
            try {
                read = new Scanner(file);
            } catch (IOException e) {
                System.out.println(e);
            }
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] token = line.split("\\s+");
                int s1_id = (Integer.parseInt(token[0]));
                if (ID == s1_id) {
                    check = false;
                    break;
                } else {
                    check = true;
                }
            }
            read.close();
        }
        return check;
    }

    public void addBus() {
        PrintWriter pw = null;
        if (file.exists()) {
            try {
                pw = new PrintWriter(new FileWriter(file.getAbsolutePath(), true));
            } catch (IOException e) {
                System.out.println(e);
            }
            pw.println(toString());
            pw.close();
        } else {
            System.out.println("File does not exists");
        }
    }

    /**
     * This is the static method to view the data of the file bus
     *
     * @return String
     */
    public static ArrayList viewBus() {
        Buses b = new Buses();
        ArrayList<Buses> a = new ArrayList<>();
        Scanner read = null;
        if (file.exists()) {
            try {
                read = new Scanner(file);
            } catch (IOException e) {
                System.out.println(e);
            }
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] token = line.split("\\s+");
                b.setB_id(Integer.parseInt(token[0]));
                b.setSlot_id(Integer.parseInt(token[1]));
                b.setD_name(token[2]);
                a.add(new Buses(b.getB_id(), b.getSlot_id(), b.getD_name()));
            }
        } else {
            System.out.println("\nFile does not exist");
            System.out.println("Enter Data for Bus first");
        }
        return a;
    }

    /**
     * This is the static method to search for the bus by taking integer as a
     * parameter and returns the object of the Buses class
     *
     * @param s_id
     * @return Buses
     */
    public static Buses searchBus(int s_id) {
        Buses b = new Buses();
        Scanner read = null;
        boolean check = true;
        String line = null;
        if (file.exists()) {
            try {
                read = new Scanner(file);
                while (read.hasNextLine()) {
                    line = read.nextLine();
                    String[] token = line.split("\\s+");
                    int s1_id = (Integer.parseInt(token[0]));
                    if (s1_id == s_id) {
                        b.setB_id(Integer.parseInt(token[0]));
                        b.setSlot_id(Integer.parseInt(token[1]));
                        b.setD_name(token[2]);
                        check = false;
                        break;
                    }
                }
                read.close();
                if (check == true) {
                    line = "No record found";
                    JOptionPane.showMessageDialog(null, line);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("File does not exist");
            System.out.println("Enter Data for Bus first\n");
        }
        return b;
    }
}
