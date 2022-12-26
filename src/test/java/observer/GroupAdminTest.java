package observer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupAdminTest {
    static GroupAdmin GA1 = new GroupAdmin();
    static GroupAdmin GA2 = new GroupAdmin();

    static ConcreteMember m1 = new ConcreteMember("Timon");
    static ConcreteMember m2 = new ConcreteMember("Pumba");
    static ConcreteMember m3 = new ConcreteMember("Simba");

    @BeforeAll
    static void setUp() {
        GA1.append("akuna matata");
    }

    @Test
    void register() {
        GA1.register(m1);
        assertEquals(1, GA1.getNumOfMembers());
        assertEquals(GA1.getString(), m1.getString());

        GA1.register(m2);
        assertEquals(m1.getString(), m2.getString());

        GA1.unregister(m1);
        assertEquals(1, GA1.getNumOfMembers());

        Member m = new Member() {
            @Override
            public void update(UndoableStringBuilder usb) {
                System.out.println("i should not print this");
            }
        };

        GA1.register(m);
        assertEquals(1 , GA1.getNumOfMembers());

        GA1.register(null);
        assertEquals(1 , GA1.getNumOfMembers());



    Exception exception = assertThrows(UnsupportedOperationException.class, () -> GA2.register(m2));

    assertEquals( "you need to unregister your GroupAdmin before "+
                          "new registration",exception.getMessage());


    }

    @Test
    void unregister() {
        GA1.register(m1);
        GA1.register(m2);
        GA1.unregister(m1);
        assertEquals(null , m1.getString());
        assertEquals(1 , GA1.getNumOfMembers());

        GA1.unregister(m3);
        assertEquals(null , m3.getString());
        assertEquals(1 , GA1.getNumOfMembers());


        GA1.unregister(null);
        assertEquals(1 , GA1.getNumOfMembers());

        GA1.register(m2);
        assertEquals(2 , GA1.getNumOfMembers());
    }

    @Test
    void insert() {
        GA1.register(m1);
        GA1.insert(0, "test ");
        assertEquals("test akuna matata" , m1.getString());
    }

    @Test
    void append() {
        GA1.register(m1);
        GA1.append(" test");
        assertEquals("akuna matata test" , m1.getString());
    }

    @Test
    void delete() {
        GA1.register(m1);
        GA1.delete(0 , 6);
        assertEquals("matata" , m1.getString());

    }

    @Test
    void undo() {
        GA1.register(m1);
        GA1.append(" test");
        GA1.undo();
        assertEquals("akuna matata" , m1.getString());

    }

    @Test
    void updateAll() {
        GA1.register(m1);
        GA1.register(m2);
        GA1.register(m3);
        GA1.append(" test");
        assertEquals("akuna matata test" , m1.getString());
        assertEquals("akuna matata test" , m2.getString());
        assertEquals("akuna matata test" , m3.getString());

        GA1.unregister(m3);
        GA1.insert(0 , "test ");
        assertEquals("test akuna matata test" , m1.getString());
        assertEquals("test akuna matata test" , m2.getString());
        assertEquals(null , m3.getString());
    }
}