package IDTest;

import hbrs.se2.collhbrs.model.entity.ids.BaseID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseIDTest {
    private BaseID baseID1;
    private BaseID baseID2;

    @BeforeEach
    void setUp() {
        baseID1 = createBaseID(12345);
        baseID2 = createBaseID(12345);
    }

    private BaseID createBaseID(int serialNumber) {
        BaseID baseID = new BaseID() {
            // Implementing an anonymous subclass to instantiate BaseID
        };
        baseID.setSerialNumber(serialNumber);
        return baseID;
    }

    @Test
    void testEqualsSameObject() {
        assertEquals(baseID1, baseID1);
    }

    @Test
    void testEqualsSameValues() {
        assertEquals(baseID1, baseID2);
    }

    @Test
    void testEqualsDifferentValues() {
        BaseID baseID3 = createBaseID(67890);
        assertNotEquals(baseID1, baseID3);
    }

    @Test
    void testEqualsNull() {
        assertNotEquals(baseID1, null);
    }

    @Test
    void testEqualsDifferentClass() {
        assertNotEquals(baseID1, new Object());
    }

    @Test
    void testHashCodeSameValues() {
        assertEquals(baseID1.hashCode(), baseID2.hashCode());
    }
}

