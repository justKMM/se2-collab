package dtoTest;

import hbrs.se2.collhbrs.model.dto.BusinessDTO;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BusinessDTOTest {

    @Mock
    UserDTO userDTO;
    private BusinessDTO businessDTO;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setUserID(1L);
        user.setUsername("testuser");

        userDTO = new UserDTO(user);
        Business business = new Business();
        business.setUser(user);
        this.businessDTO = new BusinessDTO(business);
    }

    @Test
    void testGettersAndSetters() {
        assertEquals(0, businessDTO.getBusinessID());
        assertNull(businessDTO.getName());

        businessDTO.setBusinessID(1L);
        businessDTO.setName("TestBusiness");
        businessDTO.setUser(userDTO.getUser());

        assertEquals(1L, businessDTO.getBusinessID());
        assertEquals("TestBusiness", businessDTO.getName());
        assertEquals(userDTO.getUsername(), businessDTO.getUser().getUsername());
    }
}

