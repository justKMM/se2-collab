package UtilsTest;

import hbrs.se2.collhbrs.util.Globals;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalsTest {

    @Test
    void testPages() {
        assertEquals("main", Globals.Pages.MAIN);
        assertEquals("reminder", Globals.Pages.REMINDER);
        assertEquals("", Globals.Pages.LOGIN);
        assertEquals("forgot-password", Globals.Pages.FORGOT_PASSWORD);
        assertEquals("signup", Globals.Pages.SIGNUP);
        assertEquals("main/student/profile-page", Globals.Pages.PROFIL_STUDENT);
    }

    @Test
    void testRoles() {
        //Für spätere Globals
        //assertEquals();
    }

    @Test
    void testLanguage() {
    }
}
