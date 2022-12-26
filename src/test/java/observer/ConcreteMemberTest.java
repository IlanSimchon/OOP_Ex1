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
}