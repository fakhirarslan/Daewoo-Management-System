package daewoo.management.system.gui;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * This is the Passenger class working on the passenger data on a file
 *
 */
public class Passengers implements commonInterface {

    /**
     * This is the static File object
     */
    public static File file = new File("passengers.txt");

    /**
     * This is the static Scanner object
     */
    public static Scanner input = new Scanner(System.in);
    private int p_id;
    private String name;
    private String cnic;
    private String mob_no;

    /**
     * This is the default constructor of the class Passengers
     */
    public Passengers() {
        p_id = 0;
        name = "Not Assigned";
        cnic = "Not Assigned";
        mob_no = "Not Assigned";
    }

    /**
     * This is the overloaded constructor of the class Passengers taking four
     * variables as a parameter
     *
     * @param p_id
     * @param name
     * @param cnic
     * @param mob_no
     */
    public Passengers(int p_id, String name, String cnic, String mob_no) {
        this.p_id = p_id;
        this.name = name;
        this.cnic = cnic;
        this.mob_no = mob_no;
    }

    /**
     * This is the copy constructor of the class Passengers
     *
     * @param p
     */
    public Passengers(Passengers p) {
        p.p_id = p_id;
        p.name = name;
        p.cnic = cnic;
        p.mob_no = mob_no;
    }

    /**
     * This is the mutator method to set the value of the id of the passenger
     *
     * @param p_id
     */
    public void setID(int p_id) {
        this.p_id = p_id;
    }

    /**
     * This is the mutator method to set the name of the passenger
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This is the mutator method to set the cnic of the passenger
     *
     * @param cnic
     */
    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    /**
     * This is the mutator method to set the mobile no of the passenger
     *
     * @param mob_no
     */
    public void setMob(String mob_no) {
        this.mob_no = mob_no;
    }

    /**
     * This is the accessors method to get the id of the passenger
     *
     * @return integer
     */
    public int getID() {
        return p_id;
    }

    /**
     * This is the accessors method to get the name of the passenger
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * This is the accessors method to get the cnic of the passenger
     *
     * @return String
     */
    public String getCnic() {
        return cnic;
    }

    /**
     * This is the accessors method to get the mobile no of the passenger
     *
     * @return String
     */
    public String getMob() {
        return mob_no;
    }

    @Override
    public String toString() {
        return (p_id + "\t" + name + "\t\t" + cnic + "\t\t" + mob_no);
    }

