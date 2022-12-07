package observer;
import java.util.Stack;

/**
 * an object that represent Strings and able to change the String and also undo changes. like StringBuilder with undo method.
 * @author Ilan Simchon , Alon Suissa
 * @version 1
 */
public class UndoableStringBuilder{
    private  StringBuilder s;
    private Stack<StringBuilder> my_stack;

    /**
     *creating a new UndoableStringBuilder.
     */
    public UndoableStringBuilder(){
        s = new StringBuilder();
        my_stack = new Stack<StringBuilder>();
    }


    /**
     * Appends the specified string to this character sequence.
     * @param str a String
     * @return this UndoableStringBuilder with the changed String.
     */
    public UndoableStringBuilder append(String str){
        my_stack.add(new StringBuilder(s));
        s = s.append(str);

        return this;

    }

    /**
     * Removes the characters in a substring of this sequence. The substring begins
     * at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     * @param start the index which the deletion starts from.
     * @param end the index which the deletion ends on.
     * @return this Object with the changed String.
     */
    public UndoableStringBuilder delete(int start, int end){
        try {
            my_stack.add(new StringBuilder(s));
            s.delete(start, end);
        }
        catch (StringIndexOutOfBoundsException ex) {
            my_stack.pop();
            ex.printStackTrace();
            if(start > end)
                System.err.println("end index should be grater than start index");
            else
                System.err.println("index out of bounds. Please make sure that start is in the range 0 - " + s.length());
        }
        return this ;
    }

    /**
     * Inserts the string into this character sequence.
     * @param offset the index to make the insertion.
     * @param str a String.
     * @return this object with the changed String.
     */
    public UndoableStringBuilder insert(int offset, String str){
        try {
            my_stack.add(new StringBuilder(s));
            s.insert(offset, str);
        }
        catch (StringIndexOutOfBoundsException ex) {
            my_stack.pop();
            ex.printStackTrace();
            System.err.println("Index out of bounds. Please stay in the range 0 - " + s.length() );
        }

        return this;
    }

    /**
     * Replaces the characters in a substring of this sequence with characters in
     * the specified String. The substring begins at the specified start and
     * extends to the character at index end - 1 or to the end of the sequence if
     * no such character exists. First the characters in the substring are removed
     * and then the specified String is inserted at start. (This sequence will be
     * lengthened to accommodate the specified String if necessary).
     * @param start  start index, delete from this index.
     * @param end  end index, delete till this index.
     * @param str  add this String instead of the deleted String.
     * @return this UndoableStringBuilder with the changed String.
     */
    public UndoableStringBuilder replace(int start,int end, String str){
        try {
            my_stack.add(new StringBuilder(s));
            s.replace(start, end,str);
        } catch (StringIndexOutOfBoundsException ex) {
            my_stack.pop();
            ex.printStackTrace();
            if(start > end)
                System.err.println("end should be grater than start");
            else
                System.err.println("Index out of bounds. Please make sure that start is in the range 0 - " + s.length());
        }
        catch (NullPointerException ex){
            my_stack.pop();
            ex.printStackTrace();
            System.err.println("The String should not be null.");
        }
        return this;
    }

    /**
     * Causes this character sequence to be replaced by the reverse of the
     * sequence.
     * @return this UndoableStringBuilder with reverse String of the original one.
     */
    public UndoableStringBuilder reverse(){
        my_stack.add( new StringBuilder(s));
        s.reverse();

        return this;
    }

    /**
     * Undo the last change of the UndoableStringBuilder.
     */
    public void undo(){
        if(!my_stack.isEmpty())
            s = my_stack.pop();
    }

    @Override
    public String toString() {

        return s.toString();
    }
}
