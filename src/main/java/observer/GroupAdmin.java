package observer;

import java.util.ArrayList;
import java.util.List;

public class GroupAdmin implements Sender{
    private List<ConcreteMember> members;
    UndoableStringBuilder usb;
    public GroupAdmin(){
        members = new ArrayList<>();
        usb = new UndoableStringBuilder();
    }

    @Override
    public void register(Member obj) {
        if(obj instanceof ConcreteMember){
            members.add((ConcreteMember) obj);
    }
    }

    @Override
    public void unregister(Member obj) {
        if(obj instanceof ConcreteMember) {
            members.remove((ConcreteMember) obj);
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
