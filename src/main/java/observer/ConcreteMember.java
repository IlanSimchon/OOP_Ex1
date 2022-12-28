package observer;

/**
 * an object that will register to group admin and will get updates about an undoableStringBuilder
 */
public class ConcreteMember implements Member{

    private String name;
    private UndoableStringBuilder usb;
   // private int num_of_changes;
    private boolean is_connected;
    private int count_update; // for use in Tests
    /**
     * constructor
     * @param name the name of the member
     */
    public ConcreteMember(String name){
        this.name = name;
        usb = null;
        is_connected = false;
    }

    /**
     * empty constructor
     */
    public ConcreteMember(){
        name = "Plony";
        usb = null;
        is_connected = false;
    }

    /**
     * updates the UndoableStringBuilder, and updates the number of updates.
     * @param usb the UndoableStringBuilder that need to be updated
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
        count_update++;
    }

    /**
     *
     * @return the string of the undoableStringBuilder
     */
    public String getString(){
        if(usb == null){
            return null;
        }
        return usb.toString();
    }

    /**
     *
     * @return the undoableStringBuilder that this member holds.
     */
    public UndoableStringBuilder getUsb(){
        return this.usb;
    }

    /**
     *
     * @return returns the name of this member
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return true if this member is registered to an undoableStringBuilder
     * and false if not.
     */
    public boolean getIs_connected() {
        return is_connected;
    }

    /**
     *  set the parameter 'is_connected' to the input variable
     * @param is_connected the new value
     */
    public void setIs_connected(boolean is_connected) {
        this.is_connected = is_connected;
    }

    /**
     * make the updates counter zero.
     */
    public void nullifyCount_update(){
        this.count_update = 0;
    }

    /**
     *
     * @return the number of updates that maid in the undoableStringBuilder since the last registration
     */
    public int getCount_update(){
        return  this.count_update;
    }

    /**
     *
     * @return  the description of this ConcreteMember
     */
    @Override
    public String toString() {
        return "ConcreteMember{" +
                "name='" + name + '\'' +
                ", usb=" + usb +
                '}';
    }
}
