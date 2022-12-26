package observer;

import java.util.ArrayList;
import java.util.List;

public class GroupAdmin implements Sender{
    private List<ConcreteMember> members; // not sure if needed
    private UndoableStringBuilder usb;
    public GroupAdmin(){
        members = new ArrayList<>();
        usb = new UndoableStringBuilder();
    }

    @Override
    public void register(Member obj) throws UnsupportedOperationException{
        if(obj instanceof ConcreteMember){
            ConcreteMember CMember = (ConcreteMember)obj;
            CMember.registration(true , usb);
            members.add(CMember);
        }
    }

    @Override
    public void unregister(Member obj) {
        if(obj instanceof ConcreteMember) {
            ConcreteMember CMember = (ConcreteMember)obj;
            if(members.contains(CMember)) {
                members.remove(CMember);
                CMember.registration(false, usb);
            }
        }
    }

    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset,obj);
        updateAll();
    }

    @Override
    public void append(String obj) {
        usb.append(obj);
        updateAll();
    }

    @Override
    public void delete(int start, int end) {
        usb.delete(start,end);
        updateAll();
    }

    @Override
    public void undo() {
        usb.undo();
        updateAll();
    }

    private void updateAll(){
        for (Member member: members) {
            member.update(usb);
        }
    }
    public int getNumOfMembers(){
        return members.size();
    }
    public String getString(){
        return usb.toString();
    }
}

