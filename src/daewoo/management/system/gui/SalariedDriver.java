package daewoo.management.system.gui;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * This is the class performing operations on the file of drivers that are paid
 * monthly extending Drivers class
 */
public class SalariedDriver extends Drivers {

    private double s_pay;

    /**
     * This is the static File object
     */
    public static File file = new File("SalariedDrivers.txt");

    /**
     * This is the static Scanner object
     */
    public static Scanner input = new Scanner(System.in);

    /**
     * This is the overloaded constructor of the class SalariedDriver
     *
     * @param dID
     * @param dName
     * @param dMob
     * @param s_pay
     */
    public SalariedDriver(int dID, String dName, String dMob, double s_pay) {
        super(dID, dName, dMob);
        this.s_pay = s_pay;
    }

    /**
     * This is the default constructor of the class SalariedDriver
     */
    public SalariedDriver() {
        super();
        s_pay = 0;
    }

    /**
     * This is the mutator method to set the pay of the driver
     *
     * @param s_pay
     */
    public void setspay(double s_pay) {
        this.s_pay = s_pay;
    }

    /**
     * This is the accessors method to get the pay of the driver
     *
     * @return double
     */
    public double getspay() {
        return s_pay;
    }

    @Override
    public String toString() {
        return (super.toString() + "\t\t" + Pay());
    }

    /**
     * This is the method which was abstract in Drivers class and is defined
     * here
     *
     * @return double
     */
    @Override
    public double Pay() {
        return s_pay;
    }

    /**
     * This is the static method to add the monthly paid driver
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
     * This is the static method to check the id of the driver if it already
     * exists or not
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
     * This is the static method to view the monthly paid drivers
     *
     * @return String
     */
    public static String viewDriver() {
        Scanner read = null;
        String data = null;
        int count = 0;
        if (file.exists()) {
            try {
                read = new Scanner(file);
            } catch (IOException e) {
                System.out.println(e);
            }
            while (read.hasNextLine()) {
                read.nextLine();
                count++;
            }
            read.close();
            String[] array = new String[count];
            try {
                read = new Scanner(file);
            } catch (IOException e) {
                System.out.println(e);
            }
            int i = 0;
            while (read.hasNextLine()) {
                array[i] = read.nextLine();
                i++;
            }
            read.close();
            StringBuffer line = new StringBuffer();
            for (int j = 0; j < array.length; j++) {
                line.append(array[j]);
                line.append("\n");
            }
            data = line.toString();
        } else {
            System.out.println("\nFile does not exist");
            System.out.println("Enter Data for Drivers first");
        }
        return data;
    }

    /**
     * This is the static method to search the driver by id
     *
     * @param s_id
     * @return String
     */
    public static SalariedDriver searchDriver(int s_id) {
        SalariedDriver sd = new SalariedDriver();
        Scanner read = null;
        boolean check = true;
        if (file.exists()) {
            try {
                read = new Scanner(file);
                while (read.hasNextLine()) {
                    String line = read.nextLine();
                    String[] token = line.split("\\s+");
                    int s1_id = (Integer.parseInt(token[0]));
                    if (s1_id == s_id) {
                        sd.setdID(Integer.parseInt(token[0]));
                        sd.setdName(token[1]);
                        sd.setdMob(token[2]);
                        sd.setspay(Double.parseDouble(token[3]));
                        break;
                    }
                }
                read.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("File does not exist");
            System.out.println("Enter Data for Drivers first\n");
        }
        return sd;
    }

    /**
     * This is the static method to search the driver by name
     *
     * @param s_name
     * @return String
     */
    public static String searchDriver(String s_name) {
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
            System.out.println("Enter Data for Driver first\n");
        }
        return data;
    }

    /**
     * This is the static method to delete the record of monthly paid driver
     *
     * @param s_id
     * @return String
     */
    public static String deleteDriver(int s_id) {
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
            System.out.println("Enter Data for Drivers first\n");
        }
        return data;
    }

    /**
     * This is the static method to update the record of the driver
     *
     * @param s_id
     * @param nms
     * @return String
     */
    public static String updateDriver(int s_id, String nms) {
        Scanner read = null;
        File temp = null;
        PrintWriter pw = null;
        SalariedDriver p = new SalariedDriver();
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
                p.setdID(Integer.parseInt(token[0]));
                p.setdName(token[1]);
                p.setdMob(token[2]);
                p.setspay(Double.parseDouble(token[3]));
                if (s1_id == s_id) {
                    char c = nms.charAt(0);
                    if (nms.matches("^[A-Z].*$") || nms.matches("^[a-z].*$")) {
                        p.setdName(nms);
                        p.setdMob(p.getdMob());
                        p.setspay(p.getspay());
                    } else if (c == '0') {
                        p.setdName(p.getdName());
                        p.setdMob(nms);
                        p.setspay(p.getspay());
                    } else {
                        p.setdName(p.getdName());
                        p.setdMob(p.getdMob());
                        p.setspay(Double.parseDouble(nms));
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
            System.out.println("Enter Data for Drivers first\n");
        }
        return data;
    }

    @Override
    public Object search(int s_id) {
        SalariedDriver sd = new SalariedDriver();
        return sd;
    }

    @Override
    public String delete(int s_id) {
        String s = "";
        return s;
    }
}
