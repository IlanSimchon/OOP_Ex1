import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.Member;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

public class Tests{
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";
        GroupAdmin GAMemoryTests = new GroupAdmin();
        GAMemoryTests.register(new ConcreteMember("A"));
        GA1.append("hello world,");
        logger.info(()->JvmUtilities.objectFootprint(GAMemoryTests));
        GAMemoryTests.register(new ConcreteMember("B"));
        GAMemoryTests.register(new ConcreteMember("C"));
        GAMemoryTests.register(new ConcreteMember("D"));
        GAMemoryTests.register(new ConcreteMember("E"));
        GAMemoryTests.register(new ConcreteMember("F"));
        GAMemoryTests.register(new ConcreteMember("G"));
        logger.info(()->JvmUtilities.objectFootprint(GAMemoryTests));
        GAMemoryTests.append("i just want to add text and check i it will effect the memory test =)");

        logger.info(()->JvmUtilities.objectTotalSize(GAMemoryTests));
        logger.info(() -> JvmUtilities.objectTotalSize(GAMemoryTests));
        GAMemoryTests.append("i just want to add text and check i it will effect the memory test =)");
        logger.info(() -> JvmUtilities.objectTotalSize(GAMemoryTests));

        logger.info(() -> JvmUtilities.jvmInfo());

    }

    //********************************GroupAdmin Tests********************************

    GroupAdmin GA1 = new GroupAdmin();
    GroupAdmin GA2 = new GroupAdmin();
    ConcreteMember m1 = new ConcreteMember("Timon");
    ConcreteMember m2 = new ConcreteMember("Pumba");
    ConcreteMember m3 = new ConcreteMember("Simba");

    @Test
    void register(){
        GA1.register(m1);
        assertEquals(1, GA1.getNumOfMembers());
        assertEquals(null , m1.getString());
        GA1.append("try");
        assertEquals(GA1.getString(), m1.getString());

        GA1.register(m2);
        GA1.register(m2);
        assertEquals(2 , GA1.getNumOfMembers());

        GA1.append(" test");
        assertEquals(m1.getString(), m2.getString());

        GA1.unregister(m1);
        assertEquals(1, GA1.getNumOfMembers());
    }
    @Test
    void registerOverRegister(){
        GA1.register(m2);
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> GA2.register(m2));
        assertEquals( "you need to unregister your GroupAdmin before "+
                "new registration",exception.getMessage());
    }
    @Test
    void NotConcreteMemberRegister(){
        Member m = new Member(){
            @Override
            public void update(UndoableStringBuilder usb) {
                System.out.println("i should not print this");
            }
        };
        Exception exception_other = assertThrows(IllegalArgumentException.class, () -> GA1.register(m));
        assertEquals( "your input is null or other implementation of Member",exception_other.getMessage());
        assertEquals(0 , GA1.getNumOfMembers());

    }

    @Test
    void nullRegister(){
        Exception exception_null = assertThrows(IllegalArgumentException.class, () -> GA1.register(null));
        assertEquals( "your input is null or other implementation of Member",exception_null.getMessage());
        assertEquals(0 , GA1.getNumOfMembers());
    }

    @Test
    void unregister() {
        GA1.register(m1);
        GA1.register(m2);
        GA1.unregister(m1);
        assertEquals(1 , GA1.getNumOfMembers());


        Exception exception = assertThrows(InputMismatchException.class, () ->  GA1.unregister(m3));
        assertEquals( "this GroupAdmin do not contain this ConcreteMember",exception.getMessage());

        assertEquals(1 , GA1.getNumOfMembers());
        GA1.insert(0 , "test ");
        assertEquals("test " , m2.getString());
    }
    @Test
    void NotConcreteMemberUnregister(){
        Member m = new Member(){
            @Override
            public void update(UndoableStringBuilder usb) {
                System.out.println("i should not print this");
            }
        };
        Exception exception_other = assertThrows(IllegalArgumentException.class, () -> GA1.unregister(m));
        assertEquals( "your input is null or other implementation of Member",exception_other.getMessage());
        assertEquals(0 , GA1.getNumOfMembers());

    }

    @Test
    void UnregisterNull(){

        GA1.register(m1);

        Exception exception_null = assertThrows(IllegalArgumentException.class, () -> GA1.unregister(null));
        assertEquals( "your input is null or other implementation of Member",exception_null.getMessage());

        assertEquals(1 , GA1.getNumOfMembers());

    }

    @Test
    void insert() {
        GA1.register(m1);
        GA1.insert(0, "test ");
        assertEquals("test " , m1.getString());
    }

    @Test
    void append() {
        GA1.register(m1);
        GA1.append(" test");
        assertEquals(" test" , m1.getString());
    }

    @Test
    void delete() {
        GA1.append("akuna matata");

        GA1.register(m1);
        GA1.delete(0 , 6);
        assertEquals("matata" , m1.getString());

    }

    @Test
    void undo() {
        GA1.append("akuna matata");
        GA1.register(m1);
        GA1.append(" test");
        GA1.undo();
        assertEquals("akuna matata" , m1.getString());

    }
    @Test
    void  UndoFirstThing(){
        GA1.undo();
        GA1.undo();
        assertEquals( "" ,GA1.getString());
    }
    @Test
    void updateAll() {
        GA1.append("akuna matata");
        GA1.register(m1);
        GA1.register(m2);
        GA1.register(m3);
        GA1.append(" test");
        assertEquals("akuna matata test", m1.getString());
        assertEquals("akuna matata test", m2.getString());
        assertEquals("akuna matata test", m3.getString());
    }
    @Test
    void getString(){
        GA1.append("akuna matata");
        assertEquals("akuna matata",GA1.getString() );
    }
    //********************************ConcreteMember Tests********************************
    @Test
    void update() {
        ConcreteMember m1 = new ConcreteMember("Timon");
        ConcreteMember m2 =  new ConcreteMember("Pumba");
        ConcreteMember m3 =  new ConcreteMember("Simba");

        GA1.append("akuna matata");
        GA1.register(m1);
        assertEquals(null , m1.getString());

        GA1.append("it's");
        assertEquals(1 , m1.getCount_update());

        GA1.register(m2);
        GA1.undo();
        assertEquals(m2.getString() , m1.getString());
        assertEquals(m1.getCount_update() -1 , m2.getCount_update());

        GA1.register(m3);
        GA1.delete(3,5);
        assertEquals(GA1.getString() , m3.getString());
        assertEquals(m2.getString() , m3.getString());
        assertEquals(1 , m3.getCount_update());
    }
    @Test
    void getCount_update(){
        ConcreteMember m1 = new ConcreteMember("Timon");
        assertEquals(0 , m1.getCount_update());
        GA1.register(m1);
        assertEquals(0, m1.getCount_update());
        GA1.append("hey");
        assertEquals(1, m1.getCount_update());
        GA1.append(" bye");
        assertEquals(2,m1.getCount_update());
    }
    @Test
    void getName(){
        ConcreteMember m2 =  new ConcreteMember("Pumba");
        assertEquals("Pumba" , m2.getName());

    }
    @Test
    void EmptyConstructor(){
        ConcreteMember cm = new ConcreteMember();
        assertNull(cm.getString());
        assertEquals(cm.getCount_update() , 0);
        assertEquals(cm.getName(), "Plony");

    }

}
