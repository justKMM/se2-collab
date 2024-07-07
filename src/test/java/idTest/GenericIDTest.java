package idTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public abstract class GenericIDTest {

    public <T> void testEqualsSameObject(T id) {
        assertEquals(id, id);
    }

    public <T> void testEqualsSameValues(T id1, T id2) {
        assertEquals(id1, id2);
    }

    public <T> void testEqualsDifferentValues(T id1, T id2) {
        assertNotEquals(id1, id2);
    }

    public <T> void testEqualsNull(T id) {
        assertNotEquals(null, id);
    }

    public <T> void testEqualsDifferentClass(T id, Object obj) {
        assertNotEquals(id, obj);
    }

    public <T> void testHashCodeSameValues(T id1, T id2) {
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    public <T> void testHashCodeDifferentValues(T id1, T id2) {
        assertNotEquals(id1.hashCode(), id2.hashCode());
    }
}