    /**
     * This is the equals method to check the ID of the passengers if they're
     * equal or not
     *
     * @param p
     * @return boolean
     */
    public boolean equals(Passengers p) {
        if (this.p_id == p.p_id) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This is the method to add the data of the passenger to the file
     *
     */
    public void add() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(file.getAbsolutePath(), true));
            pw.println(toString());
            pw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        JOptionPane.showMessageDialog(null, "Data added successfully");
    }

    /**
     * This is the static method to check that the new id already exists in the
     * file or not
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
     * This is the static method to view the data of the passengers from the
     * file
     *
     * @return ArrayList
     */
    public static ArrayList viewPassenger() {
        Passengers p = new Passengers();
        ArrayList<Passengers> arrayList = new ArrayList<>();
        Scanner read = null;
        if (file.exists()) {
            try {
                read = new Scanner(file);
            } catch (IOException e) {
                System.out.println(e);
            }
            int i = 0;
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] token = line.split("\\s+");
                p.setID(Integer.parseInt(token[0]));
                p.setName(token[1]);
                p.setCnic(token[2]);
                p.setMob(token[3]);
                arrayList.add(new Passengers(p.getID(), p.getName(), p.getCnic(), p.getMob()));
            }
            read.close();
        } else {
            System.out.println("\nFile does not exist");
            System.out.println("Enter Data for Passengers first");
        }
        return arrayList;
    }

    /**
     * This is the method to search the passenger using id from the file
     *
     * @param s_id
     * @return Passengers
     */
    public Passengers search(int s_id) {
        Passengers p = new Passengers();
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
                        p.setID(Integer.parseInt(token[0]));
                        p.setName(token[1]);
                        p.setCnic(token[2]);
                        p.setMob(token[3]);
                        check = false;
                        break;
                    }
                }
                read.close();
                if (check == true) {
                    JOptionPane.showMessageDialog(null, "No record found");
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("File does not exist");
            System.out.println("Enter Data for Passengers first\n");
        }
        return p;
    }

    /**
     * This is the static method to search the passenger using name from the
     * file
     *
     * @param s_name
     * @return String
     */
    public static String searchPassenger(String s_name) {
        Scanner read = null;
        boolean check = true;
        String data = null;
        int count = 0;
        if (file.exists()) {
            try {
                read = new Scanner(file);
            } catch (IOException e) {
                System.out.println(e);
            }
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] token = line.split("\\s+");
                String s1_name = token[1];
                if ((s1_name.compareToIgnoreCase(s_name)) == 0) {
                    System.out.println(line + "\n");
                    count++;
                    check = false;
                }
            }
            read.close();
            String[] array = new String[count];
            try {
                read = new Scanner(file);
            } catch (IOException e) {
                System.out.println(e);
            }
            int j = 0;
            while (read.hasNextLine()) {
                String line = read.nextLine();
                String[] token = line.split("\\s+");
                String s1_name = token[1];
                if ((s1_name.compareToIgnoreCase(s_name)) == 0) {
                    array[j] = line;
                    j++;
                    check = false;
                }
            }
            read.close();
            StringBuffer line1 = new StringBuffer();
            for (int a = 0; a < array.length; a++) {
                line1.append(array[a]);
                line1.append("\n");
            }
            data = line1.toString();
            if (check == true) {
                data = "No record found";
            }

        } else {
            System.out.println("File does not exist");
            System.out.println("Enter Data for Passengers first\n");
        }
        return data;
    }

    /**
     * This is the to delete the data of the passenger from the file
     *
     * @param s_id
     * @return String
     */
    public String delete(int s_id) {
        Scanner read = null;
        File temp = new File("temp.txt");
        PrintWriter pw = null;
        String data = null;
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
                if (s1_id == s_id) {
                    data = "Successfully deleted";
                    check = false;
                } else {
                    try {
                        pw = new PrintWriter(new FileWriter(temp.getAbsolutePath(), true));
                        pw.println(line);
                        pw.close();

                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            }
            read.close();
            file.delete();
            temp.renameTo(file);
            if (check == true) {
                data = "No record of this ID found";
            }
        } else {
            System.out.println("File does not exist");
            System.out.println("Enter Data for Passengers first\n");
        }
        return data;
    }

    /**
     * This is the static method to update the record of the passenger from the
     * file
     *
     * @param s_id
     * @param ncm
     * @return String
     */
    public String update(int s_id, String ncm) {
        Scanner read = null;
        File temp = null;
        PrintWriter pw = null;
        Passengers p = new Passengers();
        String data = null;
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
                p.setID(Integer.parseInt(token[0]));
                p.setName(token[1]);
                p.setCnic(token[2]);
                p.setMob(token[3]);
                if (s1_id == s_id) {
                    char c = ncm.charAt(0);
                    if (ncm.matches("^[A-Z].*$") || ncm.matches("^[a-z].*$")) {
                        p.setName(ncm);
                        p.setCnic(p.getCnic());
                        p.setMob(p.getMob());
                    } else if (c == '0') {
                        p.setName(p.getName());
                        p.setCnic(p.getCnic());
                        p.setMob(ncm);
                    } else {
                        p.setName(p.getName());
                        p.setCnic(ncm);
                        p.setMob(p.getMob());
                    }
                    temp = new File("temp.txt");
                    try {
                        pw = new PrintWriter(new FileWriter(temp.getAbsolutePath(), true));
                        pw.println(p.toString());
                        pw.close();

                    } catch (IOException e) {
                        System.out.println(e);
                    }
                    data = "Updated successfully";
                } else {
                    temp = new File("temp.txt");
                    try {
                        pw = new PrintWriter(new FileWriter(temp.getAbsolutePath(), true));
                        pw.println(line);
                        pw.close();

                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            }
            read.close();
            file.delete();
            temp.renameTo(file);
        } else {
            System.out.println("File does not exist");
            System.out.println("Enter Data for Passengers first\n");
        }
        return data;
    }
}
