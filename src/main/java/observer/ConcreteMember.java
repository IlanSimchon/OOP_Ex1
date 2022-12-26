package observer;


public class ConcreteMember implements Member{
    private String name;
    private UndoableStringBuilder usb;
    private int num_of_changes;

    public ConcreteMember(String name){
        this.name = name;
        usb = null;
        num_of_changes = 0;
    }
    public ConcreteMember(){
        name = "Plony";
        usb = null;
        num_of_changes = 0;
    }
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
        num_of_changes++;
    }
    public void registration(boolean register , UndoableStringBuilder usb) throws UnsupportedOperationException{
        if(register){
            if (this.usb != null) {
                throw new UnsupportedOperationException("you need to unregister your GroupAdmin before new registration");
            }
            else {
                update(usb);
                num_of_changes--; // to balance ++ of update
            }
        }
        else{
            this.usb = null;
            num_of_changes = 0;
        }
    }
    public String getString(){
        if(usb == null){
            return null;
        }
        return usb.toString();
    }

    public int getNumberOfChanges(){
        return num_of_changes;
    }

    public String getName(){
        return name;
    }
}
