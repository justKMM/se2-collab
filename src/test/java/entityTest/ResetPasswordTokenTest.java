package entityTest;

import hbrs.se2.collhbrs.model.entity.ResetPasswordToken;
import hbrs.se2.collhbrs.model.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ResetPasswordTokenTest {

    ResetPasswordToken resetPasswordToken;
    ResetPasswordToken resetPasswordToken1;

    @Mock
    User user;
    User user1;

    @BeforeEach
    public void setUp() {
        resetPasswordToken = new ResetPasswordToken("sampleToken123", user);
        resetPasswordToken1 = new ResetPasswordToken("sampleToken321", user1);
    }

    @Test
    void testGettersAndSetters() {
        assertNull(resetPasswordToken.getUser());
        assertNull(resetPasswordToken.getTokenID());
        assertEquals("sampleToken123", resetPasswordToken.getToken());

        assertNull(resetPasswordToken1.getUser());
        assertNull(resetPasswordToken1.getTokenID());
        assertEquals("sampleToken321", resetPasswordToken1.getToken());

        resetPasswordToken.setTokenID(1L);
        resetPasswordToken1.setTokenID(2L);
        resetPasswordToken.setToken("anotherToken456");
        resetPasswordToken1.setToken("anotherToken123");
        resetPasswordToken.setUser(user1);
        resetPasswordToken1.setUser(user);

        assertEquals(user1, resetPasswordToken.getUser());
        assertEquals(1L, resetPasswordToken.getTokenID());
        assertEquals("anotherToken456", resetPasswordToken.getToken());

        assertEquals(user, resetPasswordToken1.getUser());
        assertEquals(2L, resetPasswordToken1.getTokenID());
        assertEquals("anotherToken123", resetPasswordToken1.getToken());
    }

}
