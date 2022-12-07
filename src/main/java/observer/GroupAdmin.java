package observer;

import java.util.ArrayList;
import java.util.List;

public class GroupAdmin implements Sender{
    private List<ConcreteMember> members; // not sure if needed
    UndoableStringBuilder usb;
    public GroupAdmin(){
        members = new ArrayList<>();
        usb = new UndoableStringBuilder();
    }

    @Override
    public void register(Member obj) {
        if(obj instanceof ConcreteMember){
            ConcreteMember CMember = (ConcreteMember)obj;
            members.add(CMember);
           CMember.update(usb);
    }
    }

    @Override
    public void unregister(Member obj) {
        if(obj instanceof ConcreteMember) {
            ConcreteMember CMember = (ConcreteMember)obj;
            members.remove(CMember);
            CMember.update(null);
        }
    }

    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset,obj);
    }

    @Override
    public void append(String obj) {
    usb.append(obj);
    }

    @Override
    public void delete(int start, int end) {
    usb.delete(start,end);
    }

    @Override
    public void undo() {
    usb.undo();
    }
}
