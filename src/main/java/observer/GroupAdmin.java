package observer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class GroupAdmin implements Sender{
    private List<ConcreteMember> members;
    private UndoableStringBuilder usb;
    public GroupAdmin(){
        members = new ArrayList<>();
        usb = new UndoableStringBuilder();
    }

    @Override
    public void register(Member obj) throws UnsupportedOperationException {
        if (obj instanceof ConcreteMember) {
            ConcreteMember CMember = (ConcreteMember) obj;
            if (!this.members.contains(CMember)) {
                if (CMember.getIs_conected()) {
                    throw new UnsupportedOperationException("you " +
                            "need to unregister your GroupAdmin before new registration");
                } else {
                    members.add(CMember);
                    CMember.setIs_conected(true);
                    CMember.nullifyCount_update();
                }
            }
        }
             else {
                    throw new IllegalArgumentException("your input is null or other implementation of Member");
                }


    }

    @Override
    public void unregister(Member obj) {
        if(obj instanceof ConcreteMember) {
            ConcreteMember CMember = (ConcreteMember)obj;
            if(members.contains(CMember)) {
                members.remove(CMember);
                CMember.setIs_conected(false);
            }
            else {
                throw new InputMismatchException("this GroupAdmin do not contain this ConcreteMember");
            }
        }
        else {
            throw new IllegalArgumentException("your input is null or other implementation of Member");
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

    @Override
    public String toString() {
        return "GroupAdmin{" +
                " members = " + members.toString() +
                ", usb = " + usb.toString() +
                " }";
    }
}

