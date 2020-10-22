package daewoo.management.system.gui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the class Slots which performs function on the file slots
 *
 */
public class Slots {

    /**
     * This is the static File object
     */
    public static File file = new File("slots.txt");

    private int id;
    private String departure_city;
    private String arrival_city;
    private String departure;
    private String arrival;
    private double fares;

    /**
     * This is the overloaded constructor of the class Slots taking six
     * variables as the parameter
     *
     * @param id
     * @param departure_city
     * @param arrival_city
     * @param departure
     * @param arrival
     * @param fares
     */
    public Slots(int id, String departure_city, String arrival_city, String departure, String arrival, double fares) {
        this.id = id;
        this.departure_city = departure_city;
        this.arrival_city = arrival_city;
        this.departure = departure;
        this.arrival = arrival;
        this.fares = fares;
    }

    /**
     * This is the default constructor of the class Slots
     */
    public Slots() {
        id = 0;
        departure_city = null;
        arrival_city = null;
        departure = null;
        arrival = null;
        fares = 0;
    }

    /**
     * This is the mutator method to set the value of the slot id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This is the mutator method to set the departure city
     *
     * @param departure_city
     */
    public void setDeparture_city(String departure_city) {
        this.departure_city = departure_city;
    }

    /**
     * This is the mutator method to set the arrival city
     *
     * @param arrival_city
     */
    public void setArrival_city(String arrival_city) {
        this.arrival_city = arrival_city;
    }

    /**
     * This is the mutator method to set the departure time
     *
     * @param departure
     */
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    /**
     * This is the mutator method to set the arrival time
     *
     * @param arrival
     */
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    /**
     * This is the mutator method to set the fares
     *
     * @param fares
     */
    public void setFares(double fares) {
        this.fares = fares;
    }

    /**
     * This is the accessors method to get the id of the slot
     *
     * @return integer
     */
    public int getId() {
        return id;
    }

    /**
     * This is the accessors method to get the departure time of the slot
     *
     * @return String
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * This is the accessors method to get the arrival time of the slot
     *
     * @return String
     */
    public String getArrival() {
        return arrival;
    }

    /**
     * This is the accessors method to get the departure city of the slot
     *
     * @return String
     */
    public String getDeparture_city() {
        return departure_city;
    }

    /**
     * This is the accessors method to get the arrival city of the slot
     *
     * @return String
     */
    public String getArrival_city() {
        return arrival_city;
    }

    /**
     * This is the accessors method to get the fares
     *
     * @return double
     */
    public double getFares() {
        return fares;
    }

    @Override
    public String toString() {
        return (id + "\t" + departure_city + "\t\t" + arrival_city + "\t\t" + departure + "\t\t" + arrival + "\t" + fares);
    }

    /**
     * This is the check method to check the id of the slot already exists or
     * not
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

    /**
     * This is the static method to view the data of slots file
     *
     * @return String
     */
    public static ArrayList viewSlot() {
        Scanner read = null;
        Slots s = new Slots();
        ArrayList<Slots> a = new ArrayList<>();
        if (file.exists()) {
            try {
                read = new Scanner(file);
            } catch (IOException e) {
                System.out.println(e);
            }
            while (read.hasNext()) {
                String line = read.nextLine();
                String[] token = line.split("\\s+");
                s.setId(Integer.parseInt(token[0]));
                s.setDeparture_city(token[1]);
                s.setArrival_city(token[2]);
                s.setDeparture(token[3]);
                s.setArrival(token[4]);
                s.setFares(Double.parseDouble(token[5]));
                a.add(new Slots(s.getId(), s.getDeparture_city(), s.getArrival_city(), s.getDeparture(), s.getArrival(), s.getFares()));
            }
        } else {
            System.out.println("\nFile does not exist");
            System.out.println("Enter Data for Slot first");
        }
        return a;
    }

    /**
     * This is the static method to search for the specific slot from the file
     *
     * @param s_id
     * @return Slots
     */
    public static Slots searchSlot(int s_id) {
        Slots s = new Slots();
        Scanner read = null;
        boolean check = true;
        String line = null;
        if (file.exists()) {
            try {
                read = new Scanner(file);
            } catch (IOException e) {
                System.out.println(e);
            }
            while (read.hasNextLine()) {
                line = read.nextLine();
                String[] token = line.split("\\s+");
                int s1_id = (Integer.parseInt(token[0]));
                if (s1_id == s_id) {
                    s.setId(Integer.parseInt(token[0]));
                    s.setDeparture_city(token[1]);
                    s.setArrival_city(token[2]);
                    s.setDeparture(token[3]);
                    s.setArrival(token[4]);
                    s.setFares(Double.parseDouble(token[5]));
                    check = false;
                    break;
                }
            }
            read.close();
            if (check == true) {
                line = "No record found";
            }
        } else {
            System.out.println("File does not exist");
            System.out.println("Enter Data for Slot first\n");
        }
        return s;
    }
}
