package EntityTest;

import hbrs.se2.collhbrs.entity.Business;
import hbrs.se2.collhbrs.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BusinessTest {


    Business business;
    Business business1;
    Business business2;
    @Mock
    User user;

    @BeforeEach
    public void setUp() {
        business = new Business();
        business.setBusinessID(1L);
        business.setUser(user);
        business.setName("name");

        business1 = new Business();
        business1.setBusinessID(2L);
        business1.setUser(user);
        business1.setName("name1");

        business2 = new Business();
        business2.setBusinessID(2L);
        business2.setUser(user);
        business2.setName("name1");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, business.getBusinessID());
        assertEquals(user, business.getUser());
        assertEquals("name", business.getName());
    }
    @Test
    public void testEquals() {
        assertTrue(business.equals(business));
        assertTrue(business1.equals(business1));
        assertTrue(business1.equals(business2));
        assertFalse(business.equals(business1));
    }
}
