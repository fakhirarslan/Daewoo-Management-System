package daewoo.management.system.gui;

/**
 * This is the interface containing four abstract functions which will be
 * defined in its implementing class
 *
 */
public interface commonInterface {

    /**
     * This is the abstract method to add the record
     */
    public void add();

    /**
     * This is the abstract method to search the record by id
     *
     * @param s_id
     * @return Object
     */
    public Object search(int s_id);

    /**
     * This is the abstract method to delete the record
     *
     * @param s_id
     * @return String
     */
    public String delete(int s_id);

}
