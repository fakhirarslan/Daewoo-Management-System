package daewoo.management.system.gui;

/**
 * This is the abstract class of the drivers
 *
 */
public abstract class Drivers implements commonInterface{

    private int dID;
    private String dName;
    private String dMob;

    /**
     * This is the overloaded constructor of the Drivers class
     *
     * @param dID
     * @param dName
     * @param dMob
     */
    public Drivers(int dID, String dName, String dMob) {
        this.dID = dID;
        this.dName = dName;
        this.dMob = dMob;
    }

    /**
     * This is the default constructor of the Drivers class
     */
    public Drivers() {
        dID = 0;
        dName = "Not assigned";
        dMob = "Not assigned";
    }

    /**
     * This is the mutator method to set the value of the driver id
     *
     * @param dID
     */
    public void setdID(int dID) {
        this.dID = dID;
    }

    /**
     * This is the mutator method to set the name of the driver
     *
     * @param dName
     */
    public void setdName(String dName) {
        this.dName = dName;
    }

    /**
     * This is the mutator method to set the mobile number of the driver
     *
     * @param dMob
     */
    public void setdMob(String dMob) {
        this.dMob = dMob;
    }

    /**
     * This is the accessors method to get the value of the driver id
     *
     * @return integer
     */
    public int getdID() {
        return dID;
    }

    /**
     * This is the accessors method to get the name of the driver
     *
     * @return String
     */
    public String getdName() {
        return dName;
    }

    /**
     * This is the accessors method to get the mobile number of the driver
     *
     * @return String
     */
    public String getdMob() {
        return dMob;
    }

    @Override
    public String toString() {
        return (dID + "\t" + dName + "\t\t" + dMob);
    }

    /**
     * This is the abstract method of this class Pay()
     *
     * @return double
     */
    public abstract double Pay();

}
