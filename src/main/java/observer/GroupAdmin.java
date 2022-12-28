package observer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * an object that will manage a StringBuilder and group of members,
 * and will update them every time that the undoableStringBuilder has changed.
 * @author Ilan Simchon , Alon Suissa
 * @version 1
 */
public class GroupAdmin implements Sender{
    private List<ConcreteMember> members;
    private UndoableStringBuilder usb;
    public GroupAdmin(){
        members = new ArrayList<>();
        usb = new UndoableStringBuilder();
    }

    /**
     * registers the member obj to this GroupAdmin
     * @param obj the member that will register to this GroupAdmin
     * @throws UnsupportedOperationException if the member already register to another GroupAdmin
     * @throws IllegalArgumentException if the input is null or not ConcreteMember
     */
    @Override
    public void register(Member obj) throws UnsupportedOperationException {
        if (obj instanceof ConcreteMember) {
            ConcreteMember CMember = (ConcreteMember) obj;
            if (!this.members.contains(CMember)) {
                if (CMember.getIs_connected()) {
                    throw new UnsupportedOperationException("you " +
                            "need to unregister your GroupAdmin before new registration");
                } else {
                    members.add(CMember);
                    CMember.setIs_connected(true);
                    CMember.nullifyCount_update();
                }
            }
        }
             else {
                    throw new IllegalArgumentException("your input is null or other implementation of Member");
                }
    }

    /**
     * unregisters the member obj from this GroupAdmin
     * @param obj the member that will unregister
     * @throws InputMismatchException if this GroupAdmin do not contain this ConcreteMember
     * @throws IllegalArgumentException if the input is null or other implementation of Member
     */
    @Override
    public void unregister(Member obj) {
        if(obj instanceof ConcreteMember) {
            ConcreteMember CMember = (ConcreteMember)obj;
            if(members.contains(CMember)) {
                members.remove(CMember);
                CMember.setIs_connected(false);
            }
            else {
                throw new InputMismatchException("this GroupAdmin do not contain this ConcreteMember");
            }
        }
        else {
            throw new IllegalArgumentException("your input is null or other implementation of Member");
        }
    }

    /**
     * Inserts the string into this character sequence and updates all the members
     * @param offset the index to make the insertion.
     * @param obj a String.
     *
     */
    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset,obj);
        updateAll();
    }

    /**
     * Appends the specified string to this character sequence and updates all the members.
     * @param obj a String
     * @return this UndoableStringBuilder with the changed String.
     */
    @Override
    public void append(String obj) {
        usb.append(obj);
        updateAll();
    }

    /**
     * Removes the characters in a substring the undoableStringBuilder's String. The substring begins
     * at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     * also the GroupAdmin updates all the members.
     * @param start the index which the deletion starts from.
     * @param end the index which the deletion ends on.
     * @return this Object with the changed String.
     */
    @Override
    public void delete(int start, int end) {
        usb.delete(start,end);
        updateAll();
    }

    /**
     * Undo the last change of the UndoableStringBuilder and updates all the members.
     */
    @Override
    public void undo() {
        usb.undo();
        updateAll();
    }

    /**
     * updates all the members
     */
    private void updateAll(){
        for (Member member: members) {
            member.update(usb);
        }
    }

    /***
     *
     * @return the number of members this GroupAdmin have.
     */
    public int getNumOfMembers(){
        return members.size();
    }

    /**
     *
     * @return the string of the undoableStringBuilder
     */
    public String getString(){
        return usb.toString();
    }

    /**
     *
     * @return the description of this GroupAdmin
     */
    @Override
    public String toString() {
        return "GroupAdmin{" +
                " members = " + members.toString() +
                ", usb = " + usb.toString() +
                " }";
    }
}

