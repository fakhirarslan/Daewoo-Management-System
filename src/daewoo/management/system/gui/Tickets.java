package daewoo.management.system.gui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * This is the class Tickets which perform operations on the file and store data
 * regarding the tickets
 *
 */
public class Tickets implements commonInterface {

    /**
     * This is the static File object only referenced
     */
    public static File file;

    /**
     *
     */
    public static Scanner read;
    private int t_id;
    private int seat_no;
    private static int count = 0;
    private String name;
    private int bus_id;
    private double fare;
    private Passengers p = new Passengers();
    private Buses b = new Buses();

    /**
     * This is the overloaded constructor of the class Tickets taking six
     * variables as a parameter
     *
     * @param t_id
     * @param p_id
     * @param b_id
     * @param seat_no
     * @param city
     * @param discount
     */
    public Tickets(int t_id, int p_id, int b_id, int seat_no, String city, String discount) {
        file = new File("tickets" + b_id + ".txt");
        checkfile();
        this.t_id = t_id;
        this.p = p.search(p_id);
        this.b = Buses.searchBus(b_id);
        this.seat_no = seat_no;
        if (city.equalsIgnoreCase("Lahore")) {
            if (discount.equalsIgnoreCase("promo40")) {
                setFare(2500.0 - (2500.0 * (40.0 / 100.0)));
            } else {
                setFare(2500);
            }
        } else if (city.equalsIgnoreCase("Multan")) {
            if (discount.equalsIgnoreCase("promo40")) {
                setFare(3500.0 - (2500.0 * (40.0 / 100.0)));
            } else {
                setFare(3500);
            }
        }
    }

    /**
     * This is another overloaded constructor of the class Tickets taking five
     * variables as a parameter
     *
     * @param ID
     * @param name
     * @param id
     * @param seat_no
     * @param fare
     */
    public Tickets(int ID, String name, int id, int seat_no, Double fare) {
        this.t_id = ID;
        this.name = name;
        this.bus_id = id;
        this.seat_no = seat_no;
        this.fare = fare;
    }

    /**
     * This is the default constructor of the class Tickets setting default
     * values to instance variables
     */
    public Tickets() {
        this.t_id = 0;
        this.name = null;
        this.bus_id = 0;
        this.seat_no = 0;
        this.fare = 0;
    }

    /**
     * This is the mutator method to set the value of the id ticket
     *
     * @param t_id
     */
    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    /**
     * This is the mutator method to set the name of the passenger to whom
     * ticket is assigned
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This is the mutator method to set the value of the id bus
     *
     * @param bus_id
     */
    public void setBus_id(int bus_id) {
        this.bus_id = bus_id;
    }

    /**
     * This is the mutator method to set the value of the seat no of the bus
     *
     * @param seat_no
     */
    public void setSeat_no(int seat_no) {
        this.seat_no = seat_no;
    }

    /**
     * This is the mutator method to set the value of the fare
     *
     * @param fare
     */
    public void setFare(double fare) {
        this.fare = fare;
    }

    /**
     * This is the accessors method to get the id of the ticket
     *
     * @return integer
     */
    public int getT_id() {
        return t_id;
    }

    /**
     * This is the accessors method to get the name of the passenger to whom the
     * ticket is assigned
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * This is the accessors method to get the id of the bus
     *
     * @return integer
     */
    public int getBus_id() {
        return bus_id;
    }

    /**
     * This is the accessors method to get the seat no of the bus
     *
     * @return integer
     */
    public int getSeat_no() {
        return seat_no;
    }

    /**
     * This is the accessors method to get the fare
     *
     * @return double
     */
    public double getFare() {
        return fare;
    }

    @Override
    public String toString() {
        return (t_id + "\t" + p.getName() + "\t" + b.getB_id() + "\t" + seat_no + "\t" + fare);
    }

    /**
     * This is the static method to check that the id of the tickets is already
     * present if the file or not
     *
     * @param ID
     * @return boolean
     */
    public static boolean checkID(int ID) {
        Scanner read = null;
        boolean check = true;
        for (int file_no = 1; file_no <= 7; file_no++) {
            if (check == true) {
                file = new File("tickets" + file_no + ".txt");
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
            }
        }
        return check;
    }

