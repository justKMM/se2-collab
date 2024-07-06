package UtilsTest;

import hbrs.se2.collhbrs.util.Globals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalsTest {

    @Test
    void testPages() {
        assertEquals("", Globals.Pages.MAIN);
        assertEquals("main/", Globals.Pages.APP);
        assertEquals("reminder", Globals.Pages.REMINDER);
        assertEquals("login", Globals.Pages.LOGIN);
        assertEquals("forgot/password", Globals.Pages.FORGOT_PASSWORD);
        assertEquals("signup", Globals.Pages.SIGNUP);
        assertEquals("student/profile", Globals.Pages.PROFIL_STUDENT);
    }
}
