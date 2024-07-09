package id_test;

import hbrs.se2.collhbrs.model.entity.ids.BaseID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseIDTest implements GenericIDTest {
    private BaseID baseID1;
    private BaseID baseID2;

    @BeforeEach
    void setUp() {
        baseID1 = createBaseID(12345);
        baseID2 = createBaseID(12345);
    }

    private BaseID createBaseID(int serialNumber) {
        BaseID baseID = new BaseID() {
        };
        baseID.setSerialNumber(serialNumber);
        return baseID;
    }

    @Test
    void testEqualsSameObject() {
        testEqualsSameObject(baseID1);
    }

    @Test
    void testEqualsSameValues() {
        testEqualsSameValues(baseID1, baseID2);
    }

    @Test
    void testEqualsDifferentValues() {
        BaseID baseID3 = createBaseID(67890);
        testEqualsDifferentValues(baseID1, baseID3);
    }

    @Test
    void testEqualsNull() {
        testEqualsNull(baseID1);
    }

    @Test
    void testEqualsDifferentClass() {
        testEqualsDifferentClass(baseID1, new Object());
    }

    @Test
    void testHashCodeSameValues() {
        testHashCodeSameValues(baseID1, baseID2);
    }
}