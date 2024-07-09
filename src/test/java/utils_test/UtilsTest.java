package utils_test;

import hbrs.se2.collhbrs.util.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UtilsTest {

    @Test
    void testAppendToEmptyArray() {
        Integer[] originalArray = new Integer[0];
        Integer elementToAdd = 1;
        Integer[] newArray = Utils.append(originalArray, elementToAdd);

        assertEquals(1, newArray.length);
        assertEquals(elementToAdd, newArray[0]);
    }

    @Test
    void testAppendToNonEmptyArray() {
        String[] originalArray = {"a", "b"};
        String elementToAdd = "c";
        String[] newArray = Utils.append(originalArray, elementToAdd);

        assertEquals(3, newArray.length);
        assertEquals("a", newArray[0]);
        assertEquals("b", newArray[1]);
        assertEquals("c", newArray[2]);
    }

    @Test
    void testAppendNullElement() {
        String[] originalArray = {"a", "b"};
        String elementToAdd = null;
        String[] newArray = Utils.append(originalArray, elementToAdd);

        assertEquals(3, newArray.length);
        assertEquals("a", newArray[0]);
        assertEquals("b", newArray[1]);
        assertNull(newArray[2]);
    }

    @Test
    void testAppendToArrayWithNullElement() {
        String[] originalArray = {null, "b"};
        String elementToAdd = "c";
        String[] newArray = Utils.append(originalArray, elementToAdd);

        assertEquals(3, newArray.length);
        assertNull(newArray[0]);
        assertEquals("b", newArray[1]);
        assertEquals("c", newArray[2]);
    }
}