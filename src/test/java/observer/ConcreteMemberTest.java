package observer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMemberTest {
    GroupAdmin GA1 = new GroupAdmin();
    @Test
    void update() {
        ConcreteMember m1 = new ConcreteMember("Timon");
        ConcreteMember m2 =  new ConcreteMember("Pumba");
        ConcreteMember m3 =  new ConcreteMember("Simba");

        GA1.append("akuna matata");
        GA1.register(m1);
        assertEquals(GA1.getString() , m1.getString());

        GA1.append("it's");
        assertEquals(1 , m1.getNumberOfChanges());

        GA1.register(m2);
        GA1.undo();
        assertEquals(m2.getString() , m1.getString());
        assertEquals(m1.getNumberOfChanges() -1 , m2.getNumberOfChanges());

        GA1.register(m3);
        GA1.delete(3,5);
        assertEquals(GA1.getString() , m3.getString());
        assertEquals(m2.getString() , m3.getString());
        assertEquals(1 , m3.getNumberOfChanges());
    }
    @Test
    void getNumOfChanges(){
        ConcreteMember m1 = new ConcreteMember("Timon");
        assertEquals(0, m1.getNumberOfChanges());
        GA1.register(m1);
        assertEquals(0, m1.getNumberOfChanges());
        GA1.append("hey");
        assertEquals(1, m1.getNumberOfChanges());
        GA1.append(" bye");
        assertEquals(2,m1.getNumberOfChanges());
    }
    @Test
    void getName(){
        ConcreteMember m2 =  new ConcreteMember("Pumba");
        assertEquals("Pumba" , m2.getName());

    }
    @Test
    void EmptyConstractotor(){
        ConcreteMember cm = new ConcreteMember();
        assertEquals(cm.getString() , null);
        assertEquals(cm.getNumberOfChanges() , 0);
        assertEquals(cm.getName(), "Plony");

    }
}