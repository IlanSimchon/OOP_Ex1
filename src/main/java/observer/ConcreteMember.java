package observer;

public class ConcreteMember implements Member{
    UndoableStringBuilder usb;
    public ConcreteMember(){
        usb = null;
    }
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }
}
