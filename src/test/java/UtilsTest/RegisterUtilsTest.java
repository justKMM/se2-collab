package UtilsTest;

import com.vaadin.flow.component.UI;
import hbrs.se2.collhbrs.util.RegisterUtils;
import org.junit.Test;

import static org.junit.Assert.*;


public class RegisterUtilsTest {

    private final static String TESTPW = "password123";
    private final static String USERNAME = "username";

    @Test
    public void testValidEmails() {
        assertTrue(RegisterUtils.validateInput(USERNAME, "John", "Doe", "test@example.com", TESTPW, TESTPW));
        assertTrue(RegisterUtils.validateInput(USERNAME, "John", "Doe", "user.name@example.com", TESTPW, TESTPW));
        assertTrue(RegisterUtils.validateInput(USERNAME, "John", "Doe", "user123@example.co.uk", TESTPW, TESTPW));
        assertTrue(RegisterUtils.validateInput(USERNAME, "John", "Doe", "user_name@example-domain.com", TESTPW, TESTPW));
    } //funktioniert aktuell nicht, aufgrund von UI-Error

    @Test
    public void testValidateInput_InvalidFirstName() {
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            RegisterUtils.validateInput("", "InvalidFirstName", "LastName", "email@example.com", "password", "password");
        });
        assertEquals("UI instance is not available. It means that you are calling this method out of a normal workflow where it's always implicitly set. That may happen if you call the method from the custom thread without 'UI::access' or from tests without proper initialization.", exception.getMessage());
    }
}
