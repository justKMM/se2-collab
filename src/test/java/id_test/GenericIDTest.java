package id_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public interface GenericIDTest {

    public default <T> void testEqualsSameObject(T id) {
        assertEquals(id, id);
    }

    public default <T> void testEqualsSameValues(T id1, T id2) {
        assertEquals(id1, id2);
    }

    public default <T> void testEqualsDifferentValues(T id1, T id2) {
        assertNotEquals(id1, id2);
    }

    public default <T> void testEqualsNull(T id) {
        assertNotEquals(null, id);
    }

    public default <T> void testEqualsDifferentClass(T id, Object obj) {
        assertNotEquals(id, obj);
    }

    public default <T> void testHashCodeSameValues(T id1, T id2) {
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    public default <T> void testHashCodeDifferentValues(T id1, T id2) {
        assertNotEquals(id1.hashCode(), id2.hashCode());
    }
}