package entity_test;

import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
class BusinessTest {


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
    void testGettersAndSetters() {
        assertEquals(1L, business.getBusinessID());
        assertEquals(user, business.getUser());
        assertEquals("name", business.getName());
    }

    @Test
    void testEquals() {
        assertEquals(business, business);
        assertEquals(business1, business1);
        assertEquals(business1, business2);
        assertNotEquals(business, business1);
    }
}
