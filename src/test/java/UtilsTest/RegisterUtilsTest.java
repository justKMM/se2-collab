package UtilsTest;

import hbrs.se2.collhbrs.util.RegisterUtils;
import org.junit.Test;

import static org.junit.Assert.*;


public class RegisterUtilsTest {


    @Test
    public void testValidEmails() {
        assertTrue(RegisterUtils.validateInput("username", "John", "Doe", "test@example.com", "Password123", "Password123"));
        assertTrue(RegisterUtils.validateInput("username", "John", "Doe", "user.name@example.com", "Password123", "Password123"));
        assertTrue(RegisterUtils.validateInput("username", "John", "Doe", "user123@example.co.uk", "Password123", "Password123"));
        assertTrue(RegisterUtils.validateInput("username", "John", "Doe", "user_name@example-domain.com", "Password123", "Password123"));
    }
    @Test
    public void testValidateInput_InvalidFirstName() {
        Throwable exception = assertThrows(IllegalStateException.class, () -> {
            RegisterUtils.validateInput("", "InvalidFirstName", "LastName", "email@example.com", "password", "password");
        });
        assertEquals("UI instance is not available. It means that you are calling this method out of a normal workflow where it's always implicitly set. That may happen if you call the method from the custom thread without 'UI::access' or from tests without proper initialization.", exception.getMessage());
    }
}