    /**
     * This is the interface method to add the ticket against some passenger
     */
    public void add() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(file.getAbsolutePath(), true));
        } catch (IOException e) {
            System.out.println(e);
        }
        pw.println(toString());
        pw.close();
        count++;
    }

    /**
     * This is the static method which checks that promo code exists or not
     *
     * @param read
     * @return boolean
     */
    public static boolean checkDiscount(String read) {
        String discount = "PROMO40";
        if (read.compareToIgnoreCase(discount) == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This is the static method to check the seat availability
     *
     * @param seat
     * @param b_id
     * @return boolean
     */
    public static boolean checkSeat(int seat, int b_id) {
        Scanner check = null;
        boolean seat_check = false;
        if (b_id == 1 || b_id == 2 || b_id == 3 || b_id == 4 || b_id == 5 || b_id == 6 || b_id == 7) {
            File file1 = new File("tickets" + b_id + ".txt");
            if (file1.exists()) {
                try {
                    check = new Scanner(file1);
                } catch (IOException e) {
                    System.out.println(e);
                }
                while (check.hasNextLine()) {
                    String line = check.nextLine();
                    String[] token = line.split("\\s+");
                    int seat_to_check = Integer.parseInt(token[3]);
                    if (seat == seat_to_check) {
                        seat_check = true;
                        break;
                    }
                }
                check.close();
            }
        }
        return seat_check;
    }

    /**
     * This is the static method to check the number of lines to get the seat
     * availability
     *
     * @param bus_id
     * @return integer
     */
    public static int checkcount(int bus_id) {
        File file1 = new File("tickets" + bus_id + ".txt");
        if (file1.exists()) {
            try {
                read = new Scanner(file1);
            } catch (IOException e) {
                System.out.println(e);
            }
            while (read.hasNext()) {
                read.nextLine();
                count++;
            }
            read.close();
        }
        return count;
    }

    private void checkfile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * This is the static method which views the data of the tickets returning
     * ArrayList
     *
     * @return ArrayList
     */
    public static ArrayList viewTicket() {
        Tickets t = new Tickets();
        ArrayList<Tickets> arrayList = new ArrayList<>();
        Scanner check = null;
        String data = null;
        File file1;
        for (int file_no = 1; file_no <= 7; file_no++) {
            file1 = new File("tickets" + file_no + ".txt");
            if (file1.exists()) {
                try {
                    check = new Scanner(file1);
                } catch (IOException e) {
                    System.out.println(e);
                }
                while (check.hasNextLine()) {
                    String line = check.nextLine();
                    String[] token = line.split("\\s+");
                    t.setT_id(Integer.parseInt(token[0]));
                    t.setName(token[1]);
                    t.setBus_id(Integer.parseInt(token[2]));
                    t.setSeat_no(Integer.parseInt(token[3]));
                    t.setFare(Double.parseDouble(token[4]));
                    arrayList.add(new Tickets(t.getT_id(), t.getName(), t.getBus_id(), t.getSeat_no(), t.getFare()));
                }
                check.close();
            }
        }
        return arrayList;
    }

    /**
     * This is the static method to check the route is available or not to the
     * specific city
     *
     * @param city
     * @return Slots[]
     */
    public static Slots[] checkRoute(String city) {
        File file1 = new File("slots.txt");
        int count1 = 0;
        Scanner check = null;
        try {
            check = new Scanner(file1);
        } catch (IOException e) {
            System.out.println(e);
        }
        while (check.hasNext()) {
            String line = check.nextLine();
            String[] token = line.split("\\s+");
            if (city.compareToIgnoreCase(token[2]) == 0) {
                count1++;
            } else {
            }
        }
        check.close();
        Slots[] s = new Slots[count1];
        try {
            check = new Scanner(file1);
        } catch (IOException e) {
            System.out.println(e);
        }
        int j = 0;
        while (check.hasNext()) {
            String line = check.nextLine();
            String[] token = line.split("\\s+");
            if (city.equalsIgnoreCase(token[2])) {
                s[j] = new Slots();
                s[j].setId(Integer.parseInt(token[0]));
                s[j].setDeparture_city(token[1]);
                s[j].setArrival_city(token[2]);
                s[j].setDeparture(token[3]);
                s[j].setArrival(token[4]);
                j++;
            }
        }
        return s;
    }

    /**
     * This is the static method to cancel the ticket of the passenger
     *
     * @param ticket_no
     */
    public static void cancelTicket(int ticket_no) {
        Scanner check = null;
        File file1;
        File temp = new File("temp.txt");
        boolean data_check = false;
        for (int i = 1; i <= 7; i++) {
            file1 = new File("tickets" + i + ".txt");
            if (file1.exists()) {
                try {
                    check = new Scanner(file1);
                } catch (IOException e) {
                    System.out.println(e);
                }
                while (check.hasNextLine()) {
                    String line = check.nextLine();
                    String[] token = line.split("\\s+");
                    if (ticket_no == Integer.parseInt(token[0])) {
                        JOptionPane.showMessageDialog(null, "cancelled " + token[1] + " ticket successfully");
                        data_check = true;
                    } else {
                        PrintWriter pw = null;
                        try {
                            pw = new PrintWriter(new FileWriter(temp, true));
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                        pw.println(line);
                        pw.close();
                    }
                }
                check.close();
                file1.delete();
                temp.renameTo(file1);
            }
        }
        if (data_check == false) {
            JOptionPane.showMessageDialog(null, "No Ticket found on " + ticket_no + " ID");
        }
    }

    @Override
    public Object search(int s_id) {
        Tickets t = new Tickets();
        return t;
    }

    @Override
    public String delete(int s_id) {
        String s = "";
        return s;
    }
}
