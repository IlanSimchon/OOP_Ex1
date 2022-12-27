package observer;


public class ConcreteMember implements Member{
    private String name;
    private UndoableStringBuilder usb;
   // private int num_of_changes;
    private boolean is_conected;
    private int count_update; // for use in Tests

    public ConcreteMember(String name){
        this.name = name;
        usb = null;
        is_conected = false;
    }

    public ConcreteMember(){
        name = "Plony";
        usb = null;
        is_conected = false;
    }
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
        count_update++;
    }
    public String getString(){
        if(usb == null){
            return null;
        }
        return usb.toString();
    }

    public UndoableStringBuilder getUsb(){
        return this.usb;
    }

    public String getName(){
        return name;
    }

    public boolean getIs_conected() {
        return is_conected;
    }

    public void setIs_conected(boolean is_conected) {
        this.is_conected = is_conected;
    }
    public void nullifyCount_update(){
        this.count_update = 0;
    }
    public int getCount_update(){
        return  this.count_update;
    }
    @Override
    public String toString() {
        return "ConcreteMember{" +
                "name='" + name + '\'' +
                ", usb=" + usb +
                '}';
    }
}
