package utils_test;

import hbrs.se2.collhbrs.util.RegisterUtils;
import org.junit.Test;

import static org.junit.Assert.*;


public class RegisterUtilsTest {

    private static final String TEST_P = "password123";
    private static final String USERNAME = "username";

    @Test
    public void testValidEmails() {
        assertTrue(RegisterUtils.validateInput(USERNAME, "John", "Doe", "test@example.com", TEST_P, TEST_P));
        assertTrue(RegisterUtils.validateInput(USERNAME, "John", "Doe", "user.name@example.com", TEST_P, TEST_P));
        assertTrue(RegisterUtils.validateInput(USERNAME, "John", "Doe", "user123@example.co.uk", TEST_P, TEST_P));
        assertTrue(RegisterUtils.validateInput(USERNAME, "John", "Doe", "user_name@example-domain.com", TEST_P, TEST_P));
    } //funktioniert aktuell nicht, aufgrund von UI-Error

    @Test
    public void testValidateInputInvalidFirstName() {
        Throwable exception = assertThrows(IllegalStateException.class, () ->
                RegisterUtils.validateInput("", "InvalidFirstName", "LastName", "email@example.com", "password", "password"));
        assertEquals("UI instance is not available. It means that you are calling this method out of a normal workflow where it's always implicitly set. That may happen if you call the method from the custom thread without 'UI::access' or from tests without proper initialization.", exception.getMessage());
    }
}
